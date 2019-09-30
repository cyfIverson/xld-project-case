package com.xld.common.validator;

import com.baidu.unbiz.fluentvalidator.*;

public class SimpleDTOValidatorFactor<T> extends ValidatorHandler<T> implements Validator<T> {

    @Override
    public boolean validate(ValidatorContext context, T t) {
        Result validatorResult = FluentValidator.checkAll().
                doValidate().result(ResultCollectors.toSimple());
        t.getClass().getDeclaredFields();
        return super.validate(context, t);
    }
}
