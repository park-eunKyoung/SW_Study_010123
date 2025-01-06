package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BoardDto {
    private int b_num;
    private String b_title;
    private String b_contents;
    private String b_writer;   //fk: m_id
    //private String b_date;  //단순출력용
    private LocalDateTime b_date;  //시간조작 편리  00초는 읽지못함
    private String b_view;
}
