package com.icia.board.dao;

import com.icia.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Mapper
public interface BoardDao {

    void insertDummyData(BoardDto boardDto);

    ArrayList<BoardDto> getBoardList(Map<String, Integer> pagemap);

    @Select("select * from board")
    List<BoardDto> getBoardListAll();

    @Select("SELECT count(*) from board")
    int getBoardCount();
}
