package com.icia.board.dao;

import com.icia.board.dto.BoardDto;
import com.icia.board.dto.BoardFileDto;
import com.icia.board.dto.ReplyDto;
import com.icia.board.dto.SearchDto;
import org.apache.ibatis.annotations.Delete;
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
    List<BoardDto> getBoardListAll(Map<String, Integer> pageMap);


    int getBoardCount(SearchDto searchDto);

    @Delete("DELETE FROM BOARD WHERE B_NUM=#{b_num}")
    boolean boardDelete(Integer b_num);

    BoardDto getBoardDtail(Integer b_num);

    List<ReplyDto> getReplyList(Integer b_num);

    boolean insertReply(ReplyDto replyDto);

    boolean boardWriteSelectkey(BoardDto boardDto);

    boolean fileInsertMap(Map<String, String> fMap);

    @Select("select bf_orifilename, bf_sysfilename from boardfile where bf_bnum=#{bNum}")
    List<BoardFileDto> getBoardFileList(Integer bNum);
}
