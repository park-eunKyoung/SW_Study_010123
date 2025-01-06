package com.icia.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
public class HomeController {
    private final HttpServletResponse httpServletResponse;

    public HomeController(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    //    @GetMapping("/")
    @GetMapping
    public String home(HttpSession session, Model model, Locale locale, HttpServletRequest req) {
        //검증할 코드
        //세션의 불필요한 속성 객체 즉시 삭제하거나 루트에서 삭제할 것.
        if (session.getAttribute("urlPrior_login") != null) {
            session.removeAttribute("urlPrior_login");
        }
        session.removeAttribute("searchDto");
        session.removeAttribute("pageNum");
        return "index";  //index.html
    }
}
