package com.icia.board.dao;

import com.icia.board.dto.BoardDto;
import com.icia.board.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Mapper
public interface BoardDao {

    void insertDummyData(BoardDto boardDto);

    ArrayList<BoardDto> getBoardList(Map<String, Integer> pagemap);
    //동적쿼리 이용해서 키워드가 있거나 없거나 상관없이 검색
    List<BoardDto> getBoardListSearch(SearchDto searchDto);

    @Select("select * from board")
    List<BoardDto> getBoardListAll();


    int getBoardCount(SearchDto searchDto);
}
