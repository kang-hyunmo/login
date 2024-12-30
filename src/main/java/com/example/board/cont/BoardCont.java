package com.example.board.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardCont {
    @GetMapping("/board/list")
    @ResponseBody
    public String list() {
        return "게시글 리스트 보기";

    }
}
