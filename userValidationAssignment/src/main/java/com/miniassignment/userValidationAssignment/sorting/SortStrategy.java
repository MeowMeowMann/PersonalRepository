package com.miniassignment.userValidationAssignment.sorting;

import com.miniassignment.userValidationAssignment.entity.UserEntity;

import java.util.List;


// Sorting interface
public interface SortStrategy {
    List<UserEntity> sort(List<UserEntity> users,String oe);

}
