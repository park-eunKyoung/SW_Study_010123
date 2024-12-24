package com.example.demo.controller;

import com.example.demo.dto.DataDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String index() {    // Req
        log.info("log test:{}", "ok");
        return "index"; //index.html
    }

    @GetMapping("/intro")
    public String intro(Model model) {
        model.addAttribute("currentDate", LocalDate.now());
        return "intro";
    }

    @GetMapping("/t_output")
    public String output(Model model) {
        model.addAttribute("htmlStr", "<h3>Hello World</h3>");

        // map 데이터 묵음
        Map<String, String> map = new HashMap<>();
        map.put("name", "김남해");
        map.put("age", "25");
        map.put("address", "인천");

        model.addAttribute("map", map);

        // Dto(또는 Vo) 데이터 묶음
        // alt + enter 자동완성
        DataDto dataDto = new DataDto();
        dataDto.setName("김남해");
        dataDto.setAge(25);
        dataDto.setAddress("인천");
        model.addAttribute("dtoData", dataDto);

        model.addAttribute("msg", "서버로부터의 메시지");
        return "t_output";
    }

    // @RequestMapping(value = "/t_control", method = RequestMethod.GET)
    @GetMapping("/t_control")
    public String control(Model model, HttpSession session) {
        session.setAttribute("user_id", "admin");
        // model.addAttribute("session", session); // 보안때문에 session객체를 model에 담아서 thymeleaf로 넘겨야 함
        // session.invalidate();
        model.addAttribute("msg", "이 문자열이 보입니다.");
        model.addAttribute("age", 25);

        List<DataDto> dataDtoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataDto dataDto = new DataDto();
            dataDto.setName("이름" + i);
            dataDto.setAge((i + 1) * 10);
            dataDto.setAddress("주소" + i);
            dataDtoList.add(dataDto);
        }
        model.addAttribute("dataDtoList", dataDtoList);

        return "t_control";
    }

    @GetMapping("/info/{id}")
    public String info(Model model, @PathVariable("id") String id) {
        return "info";
    }

    @GetMapping("/senddata")
    public String senddata(Model model) {
        return "senddata";
    }

    @GetMapping("/a_send")
    public String aSend(@RequestParam("num1") Integer num1, @RequestParam("num2") Integer num2
            , Model model) {
        System.out.println("num1 : " + num1);
        System.out.println("num2 : " + num2);
        model.addAttribute("result", num1 + num2);
        return "s_result";
    }

    @GetMapping("/noneDtoSend")
    public String noneDtoSend(String name,
                              @RequestParam(value = "age", defaultValue = "1") int age,
                              String address,
                              Model model) {
        System.out.println("name : " + name);
        System.out.println("age : " + age);
        System.out.println("address : " + address);
        model.addAttribute("result", "none dto Send Ok");
        return "s_result";
    }

    @PostMapping("/dtoSend")
    public String dtoSend(@ModelAttribute DataDto dto, Model model) {
        System.out.println("name : " + dto.getName());
        System.out.println("age : " + dto.getAge());
        System.out.println("address : " + dto.getAddress());
        model.addAttribute("result", "dto Send Ok");
        return "s_result";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("user", new DataDto("park", 10, "인천"));
        return "user";
    }

    @PostMapping("/user")
    public String userPost(DataDto dto, Model model) {
        System.out.println(dto);
        return null;
    }
}



