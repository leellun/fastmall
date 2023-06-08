package com.newland.mall.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单校验
 * @author leell
 */
public class OrderValidator implements ConstraintValidator<Order, Boolean> {
    private List<String> valueList;

    @Override
    public void initialize(Order order) {
    }

    @Override
    public boolean isValid(Boolean s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }
        return true;
    }
}
