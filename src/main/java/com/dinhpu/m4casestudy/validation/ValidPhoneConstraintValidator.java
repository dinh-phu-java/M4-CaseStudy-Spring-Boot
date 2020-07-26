package com.dinhpu.m4casestudy.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPhoneConstraintValidator implements ConstraintValidator<ValidPhoneNumber,String> {

    private String[] preFix;
    @Override
    public void initialize(ValidPhoneNumber thePhoneNumber) {
        preFix=thePhoneNumber.value();
    }


    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        boolean result=false;

        if (phoneNumber!=null){
            for (String tempPreFix : preFix ){
                result=phoneNumber.startsWith(tempPreFix);
                if (result){
                    break;
                }
            }

            return result;
        }else{
            result=true;
        }
        return result;
    }
}
