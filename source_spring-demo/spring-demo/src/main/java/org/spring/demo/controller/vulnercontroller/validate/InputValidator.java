package org.spring.demo.controller.vulnercontroller.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=MyValidator.class)
public @interface InputValidator {
    String message() default"Input InValid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
