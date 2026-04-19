package com.microservices.learn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//Step-2: Create the validator EmployeeFieldValidator which implements ConstraintValidator interface and override
public class EmployeeFieldValidator implements ConstraintValidator<EmployeeFieldValidationAnnotation, String> {
    @Override
    public void initialize(EmployeeFieldValidationAnnotation arg0){ //name of the related annotation
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext arg1) { //validation applied on any String
        if(string == null) return true;
        if(string.split(" ").length == 2 || string.split(" ").length == 3) return true;
        else return false;
    }


}
