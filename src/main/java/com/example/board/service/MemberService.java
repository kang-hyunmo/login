package com.example.board.service;


import com.example.board.dao.MemberDao;
import com.example.board.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberDao mDao;

    public boolean login(MemberDto memberDto) {
       return mDao.login(memberDto);
    }
}
