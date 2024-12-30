package com.example.board.cont;

import com.example.board.dao.MemberDao;
import com.example.board.dto.MemberDto;
import com.example.board.service.MemberService;
import jakarta.servlet.HttpConstraintElement;
import jakarta.servlet.http.HttpSession;
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
    public String login(MemberDto memberDto,HttpSession session) {
        log.info("id:{}, pw:{}", memberDto.getM_id(), memberDto.getM_pw());
        //DB에서 select
//        MemberDto memberDto=new MemberDto();
//        memberDto.setM_id(m_id).setM_pw(m_pw);
//        MemberDto memberDto=MemberDto.builder().m_id(m_id).m_pw(m_pw).build();
//        boolean result= mSer.login(memberDto);
        MemberDto member = mSer.login(memberDto);
        log.info("member:{}", member);
        if (member != null) {
            session.setAttribute("member", member);
            Object url = session.getAttribute("urlPrior_login");
            if (url != null) {
                //session.setAttribute("id",memberDto.getM_id());
                return "redirect:" + url.toString();
            }
            return "redirect:/";
        }
        return "index";
    }
    @GetMapping("/join")
    public String join(HttpSession session) {
        //인가(권한)여부 하나하나씩 하기 힘듦 --> 인터셉터 & 시큐리티
        if(session.getAttribute("member") != null) {
            return "redirect:/";
        }
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
    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes rtts) {
    session.invalidate();
    rtts.addFlashAttribute("msg","success");
    return "redirect:/";
    }

}
