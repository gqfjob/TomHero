package com.spksrc.controller;

import com.spksrc.service.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by apple on 16/9/8.
 */
@RestController
@RequestMapping("/api/SequenceService")
public class SequenceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;
    @Autowired
    private SequenceService sequenceService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public long get(){
        ServiceInstance instance = client.getLocalServiceInstance();
        long sequence = sequenceService.getOneSequence();
        logger.info("get a sequence :"+sequence+",host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return sequence;
    }


}
