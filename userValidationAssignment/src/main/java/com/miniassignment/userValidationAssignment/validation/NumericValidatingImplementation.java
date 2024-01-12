package com.miniassignment.userValidationAssignment.validation;

import org.springframework.stereotype.Service;


public class NumericValidatingImplementation implements ValidationInterface{

//    Create private constructor
    private static NumericValidatingImplementation numericValidatingImplementation;

    private NumericValidatingImplementation(){

    }

    public static NumericValidatingImplementation getValidator(){
        if(numericValidatingImplementation==null){
            return new NumericValidatingImplementation();
        }
        else{
            return numericValidatingImplementation;
        }

    }
    @Override
    public boolean validate(String sortType,String sortOrder,String limit,String offset) {
        String numbericRegex = "\\d+";

//      Whether string contains only numeric values or not
        if(limit.matches(numbericRegex)&&offset.matches(numbericRegex)){
            return  true;
        }
        else{
            return false;
        }



    }


    @Override
    public boolean validate(String size) {
        String numbericRegex = "\\d+";
        if(size.matches(numbericRegex)){
            System.out.println("num return true");
            return  true;
        }
        else{
            System.out.println("num return false");
            return false;
        }
    }




}
