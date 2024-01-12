package com.miniassignment.userValidationAssignment.validation;

import java.util.Arrays;

public class EnglishAlphabetValidatingImplementation implements ValidationInterface{

//    Create Private constructor
    private static EnglishAlphabetValidatingImplementation englishAlphabetValidatingImplementation;

    private EnglishAlphabetValidatingImplementation(){

    }

    public static EnglishAlphabetValidatingImplementation getValidator(){

        if(englishAlphabetValidatingImplementation==null){
            return new EnglishAlphabetValidatingImplementation();
        }
        else{
            return englishAlphabetValidatingImplementation;
        }

    }
    @Override
    public boolean validate(String sortType,String sortOrder,String limit,String offset) {
        String engAlphaRegex = "^[a-zA-Z]+$";

//      Whether string contain only english alphabets or not.
        if(sortOrder.matches(engAlphaRegex)&&sortType.matches(engAlphaRegex)){
            return true;
        }
        else{
            return false;
        }

    }


    @Override
    public boolean validate(String size) {
        return false;
    }




}
