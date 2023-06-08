package com.newland.mall.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 排序注解
 * @author leell
 */
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = OrderValidator.class)
public @interface Order {
    String message() default "排序类型不支持";

    boolean[] accepts() default {false, true};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
