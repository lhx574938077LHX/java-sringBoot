package com.lhx.interceptor.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author lihongxiang
 * @date
 */
@Configuration
@EnableScheduling // 启用定时任务
public class TestScheduling {

    private final Logger logger = LoggerFactory.getLogger(TestScheduling.class);

    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        logger.info(">>>>>>>>>>>>> scheduled ... ");
    }

}
