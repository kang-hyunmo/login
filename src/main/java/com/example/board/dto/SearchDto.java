package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    //유효성 검사 spring

    private String colname;
    private String keyword;
    private Integer pageNum; //현재 페이지 번호

    private Integer listCnt; //페이지당 글의 갯수
    private Integer startIdx;
}
