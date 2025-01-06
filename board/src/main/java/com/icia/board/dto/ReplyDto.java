package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {
    //스프링 validation
    private int r_num; //pk
    private int r_bnum;
    private String r_content;
    private String r_writer;
    //직렬화(Serialization): jackson old버전에서는 LocalDateTime을 json변환시 충돌나면 추가할것
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    //역직렬화(Deserialization) : JSON을 Java 객체로 변환시 충돌나면 추가할 것
    // @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime r_date;
}
