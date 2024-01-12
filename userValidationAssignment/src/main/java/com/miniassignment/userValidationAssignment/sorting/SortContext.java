package com.miniassignment.userValidationAssignment.sorting;

import com.miniassignment.userValidationAssignment.entity.UserEntity;

import java.util.List;


//Sorting context for deciding which sort object to init. dynamically.
public class SortContext {

    private SortStrategy sortStrategy;
    private String oe;

    public SortContext(SortStrategy sortStrategy,String oe){
        this.sortStrategy=sortStrategy;
        this.oe=oe;
    }

    public List<UserEntity> sort(List<UserEntity> user,String oe){
        return sortStrategy.sort(user,oe);
    }

    public void setSort(SortStrategy sortStrategy,String oe){
        this.sortStrategy=sortStrategy;
        this.oe=oe;
    }
}
