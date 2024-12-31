package com.icia.board.controller;

import com.icia.board.dto.BoardDto;
import com.icia.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    //localhost/board
    //@GetMapping ("/list")
    @GetMapping
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        //서비스 --> DB --> 게시글들
        List<BoardDto> boardList = boardService.getBoardList(pageNum);
        if(boardList != null) {
            //페이지 정보
            String pageHtml = boardService.getPaging(pageNum);
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
