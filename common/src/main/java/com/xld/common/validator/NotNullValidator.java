package com.xld.common.validator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

import java.util.Collection;
import java.util.Map;

/**
 * 非Null校验
 * @author xld
 */
public class NotNullValidator extends ValidatorHandler<Object> implements Validator<Object> {

    /**
     * 错误信息
     */
    private String errorMsg;

    public NotNullValidator(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 校验方法
     * @param checked 需要被校验数据
     */
    @Override
    public boolean validate(ValidatorContext context, Object checked){
        if (isEmptyObject(checked)) {
            context.addErrorMsg(errorMsg);
            return false;
        }
        return true;
    }

    private static boolean isEmptyObject(Object checked) {
        if (null == checked) {
            return true;
        }

        if (checked instanceof CharSequence) {
            if (checked instanceof String) {
                return isEmptyStr(((String) checked));
            }
            return ((CharSequence) checked).length() <= 0;
        }

        if (checked instanceof JSONObject) {
            return ((JSONObject) checked).isEmpty();
        }

        if (checked instanceof JSONArray) {
            return ((JSONArray) checked).isEmpty();
        }

        if (checked instanceof Collection) {
            return ((Collection) checked).isEmpty();
        }

        if (checked instanceof Map) {
            return ((Map) checked).isEmpty();
        }

        if (checked instanceof Object[]) {
            return ((Object[]) checked).length == 0;
        }
        return false;
    }

    private static boolean isEmptyStr(String value) {
        if (value.isEmpty()) {
            return true;
        }
        value = value.trim();
        return "".equals(value);
    }

}

