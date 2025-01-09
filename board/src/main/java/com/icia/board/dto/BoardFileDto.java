package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardFileDto {
    private long bf_num;
    private long bf_bnum;
    private String bf_orifilename;
    private String bf_sysfilename;

}
