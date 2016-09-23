package com.spksrc.controller;

import com.spksrc.service.SequenceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by apple on 16/9/8.
 */
@RestController
@RequestMapping("/api/SequenceService")
public class SequenceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    //@Autowired
    //private DiscoveryClient client;
    @Autowired
    private SequenceService sequenceService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiOperation(value="获取ID", notes="获取全局唯一ID")
    public long get(){
        //ServiceInstance instance = client.getLocalServiceInstance();
        long sequence = sequenceService.getOneSequence();
        //logger.info("get a sequence :"+sequence+",host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        logger.info("get a sequence :"+sequence+",host:server-001");

        return sequence;
    }
    @ApiOperation(value="加法计算", notes="计算两个正整数之和")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@ApiParam(required=true, name="a", value="第一个整数") @RequestParam Integer a, @ApiParam(required=true, name="b", value="第二个整数")@RequestParam Integer b){
        return a+b;
    }

}
