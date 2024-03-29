package com.xld.common.http.helper;

/**
 * 自定义返回数据类型
 * @author xld
 */
public class ResponseAndStatus<T> {
    /** 请求返回状态 **/
    private Status status;
    /** 请求返回数据对象 **/
    private T responseData;

    public ResponseAndStatus(Status status, T responseData) {
        this.status = status;
        this.responseData = responseData;
    }

    public Status getStatus() {
        return status;
    }

    public T getResponseData() {
        return responseData;
    }

    /**
     * 判断当前请求是否成功
     * @return true：请求成功； false：请求失败
     */
    public boolean isSuccess(){
        return this.status == Status.SUCCESS;
    }
    
    @Override
    public String toString() {
        return "ResponseAndStatus{" +
                "status=" + status +
                ", responseData=" + responseData +
                '}';
    }
}
