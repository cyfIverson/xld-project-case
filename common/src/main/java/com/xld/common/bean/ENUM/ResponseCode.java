package com.xld.common.bean.ENUM;

/**
 * 返回码封装结果-用户非httpCode时使用
 * @author xld
 */
public enum ResponseCode {

    SERVER_ERROR(-1),
    SUCCESS(1),
    PARAMETER_NULL(2),
    PARAMETER_ERROR(3),
    SESSION_ERROR(4),
    FAIL(5),
    POWER_NO(6);

    private int code;

    private ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}