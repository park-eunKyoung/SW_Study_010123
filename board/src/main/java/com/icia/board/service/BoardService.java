package com.icia.board.service;

import com.icia.board.common.Paging;
import com.icia.board.dao.BoardDao;
import com.icia.board.dto.BoardDto;
import com.icia.board.dto.ReplyDto;
import com.icia.board.dto.SearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<BoardDto> getBoardList(Integer pageNum) {
        //select * from board order by b_date desc limit 0,10
        Map<String,Integer> pagemap = new HashMap<>();
        pagemap.put("startIndex",(pageNum-1)*10);
        pagemap.put("listCount",10);
        return boardDao.getBoardList(pagemap);
    }
    public List<BoardDto> getBoardList(SearchDto searchDto) {
        Integer pageNum = searchDto.getPageNum();
        //페이지 번호를 limit 시작 번호로 변경 - 1P:index(0)~ , 2P:index(10)~
        searchDto.setStartIndex((pageNum-1) * BoardService.LISTCOUNT);
        return boardDao.getBoardListSearch(searchDto);

    }

    public String getPaging(SearchDto searchDto) {
        int totalNum = boardDao.getBoardCount(searchDto);
        log.info("totalNum:{}", totalNum);
        String listUrl = null;
        if(searchDto.getColname() !=null){
            listUrl="/board?colname="+searchDto.getColname()+"&keyword="+searchDto.getKeyword()+"&";
        }else{
            listUrl="/board?";
        }
        Paging paging = new Paging(totalNum, searchDto.getPageNum(),searchDto.getListCount(),PAGCOUNT, "/board?");
        return paging.makeHtmlPaging();  //return "<a href=''>1</a>.....
    }

    public List<BoardDto> getBoardListSearch(SearchDto searchDto) {
        Integer pageNum = searchDto.getPageNum();
        //searchDto.setStartIndex((pageNum-1) * LISTCOUNT);
        searchDto.setStartIndex((pageNum-1) * searchDto.getListCount());
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
        if(boardDao.insertReply(replyDto)){
            rList = boardDao.getReplyList(replyDto.getR_bnum());
        }
        return rList;
    }
}
