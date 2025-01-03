package com.example.board.dao;

import com.example.board.dto.BoardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
public class BoardDaoTest {
    @Autowired   //junit 테스트시 필드주입만 된다.
    private BoardDao boardDao;

    @Test
    public void initTest(){
        assertNotNull(boardDao);
    }

    //@Test
    public void insertDummyDataTest(){
        BoardDto boardDto=new BoardDto();
        for(int i=1;i<=35;i++){
            boardDto.setB_title("제목"+i).setB_contents("무궁화꽃이 피었습니다.")
                    .setB_writer("icia");
            boardDao.insertDummyData(boardDto);
        }
    }
    //@Test
    public void findBoardListTest(){
        //assertEquals(35,boardDao.getBoardListAll().size());
        boardDao.getBoardListAll().stream().forEach(bDto -> System.out.println(bDto) );
    }
}
