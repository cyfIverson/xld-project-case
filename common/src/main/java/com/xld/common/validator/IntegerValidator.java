package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

import java.util.regex.Pattern;

/**
 * Integer 参数校验器
 * @author xld
 * @description
 * @date 2018/11/14 0:17
 */
public class IntegerValidator extends ValidatorHandler<Integer> implements Validator<Integer> {



    /** Integer 正数，限制10位数 */
    private static Pattern INTEGER_LIMIT = Pattern.compile("^[\\+]?[\\d]{0,10}$");

    /**
     * 字段名称
     */
    private String fileName;
    /**
     * 是否允许为空
     * true 允许为空 false 不允许为空
     */
    private boolean isNull;

    public IntegerValidator() {
        this(false);
    }

    public IntegerValidator(boolean isNull) {
        this("参数", isNull);
    }

    public IntegerValidator(String fileName) {
        this(fileName, false);
    }

    public IntegerValidator(String fileName, boolean isNull) {
        this.fileName = fileName;
        this.isNull = isNull;
    }


    /**
     * 校验方法
     * @param value 需要被校验数据
     */
    @Override
    public boolean validate(ValidatorContext context, Integer value){
        return isNull
                ? PatternUtil.validateAllowEmpty(value.toString(), INTEGER_LIMIT) ? true : context(context)
                : PatternUtil.validate(value.toString(), INTEGER_LIMIT) ? true : context(context);
    }

    /**
     * 错误信息
     */
    private boolean context(ValidatorContext context) {
        context.addErrorMsg(String.format("%s数据错误", fileName));
        return false;
    }

}
