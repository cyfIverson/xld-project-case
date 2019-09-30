package com.xld.web.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xld.common.bean.BO.ResultBO;
import com.xld.common.other.StrUtil;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 1、http输出消息转换类，只对ResultBO进行操作
 * 2、对fastJson的时间戳格式化
 * @author xld
 */
public class ResultMessageConverter extends AbstractHttpMessageConverter<Object> {

    public ResultMessageConverter() {
        super(Charset.forName("utf-8"),new MediaType("application","json"));
    }

    /**
     * Indicates whether the given class is supported by this converter.
     *
     * @param clazz the class to test for support
     * @return {@code true} if supported; {@code false} otherwise
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return ResultBO.class.isAssignableFrom(clazz);
    }

    /**
     * Abstract template method that reads the actual object. Invoked from {@link #read}.
     *
     * @param clazz        the type of object to return
     * @param inputMessage the HTTP input message to read from
     * @return the converted object
     * @throws IOException                     in case of I/O errors
     * @throws HttpMessageNotReadableException in case of conversion errors
     */
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return inputMessage;
    }

    /**
     * Abstract template method that writes the actual body. Invoked from {@link #write}.
     *
     * @param t             the object to write to the output message
     * @param outputMessage the HTTP output message to write to
     * @throws IOException                     in case of I/O errors
     * @throws HttpMessageNotWritableException in case of conversion errors
     */
    @Override
    protected void writeInternal(Object t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (t == null) {
            return;
        }

        if (!(t instanceof ResultBO)){
            outputMessage.getBody().write(JSON.toJSONString(t, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue).getBytes("UTF-8"));
            return;
        }
        ResultBO resultBO = (ResultBO)t;
        if (StrUtil.isNotEmpty(resultBO.getMessage())) {
            String message = resultBO.getMessage().replaceAll("\\[","");
            message = message.replaceAll("]","");
            resultBO.setMessage(message);
        }
        if (resultBO.getCode() != 1 || resultBO.getData() == null) {
            outputMessage.getBody().write(JSON.toJSONString(resultBO, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue).getBytes("UTF-8"));
            return;
        }
        outputMessage.getBody().write(JSON.toJSONString(resultBO, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue).getBytes("UTF-8"));
    }
}
