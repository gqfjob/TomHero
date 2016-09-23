package com.spksrc.controller;

import com.spksrc.service.SequenceClient;
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
    SequenceClient sequenceClient;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public long get() {
        return sequenceClient.getOneSequnce();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(){
        return  sequenceClient.add(10,20);
    }
}
