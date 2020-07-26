package com.dinhpu.m4casestudy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy= ValidPhoneConstraintValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    public String[] value() default {"0"};

    String message() default "Phone must start with 0 or +84";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};
}
