package com.example.board.dao;

// DB FW: ibatis-->mybatis
import com.example.board.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberDao {


    String getSecurityPw(String mId);



    // @Select("select count(*) from member where m_id=#{m_id} and m_pw={m_pw}")
    boolean login(MemberDto memberDto);

    boolean join(MemberDto memberDto);

    MemberDto getMemberInfo(String mId);


    boolean isUserId(String mId);
}
