package com.example.board.cont;

import com.example.board.dao.MemberDao;
import com.example.board.dto.MemberDto;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberCont {
    //@Autowired
    private final MemberService mSer;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }
    @PostMapping("/login")
    public String login(MemberDto memberDto) {
        log.info("id:{}, pw:{}", memberDto.getM_id(),memberDto.getM_pw());
        //DB에서 select
//        MemberDto memberDto=new MemberDto();
//        memberDto.setM_id(m_id).setM_pw(m_pw);
//        MemberDto memberDto=MemberDto.builder().m_id(m_id).m_pw(m_pw).build();
        boolean result= mSer.login(memberDto);
        if(result){
            return "board/list";
        }
        return "index";
    }
    @GetMapping("/join")
    public String join() {
        return "member/join";
    }
    @PostMapping("/join")
    public String join(MemberDto memberDto, Model model, RedirectAttributes rtts) {
        log.info("memberDto:{}", memberDto);
        boolean result= mSer.join(memberDto);
        if(result){
//            model.addAttribute("msg","success");
            rtts.addFlashAttribute("msg","success");
            return "redirect:/";
        }rtts.addFlashAttribute("msg","fail");

        return "member/join";
    }

}
