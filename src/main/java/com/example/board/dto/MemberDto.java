package com.example.board.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String m_id;
    private String m_pw;
    private String m_name;
    private String m_birth;
    private String m_addr;
    private String m_point;



}
