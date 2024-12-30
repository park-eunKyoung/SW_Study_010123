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

        //boolean result = mSer.login(memberDto);
        MemberDto member = mSer.login(memberDto);
        if (member != null) {
            session.setAttribute("id",memberDto.getM_id());
            //session.setAttribute("member",member);
            //return "board/list";
            return "redirect:/";
        }else{
            return "index";
        }
    }
    @GetMapping("/join")
    public String join(HttpSession session) {
        //인가여부를 너무 많이 확인해야 함. ---> 인터셉터 or 시큐리티 활용
        if (session.getAttribute("member") != null) {
            return "redirect:/";
        }
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
