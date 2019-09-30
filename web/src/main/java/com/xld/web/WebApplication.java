package com.xld.web;

import com.spring4all.swagger.EnableSwagger2Doc;
import com.xld.common.config.SpringSessionConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 项目WEB启动类
 * @author xld
 */
@SpringBootApplication
@MapperScan("com.xld.core.dao.user")
@ComponentScan({"com.xld.common", "com.xld.web", "com.xld.core.service","com.xld.web.controller"})
@EnableRedisHttpSession(redisNamespace = SpringSessionConfig.USER_REDIS_NAMESPACE,  maxInactiveIntervalInSeconds = 3600)
@EnableSwagger2Doc
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
