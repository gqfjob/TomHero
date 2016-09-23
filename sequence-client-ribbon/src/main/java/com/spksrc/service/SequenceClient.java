package com.spksrc.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by apple on 16/9/10.
 */
@Service
public class SequenceClient {
    @Autowired
    RestTemplate restTemplate;

    private static final String SERVICE_URI="http://SEQUENCE-SERVICE/api/SequenceService";

    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService(Integer a, Integer b) {
        return restTemplate.getForEntity(SERVICE_URI+"/add?a="+a+"&b="+b, String.class).getBody();
    }
    @HystrixCommand(fallbackMethod = "getServiceFallback")
    public String getService() {
        return restTemplate.getForEntity(SERVICE_URI + "/get", String.class).getBody();
    }

    public String getServiceFallback() {
        return "get sequence service is down";
    }
    public String addServiceFallback(Integer a, Integer b){
        return "add service is down";
    }
}
