package com.icia.board.controller;

import com.icia.board.dto.ReplyDto;
import com.icia.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/board")
public class BoardRestController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/reply")
    public List<ReplyDto> insertReply(ReplyDto replyDto) {
        List <ReplyDto> rList = boardService.insertReply(replyDto);
//        log.info("========insertReply={}", replyDto);
        return rList;
    }

    @GetMapping("/reply")
    public List<ReplyDto> selectReply(@RequestParam Integer r_bnum) {
        List<ReplyDto> rList = boardService.getReplyList(r_bnum);
//        log.info("나 어디있을까요====rList={}", rList);
        return rList; //ajax done() 리턴
    }
}
