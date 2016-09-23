package com.spksrc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
class MainRoot{
    @RequestMapping("/")
    public String home(){
        return "common-library";
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(MainRoot.class, args);
    }

}