package com.example.board.dao;

import com.example.board.dto.BoardDto;
import com.example.board.dto.SearchDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
    void insertDummyData(BoardDto boardDto);

    ArrayList<BoardDto> getBoardList(Map<String, Integer> pageMap);


    List<BoardDto> getBoardListSearch(SearchDto sDto);
    List<BoardDto> getBoardListSearchNew(SearchDto sDto);


    @Select("select * from board")
    List<BoardDto> getBoardListAll();

    int getBoardCount(SearchDto sDto);


    BoardDto getBoardDetail(Integer bNum);

    @Delete("delete from board where b_num=#{bnum}")
    boolean boardDelete(Integer bNum);
}
