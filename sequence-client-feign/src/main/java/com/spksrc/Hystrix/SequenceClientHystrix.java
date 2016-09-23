package com.spksrc.Hystrix;

import com.spksrc.service.SequenceClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by apple on 16/9/10.
 */
@Component
public class SequenceClientHystrix implements SequenceClient {
    @Override
    public long getOneSequnce() {
        return 0;
    }

    @Override
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return -9999;
    }
}
