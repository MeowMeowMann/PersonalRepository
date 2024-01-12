package com.miniassignment.userValidationAssignment.exceptions;


//Custom exception class
//For validation
public class CustomException extends Exception{

    private int code;

    public CustomException(String message,int code){
        super(message);
        this.code=code;
    }

    public int getCode() {

        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
