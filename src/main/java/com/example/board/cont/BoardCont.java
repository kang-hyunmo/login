package com.example.board.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardCont {
    @GetMapping("/board")
    @ResponseBody
    public String board() {
        return "board";

    }
}
