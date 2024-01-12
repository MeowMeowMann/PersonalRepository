package com.miniassignment.userValidationAssignment.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

// Validation Factory to decide which sorting method to be used.
public class ValidationFactory {
    public static ValidationInterface getValidator(String type){

//      To get numeric validation method.
        if (type.equalsIgnoreCase("numeric")){
            return NumericValidatingImplementation.getValidator();
        }

//      To get English Alphabet Validation method.
        else if (type.equalsIgnoreCase("alpha")){
            return EnglishAlphabetValidatingImplementation.getValidator();
        }

        else{
            return null;
        }
    }
}
