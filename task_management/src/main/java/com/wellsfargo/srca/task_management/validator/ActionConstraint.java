package com.wellsfargo.srca.task_management.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ActionValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
    ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionConstraint {
    String message() default "Invalid Action Selected";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] value();
}