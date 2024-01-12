package com.miniassignment.userValidationAssignment.entity;

import jakarta.persistence.*;

import java.util.List;

//Result POJO (For returning proper response from API)
//Contain LIST of USERS and PageInfo(PageEntity)
public class ResultEntity {


    private List<UserEntity> userList;

    private PageEntity pageInfo;

    public ResultEntity(List<UserEntity> userList, PageEntity pageInfo) {
        this.userList = userList;
        this.pageInfo = pageInfo;
//        this.id=id;
    }

    public List<UserEntity> getData() {
        return userList;
    }

    public void setData(UserEntity user) {
        this.userList = userList;
    }

    public PageEntity getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageEntity pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "data=" + userList +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
