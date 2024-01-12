package com.miniassignment.userValidationAssignment.service;

//import com.miniassignment.userValidationAssignment.entity.ResultEntity;
import com.miniassignment.userValidationAssignment.entity.ResultEntity;
import com.miniassignment.userValidationAssignment.entity.UserEntity;
import com.miniassignment.userValidationAssignment.exceptions.CustomException;

import java.util.List;

//User Service interface
public interface UserServiceInterface {

//  To fetch data from api and store them into database and return the list of users
    public List<UserEntity> users(int size) throws CustomException;

//  To return the list of sorted users from database according to the client
    public ResultEntity user(String sortType, String sortOrder, int limit, int offset);
}
