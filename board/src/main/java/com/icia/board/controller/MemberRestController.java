package com.icia.board.controller;

import com.icia.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberRestController {
    private final MemberService memberService;

    @GetMapping("/member/idcheck")
    public ResponseEntity<Boolean> idcheck(String m_id) {
        log.info("=====idcheck m_id:{}", m_id);
        return  ResponseEntity.ok(!memberService.isUsedId(m_id));//true or false
//        ResponseEntity<String> result = null;
//        if(!memberService.isUsedId(m_id)){
//            //false(미사용중)->true
//            return ResponseEntity.ok().body("사용가능한 아이디입니다.");
//        }else{
//            //true(사용중)-->false
        //ResponseEntity객체는 상태값+데이터값을 한번에 저장하는 객체
//            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("사용불가한 아이디입니다.");
        //       }
    }
}

