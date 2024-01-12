package com.miniassignment.userValidationAssignment.service;

import com.miniassignment.userValidationAssignment.entity.UserEntity;

import java.util.List;

// API Service interface
public interface APIServiceInterface {

//  To get random user
    public UserEntity getRandomUser();

//  Tp get list of nationality of a given user
    public List<String> getNationalityList(UserEntity user);

//  To get the gender of a given user.
    public String getUsersGender(UserEntity user);
}
