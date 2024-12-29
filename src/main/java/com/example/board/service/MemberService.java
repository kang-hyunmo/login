package com.example.board.service;


import com.example.board.dao.MemberDao;
import com.example.board.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final 생성자 주입
public class MemberService {
    //@Autowired
    private final MemberDao mDao;

    public boolean login(MemberDto memberDto) {
       return mDao.login(memberDto);
    }

    public boolean join(MemberDto memberDto) {
        return mDao.join(memberDto);

    }
}
