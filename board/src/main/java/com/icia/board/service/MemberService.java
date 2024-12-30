package com.icia.board.service;

import com.icia.board.dao.MemberDao;
import com.icia.board.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor //final 대한 생성자 주입
public class MemberService {
    //@Autowired
    private final MemberDao memberDao;

    public MemberDto login(MemberDto memberDto) {
        String encodedPw = memberDao.getSecurityPw(memberDto.getM_pw());
        //log.info("encodedPw: {}", encodedPw);
        if(encodedPw != null){
            BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
            log.info("=======아이디 존재함");
                if(pwEncoder.matches(memberDto.getM_pw(), encodedPw)){
                    log.info("=======로그인 성공");
                    return memberDao.getMemberInfo(memberDto.getM_id());
                }
                else{
                    return null;
                }
             }
        else{
            return null;
        }
    }
    public boolean join(MemberDto memberDto) {
        //이미 사용중인 아이디 : true  , false
        if(memberDao.isUsedId(memberDto.getM_id())){
            return false; // 회원가입 실패
        }
        // Encoder (암호화) <-----> Decoder (복호화)
        //1111-> sddwqasd23adax(암호화)
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        memberDto.setM_pw(pwEncoder.encode(memberDto.getM_pw()));
        return memberDao.join(memberDto); //성공시 true , 실패시 false
    }
}
