package com.icia.study_01.controller;

import com.icia.study_01.dto.MemberDto;
import com.icia.study_01.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    //@Autowired
    private final MemberService memberService;

    @GetMapping("/login")
    public String login(HttpSession httpSession) {
        if(httpSession != null) {
            return "redirect:/";
        }
        return "member/login";
    }
    @PostMapping("/login")
    public String login(MemberDto memberDto) {
        log.info("id :{},pw :{}",memberDto.getM_id(),memberDto.getM_pw());
        MemberDto member = new MemberDto();
        member.setM_id(memberDto.getM_id());
        return "redirect:/";
    }
}
