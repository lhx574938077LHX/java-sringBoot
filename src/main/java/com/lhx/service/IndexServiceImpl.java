package com.lhx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lihongxiang
 * @date
 */
@Service
public class IndexServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    public void uid(String sessionId){

    }

}
