package com.icia.board.controller;

import com.icia.board.dto.MemberDto;
import com.icia.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor //final 필드에 대한 생성자
@RequestMapping("/member")
public class MemberController {
    //@Autowired
    private final MemberService mSer;
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }
    @PostMapping("/login")
    public String login(MemberDto memberDto,HttpSession session){
        log.info("===========id:{},pw:{}",memberDto.getM_id(),memberDto.getM_pw());
        //DB에서 select
        //MemberDto memberDto = new MemberDto();
        //memberDto.setM_id(m_id).setM_pw(m_pw);
        //MemberDto memberDto = MemberDto.builder().m_id(m_id).m_pw(m_pw).build();
        boolean result = mSer.login(memberDto);
        if (result){
            session.setAttribute("id",memberDto.getM_id());
            //return "board/list";
            return "redirect:/";
        }
        return "index";
    }
    @GetMapping("/join")
    public String join() {
        return "member/join";
    }
    @PostMapping("/join")
    public String join(MemberDto memberDto, Model model, HttpSession session,RedirectAttributes rttr){
        log.info("===========memberDto:{}",memberDto);
        boolean result = mSer.join(memberDto);
        if(result){
            //model.addAttribute("msg","가입성공");
            rttr.addFlashAttribute("msg","가입성공");
            //session.setAttribute("msg","ss 가입성공");
            //session 영역에 저장 후 1번 사용후 session 영역에서 삭제함
            //return "redirect:/member/login";
            return "redirect:/"; //-> localhost/ --> index.html
        }
        //request 영역에 저장
        rttr.addFlashAttribute("msg","가입실패");
        //model.addAttribute("msg","가입실패");
        return "redirect:/member/join";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session,RedirectAttributes rttr){
        session.invalidate(); //session 무효화
        rttr.addFlashAttribute("msg","로그아웃 되었습니다.");
        return "redirect:/";
    }
} //controller end
