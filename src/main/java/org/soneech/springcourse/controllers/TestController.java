package org.soneech.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {
    @GetMapping("/exit")
    public String exit() {
        return "test/exit";
    }
}
