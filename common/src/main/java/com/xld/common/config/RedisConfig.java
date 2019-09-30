package com.xld.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redisTemplate初始化,开启spring-session redis存储支持
 * @author xld
 */
@Configuration
public class RedisConfig {

    /**
     * redisTemplate 序列化使用的Serializeable, 存储二进制字节码, 所以自定义序列化类
     * @return redisTemplate
     * @Rparam redisConnectionFactory
     */
    @Bean
    public RedisTemplate<Object, Object> protoStuffTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // redis value使用的序列化器
        template.setValueSerializer(new ProtostuffSerializer());
        // redis key使用的序列化器
        template.setKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}
