package com.spksrc.service;

import com.spksrc.Hystrix.SequenceClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by apple on 16/9/9.
 */
@FeignClient(value ="sequence-service", fallback = SequenceClientHystrix.class)
public interface SequenceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/SequenceService/get")
    long getOneSequnce();

    @RequestMapping(method = RequestMethod.GET, value = "/api/SequenceService/add")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
}
