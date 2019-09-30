package com.xld.common.bean.ENUM;

/**
 * http status枚举类
 * @author xld
 */
public enum HttpStatusEnum implements HttpStatusHandler {

    SUCCESS(200, 1, "操作成功"),
    SUCCESS_POST(201, 1, "操作成功"),
    SUCCESS_DELETE(204, 1, "操作成功"),
    PARAMTER_ERROE(400, 3, "参数错误"),
    ROLE_ERROE(401, 6, "无权限访问"),
    SESSION_ERROR(403, 4, "登录过期"),
    DATA_DISABLE(410, 8, "数据被禁用"),
    DATA_LIMIT(413, 9, "数据超过限制"),
    FAIL(400, 5, "操作失败"),
    DATA_ERROR(400, 7, "未查询到数据"),
    SYSTEM_ERROR(500, -1, "系统错误"),
    SYSTEM_SHUTDOWN(501, -2, "该功能暂未开放"),
    SYSTEM_NO_POWER(511, -3, "未授权");

    private int httpCode;
    private int code;
    private String message;

    HttpStatusEnum(int httpCode, int code, String meseage) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = meseage;
    }

    @Override
    public int getHttpCode() {
        return this.httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
