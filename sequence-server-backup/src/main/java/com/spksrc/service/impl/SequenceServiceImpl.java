package com.spksrc.service.impl;

import com.spksrc.service.SequenceService;
import com.spksrc.tools.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by apple on 16/9/8.
 */
@Component
public class SequenceServiceImpl implements SequenceService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public long getOneSequence() {
        long result = 0L;
        try {
            IdWorker idWorker = new IdWorker(2,2);
            result = idWorker.nextId();
        }catch (Exception e){
            logger.error("general key error:"+ e.getMessage());
        }
        return result;
    }
}
