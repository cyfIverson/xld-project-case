package com.xld.common.bean.ENUM;

/**
 * 状态码
 * @author xld
 */
public interface StatusCode {
    /**
     * 获取状态错误码
     * @return 错误码
     */
    int getCode();

    /**
     * 获取默认错误信息
     * @return 默认错误信息
     */
    String getMessage();
}
