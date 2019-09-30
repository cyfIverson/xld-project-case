package com.xld.common.config;

/**
 * 系统常规配置
 * @author xld
 */
public class SystemCommonConfig {

    /** 用户登录记录 -业务类型（2）+日期（6）+随机位（12） */
    public static final String LOGIN_USER = "10";

    /** 后台管理员登录记录 -业务类型（2）+日期（6）+随机位（12） */
    public static final String LOGIN_BACKER = "11";

    /** app端sessionId保留时间 单位秒*/
    public static final long SESSIONTIME = 1L * 7 * 24 * 60 * 60;

    /**
     * APP端的HTTP请求头的名字
     */
    public static final String ACCESS_TOKEN_APP = "APP-USER-SESSION";

    /**
     * WEB端的HTTP请求头的名字
     */
    public static final String ACCESS_TOKEN_WEB = "WEB-USER-SESSION";

}
