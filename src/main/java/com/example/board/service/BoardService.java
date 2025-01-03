package com.example.board.service;

import com.example.board.common.Paging;
import com.example.board.dao.BoardDao;
import com.example.board.dto.BoardDto;
import com.example.board.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    public static final Integer LISTCNT = 10;
    public static final Integer PAGECOUNT = 2;

    private final BoardDao boardDao;

    public List<BoardDto> getBoardList(Integer pageNum) {
        //select * from board order b_date desc limit 0,10  , 1page
        //select * from board order b_date desc limit 10,10  , 2page
        //select * from board order b_date desc limit 20,10  , 3page
        Map<String,Integer> pageMap = new HashMap<>();
        pageMap.put("startIndex", (pageNum - 1) * 10);
        pageMap.put("pageSize", 10); //listCnt와 같음
        return boardDao.getBoardList(pageMap);
    }
    //검색을 통한 리스트
    public List<BoardDto> getBoardList(SearchDto sDto) {
        Integer pageNum = sDto.getPageNum();
        sDto.setStartIdx((pageNum - 1) * BoardService.LISTCNT);
        return boardDao.getBoardListSearch(sDto); //검색결과가 없거나 실패시 null
    }

    public String getPaging(SearchDto sDto) {
        int totalNum= boardDao.getBoardCount(sDto);
        log.info("totalNum="+totalNum);

        String listUrl=null;
        if (sDto.getColName()!=null){
            listUrl="/board?colName="+sDto.getColName()+"&keyword="+sDto.getKeyword()+"&";
        }else {
            listUrl="/board?";
        }

        Paging paging = new Paging(totalNum, sDto.getPageNum(), sDto.getListCnt(), PAGECOUNT,listUrl);
        return paging.makeHtmlPaging(); // return
    }
public List<BoardDto> getBoardListSearch(SearchDto sDto) {
        Integer pageNum = sDto.getPageNum();
        sDto.setStartIdx((pageNum - 1) * sDto.getListCnt());
        return boardDao.getBoardListSearch(sDto);
}

    public List<BoardDto> getBoardListSearchNew(SearchDto sDto) {
        Integer pageNum = sDto.getPageNum();
        sDto.setStartIdx((pageNum - 1) * sDto.getListCnt());
        return boardDao.getBoardListSearchNew(sDto);
    }

    public BoardDto getBoardDetail(Integer bNum) {
        return boardDao.getBoardDetail(bNum);
    }

    public boolean boardDelete(Integer bNum) {
        return boardDao.boardDelete(bNum);
    }
}
