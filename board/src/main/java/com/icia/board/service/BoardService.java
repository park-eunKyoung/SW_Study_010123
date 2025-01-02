package com.icia.board.service;

import com.icia.board.common.Paging;
import com.icia.board.dao.BoardDao;
import com.icia.board.dto.BoardDto;
import com.icia.board.dto.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    public static final Integer LISTCOUNT = 10;
    @Autowired //필드 주입
    private BoardDao boardDao;
    public List<BoardDto> getBoardList(Integer pageNum) {
        //select * from board order by b_date desc limit 0,10
        Map<String,Integer> pagemap = new HashMap<>();
        pagemap.put("startIndex",(pageNum-1)*10);
        pagemap.put("listCount",10);
        return boardDao.getBoardList(pagemap);
    }
    public List<BoardDto> getBoardList(SearchDto searchDto) {
        Integer pageNum = searchDto.getPageNum();
        //페이지 번호를 limit 시작 번호로 변경 - 1P:index(0)~ , 2P:index(10)~
        searchDto.setStartIndex((pageNum-1) * BoardService.LISTCOUNT);
        return boardDao.getBoardListSearch(searchDto);

    }

    public String getPaging(Integer pageNum) {
        int totalNum = boardDao.getBoardCount();
        Paging paging = new Paging(totalNum, pageNum, 10, 2, "/board?");
        return paging.makeHtmlPaging();  //return "<a href=''>1</a>.....
    }
}
