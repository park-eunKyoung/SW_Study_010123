package com.icia.study_01.service;


import com.icia.study_01.dao.MemberDao;
import com.icia.study_01.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDao memberDao;
    public MemberDto login(MemberDto memberDto) {
        String encoPw = memberDao.getSecurityPw(memberDto.getU_pw());
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        if(pwEncoder.matches(encoPw, memberDto.getU_pw())) {
            return memberDao.getMemberInfo();
        }else{
        }
        return null;
        }
    public boolean join(MemberDto memberDto) {
        if(memberDao.isUsedId(memberDto.getU_id())){
            return false;
        }
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        memberDto.setU_pw(pwEncoder.encode(memberDto.getU_pw()));
        return memberDao.join(memberDto);
    }
}
