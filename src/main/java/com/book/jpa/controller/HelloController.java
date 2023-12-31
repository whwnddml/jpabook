package com.book.jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class HelloController {



    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }


}
