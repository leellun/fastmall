package com.newland.mall.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author liulun
 * @date 2023/4/4 12:47
 */

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumIntegerValidator.class})
@Documented
public @interface EnumInteger {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};

    /**
     * 允许的枚举
     * @return
     */
    Class<? extends Enum<?>> enumClass();

}