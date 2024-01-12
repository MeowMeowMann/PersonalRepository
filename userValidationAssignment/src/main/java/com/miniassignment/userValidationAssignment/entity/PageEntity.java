package com.miniassignment.userValidationAssignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// Page POJO
// Contain values for pageInfo.
public class PageEntity {


    private String hasNextPage;

    private String hasPreviousPage;
    private int total;

    public PageEntity(String hasNextPage, String hasPreviousPage, int total) {
        this.hasNextPage = hasNextPage;
        this.hasPreviousPage = hasPreviousPage;
        this.total = total;
    }

    public PageEntity() {

    }

    public String getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(String hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public String getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(String hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageEntity{" +
                "hasNextPage='" + hasNextPage + '\'' +
                ", hasPreviousPage='" + hasPreviousPage + '\'' +
                ", total=" + total +
                '}';
    }
}
