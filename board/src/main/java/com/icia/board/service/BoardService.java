package com.icia.board.service;

import com.icia.board.common.Paging;
import com.icia.board.dao.BoardDao;
import com.icia.board.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired //필드 주입
    private BoardDao boardDao;
    public List<BoardDto> getBoardList(Integer pageNum) {
        //select * from board order by b_date desc limit 0,10
        Map<String,Integer> pagemap = new HashMap<>();
        pagemap.put("startIndex",(pageNum-1)*10);
        pagemap.put("pageSize",10);

        return boardDao.getBoardList(pagemap);

    }

    public String getPaging(Integer pageNum) {
        int totalNum = boardDao.getBoardCount();
        Paging paging = new Paging(totalNum, pageNum, 10, 2, "/board?");
        return paging.makeHtmlPaging();  //return "<a href=''>1</a>.....
    }
}
