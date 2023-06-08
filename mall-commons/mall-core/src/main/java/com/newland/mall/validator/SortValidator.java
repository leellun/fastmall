package com.newland.mall.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序校验
 * @author leell
 */
public class SortValidator implements ConstraintValidator<Sort, String> {
    private List<String> valueList;

    @Override
    public void initialize(Sort sort) {
        valueList = new ArrayList<>();
        for (String val : sort.accepts()) {
            valueList.add(val.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!valueList.contains(s.toUpperCase())) {
            return false;
        }
        return true;
    }
}
