package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorChain;
import com.xld.common.other.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 字符串校验链
 * @author sjl
 */
public class StringValidatorChainFactor {
    /**
     * 默认校验字段名
     */
    private static final String FIELD_NAME = "该字段";
    /**
     * 是否允许为空
     * true 允许为空  false 不允许
     */
    private static final boolean IS_ALLOW_NULL = false;
    /**
     * 默认校验规则  允许中英文 数字 下划线
     */
    private static final Pattern DEFAULT = PatternUtil.CHINESE_ENGLISH;
    /**
     * 默认最小长度
     */
    private static final int MIN_LENGTH = 1;
    /**
     * 默认最大长度
     */
    private static final int MAX_LENGTH = Integer.MAX_VALUE;

    /**
     * 获取校验链
     * @param filedName
     */
    public static ValidatorChain getValidatorChain(String filedName) {
        return getValidatorChain(filedName, IS_ALLOW_NULL, DEFAULT, MIN_LENGTH, MAX_LENGTH);
    }

    /**
     * 获取校验链
     * @param filedName
     */
    public static ValidatorChain getValidatorChain(String filedName, boolean isAllowNull) {
        return getValidatorChain(filedName, isAllowNull, DEFAULT, MIN_LENGTH, MAX_LENGTH);
    }

    /**
     * 获取校验链
     * @param filedName
     */
    public static ValidatorChain getValidatorChain(String filedName, Pattern pattern) {
        return getValidatorChain(filedName, IS_ALLOW_NULL, pattern, MIN_LENGTH, MAX_LENGTH);
    }

    /**
     * 获取校验链
     * @param filedName
     */
    public static ValidatorChain getValidatorChain(String filedName, int minLength, int maxLength) {
        return getValidatorChain(filedName, IS_ALLOW_NULL, DEFAULT, minLength, maxLength);
    }

    /**
     * 获取校验链
     * @param filedName
     */
    public static ValidatorChain getValidatorChain(String filedName, Pattern pattern, int minLength, int maxLength) {
        return getValidatorChain(filedName, IS_ALLOW_NULL, pattern, minLength, maxLength);
    }

    /**
     * 获取校验链
     * @param filedName
     */
    public static ValidatorChain getValidatorChain(String filedName, boolean isAllowNull,
                                             Pattern pattern, int minLength, int maxLength) {
        if (StrUtil.isEmpty(filedName)) {
            filedName = FIELD_NAME;
        }
        ValidatorChain chain = new ValidatorChain();
        List<Validator> validators = new ArrayList<>();
        if (pattern != null) {
            validators.add(new StringRuleValidator(filedName, isAllowNull, pattern));
        }
        validators.add(new StringLengthValidator(filedName, isAllowNull, minLength, maxLength));
        chain.setValidators(validators);
        return chain;
    }

}
