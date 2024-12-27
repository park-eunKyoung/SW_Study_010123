package com.icia.board.service;

import com.icia.board.dao.MemberDao;
import com.icia.board.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final 대한 생성자 주입
public class MemberService {
    //@Autowired
    private final MemberDao mDao;

    public boolean login(MemberDto memberDto) {
       return mDao.login(memberDto);
    }

    public boolean join(MemberDto memberDto) {
        //이미 사용중인 아이디 : true  , false
        if(mDao.isUsedId(memberDto.getM_id())){
            return false; // 회원가입 실패
        }
        return mDao.join(memberDto); //성공시 true , 실패시 false
    }
}
