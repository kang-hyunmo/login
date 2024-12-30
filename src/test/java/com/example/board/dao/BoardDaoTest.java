package com.example.board.dao;

import com.example.board.dto.BoardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardDaoTest {
    @Autowired
    private BoardDao boardDao;
    @Test
    void insertDummyDataTest(){
        BoardDto boardDto=new BoardDto();
        for(int i=1;i<=35;i++){
            boardDto.setB_title("제목"+i).setB_content("무궁화 꽃이 피었습니다")
                    .setB_id("icia");
            boardDao.insertDummyData(boardDto);
        }

        }
    }

