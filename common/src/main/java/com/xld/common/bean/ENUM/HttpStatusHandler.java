package com.xld.common.bean.ENUM;

/**
 * @description http status 操作接口对StatusCode接口的增强
 *              自定义的http status枚举实现该接口，可直接使用Results
 * @author xld
 */
public interface HttpStatusHandler extends StatusCode {

    /**
     * 获取http编码
     * @return
     */
    int getHttpCode();
}
