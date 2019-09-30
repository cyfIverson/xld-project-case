package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 字符串非空校验
 *
 * @author xld
 */
public class NotNullStringValidator extends ValidatorHandler<String> implements Validator<String> {

    /**
     * 需要被校验字符串的字段名
     */
    private String fieldName;

    public NotNullStringValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * 校验方法
     *
     * @param checkedString 需要被校验字符串
     */
    @Override
    public boolean validate(ValidatorContext context, String checkedString) {
        if (isEmpty(checkedString)) {
            context.addError(ValidationError.create(String.format("%s不能为空！", fieldName))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(checkedString));
            return false;
        }
        return true;
    }

    /** 字符串判空 */
    private static boolean isEmpty(String value) {
        if (null == value || value.isEmpty()) {
            return true;
        }
        value = value.trim();
        return "".equals(value);
    }

}
