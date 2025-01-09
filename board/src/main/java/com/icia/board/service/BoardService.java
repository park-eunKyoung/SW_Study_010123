package com.icia.board.service;

import com.icia.board.common.FileManager;
import com.icia.board.common.Paging;
import com.icia.board.dao.BoardDao;
import com.icia.board.dao.MemberDao;
import com.icia.board.dto.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BoardService {
    public static final Integer LISTCOUNT = 10;
    public static final Integer PAGCOUNT = 2;


    @Autowired //필드 주입
    private BoardDao boardDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private FileManager fileManager;

    public List<BoardDto> getBoardList(Integer pageNum) {
        //select * from board order by b_date desc limit 0,10
        Map<String, Integer> pagemap = new HashMap<>();
        pagemap.put("startIndex", (pageNum - 1) * 10);
        pagemap.put("listCount", 10);
        return boardDao.getBoardList(pagemap);
    }

    public List<BoardDto> getBoardList(SearchDto searchDto) {
        Integer pageNum = searchDto.getPageNum();
        //페이지 번호를 limit 시작 번호로 변경 - 1P:index(0)~ , 2P:index(10)~
        searchDto.setStartIndex((pageNum - 1) * BoardService.LISTCOUNT);
        return boardDao.getBoardListSearch(searchDto);

    }

    public String getPaging(SearchDto searchDto) {
        int totalNum = boardDao.getBoardCount(searchDto);
        log.info("totalNum:{}", totalNum);
        String listUrl = null;
        if (searchDto.getColname() != null) {
            listUrl = "/board?colname=" + searchDto.getColname() + "&keyword=" + searchDto.getKeyword() + "&";
        } else {
            listUrl = "/board?";
        }
        Paging paging = new Paging(totalNum, searchDto.getPageNum(), searchDto.getListCount(), PAGCOUNT, "/board?");
        return paging.makeHtmlPaging();  //return "<a href=''>1</a>.....
    }

    public List<BoardDto> getBoardListSearch(SearchDto searchDto) {
        Integer pageNum = searchDto.getPageNum();
        //searchDto.setStartIndex((pageNum-1) * LISTCOUNT);
        searchDto.setStartIndex((pageNum - 1) * searchDto.getListCount());
        return boardDao.getBoardListSearch(searchDto);
    }

    public BoardDto getBoardDetail(Integer b_num) {
        return boardDao.getBoardDtail(b_num);
    }

    public boolean boardDelete(Integer b_num) {
        return boardDao.boardDelete(b_num);
    }

    public List<ReplyDto> getReplyList(Integer b_num) {
        return boardDao.getReplyList(b_num);
    }

    public List<ReplyDto> insertReply(ReplyDto replyDto) {
        List<ReplyDto> rList = null;
        if (boardDao.insertReply(replyDto)) {
            rList = boardDao.getReplyList(replyDto.getR_bnum());
        }
        return rList;
    }

    public boolean boardWrite(BoardDto boardDto, HttpSession httpsession) {
        //1. 글번호(100), 글제목, 글내용, 글쓴이,... insert board 후 즉시 b_num(100) 반환
        //1-1. select b_num from board; //100번
        //2. 만약 첨부파일이 있다면, 글번호, 원파일명, 난수 파일명, insert boardfile (100)
        boolean result = boardDao.boardWriteSelectkey(boardDto);
        log.info("새글번호 :{}", boardDto.getB_num());
        if (result) {
            //글쓰기 마다 회원에게 point 10점 주기
            MemberDto memberDto = (MemberDto) httpsession.getAttribute("member");
            int point = memberDto.getM_point() + 10;
            if (point > 999) {
                point = 999;
            }
            memberDto.setM_point(point);
            memberDao.updateMemberPoint(memberDto);
            memberDto = memberDao.getMemberInfo(memberDto.getM_id()); // 등급포함 회원의 최신 정보 가져옴
            httpsession.setAttribute("memberDto", memberDto);
            //첨부파일 여부 확인 
            if (!boardDto.getAttachments().get(0).isEmpty()) {
                //파일업로드, DB insert
                if (fileManager.fileUpload(boardDto.getAttachments(), httpsession, boardDto.getB_num())) {
                    log.info("uplad 성공");
                    return true; //첨부파일과 글쓰기 성공
                }
                return true; //첨부파일 글쓰기 성공
            }
            return true; //첨부파일 없이 글쓰기 성공
        } else {
            return false;
        }
    }

    public List<BoardFileDto> getBoardFileList(Integer bNum) {
       return boardDao.getBoardFileList(bNum);
    }
}
