package com.xld.timer.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @descript 定时器测试
 * @author xld
 * @date 2019-09-29 4:01 PM
 */
@Component
public class TestTimer {

    @Scheduled(cron = "0/5 * * * * ?")
    public void test(){
        System.out.println("----定时器测试----");
    }
}
