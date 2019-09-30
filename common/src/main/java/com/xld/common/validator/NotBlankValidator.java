package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 非空检验
 *
 * @author shenwei
 */
public class NotBlankValidator extends ValidatorHandler<CharSequence> implements Validator<CharSequence> {

    /**
     * 错误信息
     */
    private String errorMsg;

    public NotBlankValidator(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 校验方法
     * @param checked 需要被校验数据
     */
    @Override
    public boolean validate(ValidatorContext context, CharSequence checked){
        if ( checked == null ) {
            context.addErrorMsg(errorMsg);
            return false;
        }

        if (checked.toString().trim().length() == 0){
            context.addErrorMsg(errorMsg);
            return false;
        }
        return true;
    }
}