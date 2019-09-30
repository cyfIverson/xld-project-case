package com.xld.web.resolver;

import com.xld.common.bean.BO.ResultBO;
import com.xld.common.bean.ENUM.HttpStatusEnum;
import com.xld.common.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller全局异常处理
 * @author xld
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionResolver {

    /**
     * Controller全局Exception捕获
     * @param e exception异常
     * @return 返回响应
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBO serviceCommonExceptionHandler(Exception e) {
        //对捕获的异常进行处理并打印日志之后返回json数据
        log.error(e.getMessage(),e);
        return Results.result(HttpStatusEnum.SYSTEM_ERROR);
    }
}
