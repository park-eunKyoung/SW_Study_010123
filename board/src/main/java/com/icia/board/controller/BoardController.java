package com.icia.board.controller;

import com.icia.board.dto.BoardDto;
import com.icia.board.dto.BoardFileDto;
import com.icia.board.dto.ReplyDto;
import com.icia.board.dto.SearchDto;
import com.icia.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/viewimg")
    public String viewImg(Model model) {
        return "board/viewimg";
    }

    //localhost/board
    //@GetMapping ("/list")
    @GetMapping
    public String list(SearchDto searchDto, Model model,HttpSession session) {
        log.info("========before searchDto={}", searchDto);
        //서비스 --> DB --> 게시글들
        if (searchDto.getPageNum() == null) {
            searchDto.setPageNum(1);
        }
        if (searchDto.getListCount() == null) {
            searchDto.setListCount(BoardService.LISTCOUNT);
        }
        if (searchDto.getStartIndex() == null) {
            searchDto.setStartIndex(0);
        }
        List<BoardDto> boardList = null;
        //장작 쿼리 작성시
//            if(searchDto.getColname( ) == null || searchDto.getKeyword() == null){
//                boardList = boardService.getBoardList(searchDto.getPageNum()); //페이지 번호 클릭
//            }else{
//                boardList = boardService.getBoardList(searchDto);
//            }
        //동적 쿼리작성시
        boardList = boardService.getBoardListSearch(searchDto);
        if (boardList != null) {
            //페이지 정보
            String pageHtml = boardService.getPaging(searchDto);

            //상세보기에서 게시글 목록으로 돌아가기 위해
            if(searchDto.getColname()!=null){
                session.setAttribute("searchDto", searchDto);
                log.info("%%%검색중이었다면 searchDto 세션에 저장");
            }else{
                session.removeAttribute("searchDto");
                session.setAttribute("pageNum", searchDto.getPageNum());
            }
            model.addAttribute("paging", pageHtml);
            model.addAttribute("boardList", boardList); //js(json) , each문
            return "board/list";
        }
        return "redirect:/";
    }
    @GetMapping("/delete")
    public String boardDelete(@RequestParam("b_num") Integer b_num, RedirectAttributes rttr) {
        log.info("=====delete b_num:{}", b_num);
        if (b_num == null || b_num < 1) {
            return "redirect:/board";
        }
        if(boardService.boardDelete(b_num)){
            rttr.addFlashAttribute("msg", b_num+"번 삭제 성공"); //1번만 쓰고 삭제돼
            //rttr.addAttribute("msg", b_num+"번 삭제 성공"); //여러번출력
//            return "redirect:/board?pageNum=1";
            return "redirect:/board"; //기본값 1
        }else{
            rttr.addAttribute("msg", b_num+"번 삭제 실패");
            return "redirect:/board/detail?b_num="+b_num;
        }
    }
    @GetMapping("/detail")
    public String detailParam(@RequestParam("b_num") Integer b_num, Model model) {
        log.info("===con bnum:{}", b_num);
        return null;
    }

    @GetMapping("/detail/{b_num}") //.../board/detail/80/aaa
    public String detailBoard(@PathVariable("b_num") Integer b_num, Model model) {
        log.info("========detail b_num={}", b_num);
        //페이지 넘버가 없거나 음수일때
        if (b_num == null || b_num < 1) {
            return "redirect:/board";
        }
        BoardDto board = boardService.getBoardDetail(b_num);
        //log.info("board:{}", board); // 제목, 글쓴이, 내용, 날짜. 조회수
        if (board == null) {
            return "redirect:/board";
        } else {
            //댓글리스트 가져오기 동기 or 비동기
            //List<ReplyDto> rList = boardService.getReplyList(b_num);
            //첨부파일 리스트 가져오기
            List<BoardFileDto> boardFileDtoList = boardService.getBoardFileList(b_num);  //0~n개
            log.info("===boardFileDtoList.size:{}", boardFileDtoList.size());
            model.addAttribute("boardFileDtoList", boardFileDtoList);
            model.addAttribute("board", board);
            //model.addAttribute("rList", rList);
            return "board/detail";
        }
    }
    @GetMapping("/write")
    public String write() {
        return "board/write";
    }
    //    @PostMapping("/write")  // @RequestPart : Multipart-file을 받을 때, 생략가능함.
//    public String write(BoardDto board, @RequestPart List<MultipartFile> attachments) {
//        log.info("===write board:{}", board);  //b_writer, b_title, b_contents
//        log.info("===write attachments:{}", attachments.size());  //
//        for (MultipartFile file : attachments) {
//        log.info("====file:{}", file.getOriginalFilename());
//        return "redirect:/board";    //get만 허용
//    }
    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession httpsession, RedirectAttributes redirectAttributes) {
        // tomcat rootPath : main/webapp
        // realPath : main/webapp/upload
            log.info("===write board :{}", boardDto);
            //log.info("=====write board.attachments.size :{}", boardDto.getAttachments().size());
            for(MultipartFile file:boardDto.getAttachments()){
                log.info("=====file :{}", file.getOriginalFilename());
                log.info("=====file.getSize() :{}", file.getSize());
            }
        String realPath = httpsession.getServletContext().getRealPath("/");
        log.info("rootPath:{}", realPath);
        realPath+="upload/";
        log.info("realPath:{}", realPath);
        File dir =  new File(realPath);
        if(!dir.exists()){
            dir.mkdir();
        }
        boolean result = boardService.boardWrite(boardDto,httpsession);
        if(result){
            redirectAttributes.addFlashAttribute("msg","글쓰기 성공");
            return "redirect:/board";
        }else{
            redirectAttributes.addFlashAttribute("msg","글쓰기 실패");
            return "redirect:/board/write";
        }
    }
}
