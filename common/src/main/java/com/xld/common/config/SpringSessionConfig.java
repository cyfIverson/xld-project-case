package com.xld.common.config;

/**
 * @description: spring redis session config
 * @author xld
 */
public class SpringSessionConfig {

    /** 后台管理系统 redis 空间命名 */
    public static final String BACK_REDIS_NAMESPACE = "FINANCIAL_CHAIN_MANAGE";
    /** 用户端 redis 空间命名 */
    public static final String USER_REDIS_NAMESPACE = "FINANCIAL_CHAIN_USER";

    /** 后台管理系统 backerSession */
    public static final String BACK_SESSION_NAME = "backerSession";
    /** 用户端 userSession */
    public static final String USER_SESSION_NAME = "userSession";
    public static final String USER_SESSION_NAME_APP = USER_SESSION_NAME + ":APP:";
    public static final String USER_SESSION_NAME_WEB = USER_SESSION_NAME + ":WEB:";

    /**
     * spring redis session config
     * @param sessionName 客户端sessionName，不可为空
     */
    public SpringSessionConfig(String sessionName) {
        this(null, sessionName);
    }

    /**
     * spring redis session config
     * @param namespace redisSessioin 空间命名，可为空
     * @param sessionName 客户端sessionName，不可为空
     */
    public SpringSessionConfig(String namespace, String sessionName) {
        this(namespace, sessionName, 0);
    }

    /**
     * spring redis session config
     * @param namespace redisSessioin 空间命名，可为空
     * @param sessionName 客户端sessionName，不可为空
     * @param sessionTimeOut session销毁时间(秒)，默认1800秒
     */
    public SpringSessionConfig(String namespace, String sessionName, int sessionTimeOut) {
        this.namespace = namespace;
        this.sessionIdPreFix = (null == namespace || "".equals(namespace)) ? "spring:session:"
                : "spring:session:" + namespace + ":" ;
        this.sessions = this.sessionIdPreFix + "sessions:";
        this.sessionsExpires = this.sessionIdPreFix + "sessions:expires:";
        this.sessionsExpirations = this.sessionIdPreFix + "expirations:";
        this.sessionName = sessionName;
        this.sessionNameAttr = "sessionAttr:" + sessionName;
        this.sessionNamePower = "sessionAttr:" + sessionName + "RolePower";
        this.sessionNameId = this.sessionIdPreFix + sessionName + "Id:";
        this.sessionTimeOut = sessionTimeOut <= 0 ? 1800 : sessionTimeOut;
    }

    /** sessionId前缀 */
    private String namespace;
    /** sessionId前缀 */
    private String sessionIdPreFix;
    /** sessions: */
    private String sessions;
    /** sessions:expires: */
    private String sessionsExpires;
    /** expirations: */
    private String sessionsExpirations;
    /** 客户端名称 */
    private String sessionName;
    /** 客户端sessionAttr: */
    private String sessionNameAttr;
    /** 客户端sessionAttr:backer_rolePower */
    private String sessionNamePower;
    /** 客户端id前缀 */
    private String sessionNameId;
    /** session销毁时间(秒) */
    private int sessionTimeOut;

    /**  客户端 namespace */
    public String getNamespace() {
        return namespace;
    }

    /** sessionId前缀 */
    public String getSessionIdPreFix() {
        return sessionIdPreFix;
    }

    /** sessions: */
    public String getSessions() {
        return sessions;
    }

    /** sessions:expires: */
    public String getSessionsExpires() {
        return sessionsExpires;
    }

    /** expirations: */
    public String getSessionsExpirations() {
        return sessionsExpirations;
    }

    /** 客户端名称 */
    public String getSessionName() {
        return sessionName;
    }

    /** 客户端sessionAttr: */
    public String getSessionNameAttr() {
        return sessionNameAttr;
    }

    /** 客户端sessionAttr:backer_rolePower */
    public String getSessionNamePower() {
        return sessionNamePower;
    }

    /** 客户端id前缀 */
    public String getSessionNameId() {
        return sessionNameId;
    }

    /** session销毁时间(秒) */
    public int getSessionTimeOut() {
        return sessionTimeOut;
    }

}
