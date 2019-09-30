package com.xld.common.bean.ENUM;

import com.xld.common.config.SpringSessionConfig;

/**
 * @description: spring redis session enum
 *               为不同端口提供spring redis session
 * @author xld
 */
public enum SpringSessionENUM {

    USER_NAMESPACE(SpringSessionConfig.USER_REDIS_NAMESPACE),
    BACK_NAMESPACE(SpringSessionConfig.USER_REDIS_NAMESPACE),

    BACK_SESSION(new SpringSessionConfig(SpringSessionConfig.BACK_REDIS_NAMESPACE, SpringSessionConfig.BACK_SESSION_NAME)),
    USER_SESSION(new SpringSessionConfig(SpringSessionConfig.USER_REDIS_NAMESPACE, SpringSessionConfig.USER_SESSION_NAME));

    private SpringSessionConfig springSessionConfig;

    SpringSessionENUM(SpringSessionConfig springSessionConfig) {
        this.springSessionConfig = springSessionConfig;
    }

    public SpringSessionConfig getSpringSessionConfig() {
        return springSessionConfig;
    }

    private String namespace;

    SpringSessionENUM (String redisNameSpace) {
        namespace = redisNameSpace;
    }

    public String getNamespace() {
        return namespace;
    }

}
