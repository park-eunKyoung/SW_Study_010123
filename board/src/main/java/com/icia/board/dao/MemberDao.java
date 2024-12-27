package com.icia.board.dao;
//DB FW : ibatis  ---> mybatis
import com.icia.board.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDao {
    //@Select("select count(*) from member where m_id=#{m_id} and m_pw=#{m_pw}")
    boolean login(MemberDto memberDto);
}
