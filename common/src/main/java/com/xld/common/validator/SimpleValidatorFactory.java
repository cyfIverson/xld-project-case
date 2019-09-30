package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.Validator;
import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * 简易 校验器工厂
 */
public class SimpleValidatorFactory {

    /**
     * 简易匹配规则
     */
    private static HashMap<Class, Class> validatorMap;

    static {
        validatorMap = new HashMap<>();
        validatorMap.put(String.class, StringLengthValidator.class);
        validatorMap.put(Double.class, DoubleLengthValidator.class);
        validatorMap.put(Integer.class, IntegerValidator.class);
    }

    /**
     * 获取指定的校验器
     * @param clazz 需要校验的字段的类
     * @param fieldName 需要校验的字段名称
     * @return 指定的校验器 null 无匹配的校验器
     */
    public static Validator getInstanceByType(Class clazz, String fieldName) {
        try {
            Constructor constructor = validatorMap.get(clazz).getConstructor(String.class);
            return (Validator) constructor.newInstance(fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
