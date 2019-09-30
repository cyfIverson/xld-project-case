package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.xld.common.other.StrUtil;
import java.util.regex.Pattern;

/**
 * 字符串规则校验
 */
public class StringRuleValidator  extends ValidatorHandler<String> implements Validator<String> {

    /**
     * 校验字段名
     */
    private String fieldName;
    /**
     *  是否允许为空
     *  如果为空 则不做规则校验
     *  true 允许为空  false 不允许
     */
    private boolean isAllowNull;
    /**
     * 校验规则
     */
    private Pattern pattern;

    public StringRuleValidator(String fieldName) {
        this(fieldName, false, PatternUtil.CHINESE_ENGLISH);
    }
    public StringRuleValidator(String fieldName, Pattern pattern) {
        this(fieldName, false, pattern);
    }
    public StringRuleValidator(String fieldName,  boolean isAllowNull, Pattern pattern) {
        this.fieldName = fieldName;
        this.isAllowNull = isAllowNull;
        this.pattern = pattern;
    }


    @Override
    public boolean validate(ValidatorContext context, String str) {
        boolean flag = isAllowNull;
        if (StrUtil.isNotEmpty(str)) {
            flag = pattern.matcher(str).matches();
            if (!flag) {
                context.addError(ValidationError.create(String.format("%s不符合要求！", fieldName))
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
