package com.miniassignment.userValidationAssignment.controller;

import com.miniassignment.userValidationAssignment.entity.ResultEntity;
import com.miniassignment.userValidationAssignment.entity.UserEntity;
import com.miniassignment.userValidationAssignment.exceptions.CustomException;
import com.miniassignment.userValidationAssignment.service.UserServiceImplementation;
import com.miniassignment.userValidationAssignment.validation.ValidationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//User Controller
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

// Post Mapping for POST API
    @PostMapping("/")
    public List<UserEntity> user(@RequestBody String size) throws CustomException {
        boolean numericValidator=ValidationFactory.getValidator("numeric").validate(size);

//      Validation to check numeric value.
        if((numericValidator)){

//      Checking whether size is within given limits
            if ((0<=Integer.parseInt(size)&&Integer.parseInt(size)<6)){
                return userServiceImplementation.users(Integer.parseInt(size));
            }
            else throw new CustomException("Value entered in size must be between 1 and 5",400);
        }
        else throw new CustomException("Value inserted in size is wrong type",400);
    }


//  Get Mapping for GET API
    @GetMapping("/")
    public ResultEntity user(@RequestParam String sortType, @RequestParam String sortOrder, @RequestParam String limit, @RequestParam String offset) throws CustomException {
        boolean alphaValidator=ValidationFactory.getValidator("alpha").validate(sortType,sortOrder,limit,offset);
        boolean numericValidator=ValidationFactory.getValidator("numeric").validate(sortType,sortOrder,limit,offset);

//      Validation to check numeric and English Alphabet values.
        if(alphaValidator&&numericValidator){

//          Checking whether limit and offset lie between acceptable range.
            if((0<Integer.parseInt(limit)&&Integer.parseInt(limit)<6)&&(0<=Integer.parseInt(offset))){

//              Checking whether SortType and SortOrder have acceptable keywords in the string.
                if(((sortOrder.equalsIgnoreCase("even") ||sortOrder.equalsIgnoreCase("odd"))&&(sortType.equalsIgnoreCase("age")||sortType.equalsIgnoreCase("name")))){
                    return userServiceImplementation.user(sortType,sortOrder, Integer.parseInt(limit), Integer.parseInt(offset));
                }
                else throw new CustomException("Value inserted in SortType must be Age/Name and SortOrder must be Odd/Even",400);
            }
            else throw new CustomException("Value inserted in limit must be between 1 and 5 and offset must be 0 or a positive number",400);
        }
        else throw new CustomException("Wrong value inserted in SortType,SortOrder ,limit or offset", 400);

    }
}
