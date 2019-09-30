package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

import java.util.regex.Pattern;

/**
 * Double 参数校验器
 * @author xld
 * @description
 * @date 2018/11/14 0:17
 */
public class DoubleValidator extends ValidatorHandler<String> implements Validator<String> {

    /** Double 正数限制整数12位数、小数5位 */
    private static Pattern DOUBLE_LIMIT = Pattern.compile("[0-9]{0,12}.?[0-9]{0,5}");

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 是否判空
     */
    private boolean isNull;

    public DoubleValidator() {
        this("参数错误", false);
    }

    public DoubleValidator(boolean isNull) {
        this("参数错误", isNull);
    }

    public DoubleValidator(String errorMsg) {
        this(errorMsg, false);
    }

    public DoubleValidator(String errorMsg, boolean isNull) {
        this.errorMsg = errorMsg;
        this.isNull = isNull;
    }

    /**
     * 校验方法
     * @param value 需要被校验数据
     */
    @Override
    public boolean validate(ValidatorContext context, String value){
        return isNull
                ? PatternUtil.validateAllowEmpty(value, DOUBLE_LIMIT) ? true : context(context)
                : PatternUtil.validate(value, DOUBLE_LIMIT) ? true : context(context);
    }

    /**
     * 错误信息
     */
    private boolean context(ValidatorContext context) {
        context.addErrorMsg(errorMsg);
        return false;
    }

}
