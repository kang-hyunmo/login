package com.example.board.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BoardDto {
    private int b_num;
    private String b_title;
    private String b_content;
    private String b_id;
    private String b_date;
    private String b_views;
}
