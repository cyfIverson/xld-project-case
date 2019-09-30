package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

import java.util.regex.Pattern;

/**
 * request 参数校验器
 * @author xld
 * @description
 * @date 2018/11/14 0:17
 */
public class CommonValidator extends ValidatorHandler<String> implements Validator<String> {

    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 正则表达式
     */
    private Pattern pattern;

    /**
     * 是否判空
     */
    private boolean isNull;

    public CommonValidator(Pattern pattern) {
        this("参数错误", pattern, false);
    }

    public CommonValidator(Pattern pattern, boolean isNull) {
        this("参数错误", pattern, isNull);
    }

    public CommonValidator(String errorMsg, Pattern pattern) {
        this(errorMsg, pattern, false);
    }

    public CommonValidator(String errorMsg, Pattern pattern, boolean isNull) {
        this.errorMsg = errorMsg;
        this.pattern = pattern;
        this.isNull = isNull;
    }

    /**
     * 校验方法
     * @param value 需要被校验数据
     */
    @Override
    public boolean validate(ValidatorContext context, String value){
        return isNull
                ? PatternUtil.validateAllowEmpty(value, pattern) ? true : context(context)
                : PatternUtil.validate(value, pattern) ? true : context(context);
    }

    /**
     * 错误信息
     */
    private boolean context(ValidatorContext context) {
        context.addErrorMsg(errorMsg);
        return false;
    }

}
