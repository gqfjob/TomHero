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
    private static final String SERVICE_URI="http://SEQUENCE-SERVICE";
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        return sequenceClient.getService();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return  sequenceClient.addService(10,20);
    }
}
