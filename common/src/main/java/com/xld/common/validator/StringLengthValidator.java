package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.xld.common.other.StrUtil;

/**
 * 字符串长度校验器
 * @author xld
 */
public class StringLengthValidator extends ValidatorHandler<String> implements Validator<String> {
    /**
     * 校验字段名
     */
    private String fieldName;
    /**
     * 最小长度
     */
    private int minLength;
    /**
     * 最大长度
     */
    private int maxLength;
    /**
     *  是否允许为空
     *  如果为空 则不做长度校验
     *  true 允许为空  false 不允许
     */
    private boolean isAllowNull;

    public StringLengthValidator(String fieldName) {
        this(fieldName, false, 1, Integer.MAX_VALUE);
    }
    public StringLengthValidator(String fieldName, int minLength, int maxLength) {
        this(fieldName, false, minLength, maxLength);
    }
    public StringLengthValidator(String fieldName, boolean isAllowNull, int minLength, int maxLength) {
        this.fieldName = fieldName;
        this.isAllowNull = isAllowNull;
        if (minLength < 0) {
            minLength = 0;
        }
        if (maxLength <= 0) {
            maxLength = Integer.MAX_VALUE;
        }
        if (minLength > maxLength) {
            maxLength = Integer.MAX_VALUE;
        }
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(ValidatorContext context, String str) {
        boolean flag = isAllowNull;
        str = str.trim();
        if (StrUtil.isNotEmpty(str)) {
            flag = str.length() >= minLength && str.length() <= maxLength;
            if (!flag) {
                context.addError(ValidationError.create(String.format("%s长度不符合要求！", fieldName))
                        .setErrorCode(-1)
                        .setField(fieldName)
                        .setInvalidValue(str));
            }
        } else if (!isAllowNull) {
            context.addError(ValidationError.create(String.format("%s不能为空！", fieldName))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(str));
        }
        return flag;
    }
}
