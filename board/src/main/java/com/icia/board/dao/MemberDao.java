package com.icia.board.dao;
//DB FW : ibatis  ---> mybatis
import com.icia.board.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDao {
    //@Select("select count(*) from member where m_id=#{m_id} and m_pw=#{m_pw}") //#{} -> 필드명
    boolean login(MemberDto memberDto);

    boolean join(MemberDto memberDto);

    //@Select("select count(*) from member where m_id=#{id}") // #{} -> 파라미터 명
    boolean isUsedId(String id);

    MemberDto getMemberInfo(String mId);

    String getSecurityPw(String mId);
}
