package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private String colname;
    private String keyword;
    private Integer pageNum; //현재페이지 번호
    private Integer listCount; //페이지당 글의 개수
    private Integer startIndex; //listCount 10일때, 1page index : 0~ , 2page index : 10~
}
