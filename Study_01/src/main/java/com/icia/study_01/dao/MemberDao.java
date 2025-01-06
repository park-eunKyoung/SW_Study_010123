package com.icia.study_01.dao;

import com.icia.study_01.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    boolean login(MemberDto memberDto);

    boolean isUsedId(String id);

    boolean join(MemberDto memberDto);


    String getSecurityPw(String uPw);

    MemberDto getMemberInfo();
}
