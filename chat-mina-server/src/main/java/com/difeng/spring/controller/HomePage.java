package com.difeng.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by apple on 16/9/23.
 */
@Controller
@RequestMapping("/home")
public class HomePage {
    @RequestMapping("/index")
    public String Index(){
        return "chat-server started!";
    }
}
