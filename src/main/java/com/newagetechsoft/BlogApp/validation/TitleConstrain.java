package com.newagetechsoft.BlogApp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContentPostValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TitleConstrain {

    String message() default "";
    Class<?>[] groups() default {}; // Specify the validation groups here
    Class<? extends Payload>[] payload() default {};

}
