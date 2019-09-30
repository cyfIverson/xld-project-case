package com.xld.common.bean.ENUM;

/**
 * 业务码
 * @author xld
 */
public interface BusinessCode {
    /**
     * 获取业务错误码
     * @return 错误码
     */
    int getCode();

    /**
     * 获取业务错误信息
     * @return 业务错误信息
     */
    String getMessage();
}
