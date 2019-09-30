package com.xld.timer;

import com.xld.common.config.SpringSessionConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 系统定时器启动类
 * @author xld
 */
@SpringBootApplication
@MapperScan("com.xld.core.dao")
@ComponentScan({"com.xld.common","com.xld.core.service","com.xld.timer"})
@EnableRedisHttpSession(redisNamespace = SpringSessionConfig.BACK_REDIS_NAMESPACE,  maxInactiveIntervalInSeconds = 3600)
public class TimerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimerApplication.class, args);
    }

}
