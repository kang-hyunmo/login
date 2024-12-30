package com.example.board.service;


import com.example.board.dao.MemberDao;
import com.example.board.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //final 생성자 주입
@Slf4j
public class MemberService {
    //@Autowired
    private final MemberDao mDao;

    public MemberDto login(MemberDto memberDto) {
        String encoPw= mDao.getSecurityPw(memberDto.getM_id());
        log.info("encoPw:{}",encoPw);
        if (encoPw!=null){
            BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
            log.info("아이디 존재");

            if (pwEncoder.matches(memberDto.getM_pw(), encoPw)){
                log.info("로그인 성공");
                return mDao.getMemberInfo(memberDto.getM_id());
            }else {
                log.info("비번 오류");
            }

        }else {
            log.info("아이디 x");

        }
        return null;
//       return mDao.login(memberDto);
    }

    public boolean join(MemberDto memberDto) {

        if(mDao.isUserId(memberDto.getM_id())){
            return false;
        }
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        memberDto.setM_pw(pwEncoder.encode(memberDto.getM_pw()));
        return mDao.join(memberDto);

    }
}
