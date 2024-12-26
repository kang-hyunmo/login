package com.example.board.cont;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCont {
    @GetMapping
    public String home() {
        return "index";
    }
}
