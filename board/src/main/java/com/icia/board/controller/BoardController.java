package com.icia.board.controller;

import com.icia.board.dto.BoardDto;
import com.icia.board.dto.SearchDto;
import com.icia.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    //localhost/board
    //@GetMapping ("/list")
    @GetMapping
    public String list(SearchDto searchDto, Model model) {
        log.info("========before searchDto={}", searchDto);
            //서비스 --> DB --> 게시글들
            if(searchDto.getPageNum() == null){
                searchDto.setPageNum(1);
            }
            if(searchDto.getListCount() == null){
                searchDto.setListCount(BoardService.LISTCOUNT);
            }
            if(searchDto.getStartIndex() == null){
                searchDto.setStartIndex(0);
            }

            List<BoardDto> boardList = null;

            if(searchDto.getColname( ) == null || searchDto.getKeyword() == null){
                boardList = boardService.getBoardList(searchDto.getPageNum()); //페이지 번호 클릭
            }else{
                boardList = boardService.getBoardList(searchDto);
            }

            if(boardList != null) {
                //페이지 정보
                String pageHtml = boardService.getPaging(searchDto.getPageNum());
                model.addAttribute("paging", pageHtml);
                model.addAttribute("boardList", boardList); //js(json) , each문
                return "board/list";
        }
        return "redirect:/";
    }
    @GetMapping("/write")
    public String write() {
        return "/board/write";
    }
    @PostMapping("/write")
    public String write(BoardDto boardDto) {
        //DB에 글을 저장
        return "redirect:/board/list"; //Get만 허용
    }
}
