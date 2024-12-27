package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class boardController {
    @GetMapping("/list")
    @ResponseBody
    public String boardList() {
        return "/board/list 요청";
    }
    @GetMapping("/write")
    @ResponseBody
    public String boardWrite(){
        return "board/write 요청";
    }

}
