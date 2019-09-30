package com.xld.common.result;

import com.alibaba.fastjson.JSONObject;
import com.xld.common.bean.BO.ResultBO;
import com.xld.common.bean.ENUM.BusinessCode;
import com.xld.common.bean.ENUM.HttpStatusHandler;
import com.xld.common.bean.ENUM.StatusCode;

/**
 * 封装ResultBO
 * @author xld
 */
public class Results {

    public Results() {}

    public static <T> ResultBO result(HttpStatusHandler handler) {
        return result(handler, "");
    }

    public static <T> ResultBO result(Integer code, String msg, T data) {
        return new ResultBO().data(data).code(code).message(msg);
    }

    public static <T> ResultBO result(HttpStatusHandler handler, String message) {
        return result(handler, message,  null);
    }

    public static <T> ResultBO result(HttpStatusHandler handler, JSONObject data) {
        return result(handler,  null, data);
    }

    public static <T> ResultBO result(HttpStatusHandler handler, String message, T data) {
        return result(handler.getCode(), message != null && !message.isEmpty() ? message : handler.getMessage(), data);
    }

    public static <T> ResultBO success(BusinessCode businessCode) {
        return result(businessCode.getCode(), businessCode.getMessage(), null);
    }

    public static <T> ResultBO success(StatusCode businessCode, T data) {
        return result(businessCode.getCode(), businessCode.getMessage(), data);
    }

    public static <T> ResultBO success(Integer code, String msg, T data) {
        return result(code,msg, data);
    }

    public static <T> ResultBO fail(StatusCode businessCode) {
        return result(businessCode.getCode(), businessCode.getMessage(),  null);
    }

    public static <T> ResultBO fail(StatusCode businessCode,T data) {
        return result(businessCode.getCode(), businessCode.getMessage(), data);
    }

    public static <T> ResultBO fail(Integer code, String msg,T data) {
        return result(code,msg,data);
    }
}
