package com.microservices.learn.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//Step-1: Create annotation EmployeeFieldValidationAnnotation
@Documented
//Step-3: Link Validator with annotation
@Constraint(validatedBy = EmployeeFieldValidator.class)
//Describes the placement of the annotation whether it can come at instance variable or method or both
@Target({ElementType.FIELD})
//Describes the retention policy of the annotation whether it should be class, runtime, or source.
//source means retention policy is applicable on source code but will be discarded on compile time.
@Retention(RetentionPolicy.RUNTIME)
public @interface EmployeeFieldValidationAnnotation {
    String message() default "Value should have 2 or 3 words"; //default message to display on validation fails...
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
