package com.newland.mall.validator;

import com.newland.mall.enumeration.IBasicEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * value值是Integer类型的枚举校验器
 *
 * @author liulun
 * @date 2023/4/4 12:47
 */
public class EnumIntegerValidator implements ConstraintValidator<EnumInteger, Integer> {

    private Class<? extends Enum> enumClass;

    @Override
    public void initialize(EnumInteger enumIntegerValid) {
        enumClass = enumIntegerValid.enumClass();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        IBasicEnum[] enums = (IBasicEnum[]) enumClass.getEnumConstants();
        if (enums == null || enums.length == 0) {
            return false;
        }
        return enums[0].valueOfKey(value) != null;
    }
}
