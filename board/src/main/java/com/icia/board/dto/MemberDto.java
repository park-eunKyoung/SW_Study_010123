package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Alias("MemberDto")
//@Alias("memberDto")
public class MemberDto {
    private String m_id;   //필드명==파라미터명==컬럼명
    private String m_pw;   //필드명==파라미터명==컬럼명
    private String m_name;   //필드명==파라미터명==컬럼명
    private String m_birth;   //필드명==파라미터명==컬럼명
    private String m_addr;   //필드명==파라미터명==컬럼명
    private String m_point;   //필드명==파라미터명==컬럼명

    private String g_name;    //회원 등급
}
