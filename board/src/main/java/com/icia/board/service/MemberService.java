package com.icia.board.service;

import com.icia.board.dao.MemberDao;
import com.icia.board.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor  //final 대한 생성자 주입
@Slf4j
public class MemberService {
    //@Autowired
    private final MemberDao mDao;

    public MemberDto login(MemberDto memberDto) {
        String encoPw = mDao.getSecurityPw(memberDto.getM_id());
        log.info("encoPw:{}", encoPw);
        if(encoPw!=null){
            BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
            log.info("====아이디 존재함");
            if(pwEncoder.matches(memberDto.getM_pw(),encoPw)){
                log.info("====로그인 성공");
                return mDao.getMemberInfo(memberDto.getM_id());
            }else{
                log.info("====비번 오류");
                //return null;
            }
        }else{
            log.info("=====아이디 오류");
            //return null;
        }
        return null;
    }

    public boolean join(MemberDto memberDto) {
        //이미 사용중인 아이디: true
        if (mDao.isUsedId(memberDto.getM_id())) {
            return false;  //회원가입 실패
        }
        // Encoder(암호화)  <----> Decoder(복호화)
        //1111->sdkjflkdsjkfjsdlkjflkdsjaf
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        memberDto.setM_pw(pwEncoder.encode(memberDto.getM_pw()));
        return mDao.join(memberDto);  //성공:true, 실패:false
    }
}
