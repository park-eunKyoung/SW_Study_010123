package com.icia.board.controller;

import com.icia.board.dto.MemberDto;
import com.icia.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor  //final 필드에 대한 생성자
@RequestMapping("/member")
public class MemberController {
     //@Autowired
    private final MemberService mSer;
    @GetMapping("/login")
    public String login(HttpSession session) {
        //로그인 상태라면 index.html 리다이렉트
        if(session.getAttribute("member")!=null){
            return "redirect:/";   //index.html
        }
            return "member/login";
    }
    @PostMapping("/login")
    public String login(MemberDto memberDto, HttpSession session, RedirectAttributes rttr) {
        log.info("============id:{}, pwd:{}", memberDto.getM_id(), memberDto.getM_pw());
        //DB에서 select
//        MemberDto memberDto=new MemberDto();
//        memberDto.setM_id(m_id).setm_pw(m_pw);

        //boolean result=mSer.login(memberDto);
        MemberDto member=mSer.login(memberDto);
        log.info("=====member: {}", member);
        if(member!=null){
            //session.setAttribute("id", memberDto.getM_id());
            session.setAttribute("member", member);  //id,name,point
            Object url = session.getAttribute("urlPrior_login");
            if(url!=null){
                session.removeAttribute("urlPrior_login");
                //System.out.println("url:"+url.toString());
                return "redirect:"+url.toString();
            }
            return "redirect:/board";
            //return "redirect:/member/login";
        }//end login ok

        //request객체에 파라미터 저장
        //rttr.addAttribute("msg", "로그인 실패");
        //session영역저장-->request영역(model속성객체)저장-->1번 사용후 자동삭제
        rttr.addFlashAttribute("msg", "로그인 실패");
        return "redirect:/";
    }
    @GetMapping("/join")
    public String join(HttpSession session) {
        //인가(권한)여부를 너무 많이 확인해야 됨.--->인터셉터 or 시큐리티 활용
        //로그인 상태라면 index.html 리다이렉트
        if(session.getAttribute("member")!=null){
            return "redirect:/";   //index.html
        }
        return  "member/join";
    }
    @PostMapping("/join")
    public String join(MemberDto memberDto, Model model, HttpSession session , RedirectAttributes rttr) {
        log.info("=========memberDto:{}", memberDto);
        boolean result=mSer.join(memberDto);
        if(result){
           //model.addAttribute("msg", "가입성공");
            //session.setAttribute("msg","ss 가입성공");
            rttr.addFlashAttribute("msg","가입성공");
            //session영역에 저장후 -->request영역(model속성객체)에 저장-->1번 사용후 삭제함.
            //return  "redirect:/member/login"; //
            return  "redirect:/";
        }
        //model.addAttribute("msg", "가입실패");
        rttr.addFlashAttribute("msg","가입실패");
        return "redirect:/member/join";
    }
    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes rttr) {
        session.invalidate();  //세션 무효화
        rttr.addFlashAttribute("msg","로그아웃 성공");
        return "redirect:/";
    }
}//con end
