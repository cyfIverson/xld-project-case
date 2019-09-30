package com.xld.common.bean.BO;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 结果返回封装实体
 * @author xld
 * @param <T>
 */
public class ResultBO<T> implements Serializable {

    /** todo 这样暂时引用不到值 response为null */
    @Autowired
    HttpServletResponse response;

    /** 业务状态（编码）*/
    private Integer code;
    /** 反馈信息 */
    private String message;
    /** 数据 */
    private T data;

    /** 默认构造函数 */
    public ResultBO() {}

    public ResultBO<T> data(T data) {
        this.setData(data);
        return this;
    }

    public ResultBO<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public ResultBO<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public ResultBO<T> httpCode(Integer httpCode) {
        System.out.println(response);
        if (null != response){
            response.setStatus(httpCode);
        }
        return this;
    }

    /**
     * 业务状态（编码）
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * 业务（编码）
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

   /**
     * 反馈信息
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 反馈信息
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回数据
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * 返回数据
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}