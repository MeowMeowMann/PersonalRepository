package com.miniassignment.userValidationAssignment.validation;


// Validation interface
public interface ValidationInterface {

//  Validate for sortType,SortOrder,Limit and offset.
    boolean validate(String sortType,String sortOrder,String limit,String offset);

//  Validate for size
    boolean validate(String size);






}
