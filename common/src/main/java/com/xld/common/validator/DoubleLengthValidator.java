package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import java.util.regex.Pattern;

/**
 * Double 校验器
 */
public class DoubleLengthValidator extends ValidatorHandler<Double> implements Validator<Double> {

    /**
     * 校验规则
     */
    private Pattern pattern;

    /**
     * 错误信息
     */
    private String errorMsg;

    public DoubleLengthValidator() {
        this(12, 5);
    }
    /**
     * @param integerNumber 整数最大位数
     * @param decimalNumber 小数最大位数
     */
    public DoubleLengthValidator(int integerNumber, int decimalNumber) {
        this("参数错误", integerNumber, decimalNumber);
    }
    /**
     * @param errorMsg 错误信息
     * @param integerNumber 整数最大位数
     * @param decimalNumber 小数最大位数
     */
    public DoubleLengthValidator(String errorMsg, int integerNumber, int decimalNumber) {
        this.errorMsg = errorMsg;
        this.pattern = PatternUtil.doubleLimitPattern(integerNumber, decimalNumber);
    }


    @Override
    public boolean validate(ValidatorContext context, Double aDouble) {
        boolean isMatch = pattern.matcher(aDouble.toString()).matches();
        if (!isMatch) {
            context.addErrorMsg(errorMsg);
        }
        return isMatch;
    }
}
