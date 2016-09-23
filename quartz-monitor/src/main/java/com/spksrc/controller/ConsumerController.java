package com.spksrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by apple on 16/9/9.
 */
@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        return restTemplate.getForEntity("http://SEQUENCE-SERVICE/get", String.class).getBody();
    }

}
