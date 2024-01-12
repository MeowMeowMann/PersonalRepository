package com.miniassignment.userValidationAssignment.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Error POJO (For returning proper response in exception body)
public class ErrorEntity {

    private String message;
    private int code;
    private LocalDateTime timeStamp;
    public ErrorEntity(String message,int code,LocalDateTime timeStamp){
        this.message=message;
        this.code=code;
        this.timeStamp=timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
