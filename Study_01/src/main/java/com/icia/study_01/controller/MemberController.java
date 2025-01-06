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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    //@Autowired
    private final MemberService memberService;

    @GetMapping("/login")
    public String login(HttpSession httpSession) {
        if(httpSession.getAttribute("id") != null) {
            return "redirect:/"; //index.html
        }
        return "member/login";
    }
    @PostMapping("/login")
    public String login(MemberDto memberDto, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        log.info("id :{},pw :{}",memberDto.getU_id(),memberDto.getU_pw());
        MemberDto member = memberService.login(memberDto);
        log.info("member:{}", member);
        return "redirect:/";
    }
    @GetMapping("/join")
    public String join(HttpSession httpSession) {
        if(httpSession.getAttribute("member") != null) {
            return "redirect:/";
        }
        return "member/join";
    }
    @PostMapping("/join")
    public String join(MemberDto memberDto, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        log.info("member:{}", memberDto);
        boolean result = memberService.join(memberDto);
        if(result) {
            redirectAttributes.addFlashAttribute("msg","가입성공");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("msg","가입실패");
        return "redirect:/member/join";
    }

}
