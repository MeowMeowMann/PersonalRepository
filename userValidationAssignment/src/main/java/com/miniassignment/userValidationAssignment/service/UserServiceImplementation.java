package com.miniassignment.userValidationAssignment.service;

import java.util.ArrayList;
import java.util.concurrent.*;


import com.miniassignment.userValidationAssignment.dao.UserDAO;
//import com.miniassignment.userValidationAssignment.entity.ResultEntity;
import com.miniassignment.userValidationAssignment.entity.PageEntity;
import com.miniassignment.userValidationAssignment.entity.ResultEntity;
import com.miniassignment.userValidationAssignment.entity.UserEntity;
import com.miniassignment.userValidationAssignment.exceptions.CustomException;
import com.miniassignment.userValidationAssignment.sorting.AgeSort;
import com.miniassignment.userValidationAssignment.sorting.NameSort;
import com.miniassignment.userValidationAssignment.sorting.SortContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

//User Service implementation to perform user feunctions.
@Service
public class UserServiceImplementation implements UserServiceInterface {



    private LocalDateTime dateModified= LocalDateTime.now();

    @Autowired
    UserDAO userDAO;

    @Autowired
    APIServiceImplementation apiServiceImplementation;






//  Method to fetch random user , nationality list and gender and perform verification
//  and store the list of user into databse and return the list of users.
    @Override
    public List<UserEntity> users(int size) throws CustomException {
        List<UserEntity> listUserEntity = new ArrayList();


        try {

//      For loop to iterate according to the size given by client
//      Each iteration will perform operation for one user at a time.

                for(int i=0;i<size;i++){
                    UserEntity user=apiServiceImplementation.getRandomUser();

//              Parallel execution
                    ExecutorService executorService= Executors.newFixedThreadPool(2);

//              Fetching nationality List
                    Callable<List<String>> callableNatList = () -> apiServiceImplementation.getNationalityList(user);
                    Future<List<String>> tempList = executorService.submit(callableNatList);
                    List<String> nationalityList = tempList.get();

//              Fetching gender
                    Callable<String> callableGender = () -> apiServiceImplementation.getUsersGender(user);
                    Future<String> tempGender = executorService.submit(callableGender);
                    String gender = tempGender.get();

//                  If nationality and gender of user are successfully verified ,
//                  verification status will be changed to "Verified" from default "To be verified"
//                  and UpdateDateTime will also be modified .
                    if((nationalityList.contains(user.getNationality())) &&(gender.equalsIgnoreCase(user.getGender()))){

                        user.setVerificationStatus("Verified");
                        user.setDateModified(dateModified);
                    }
                    listUserEntity.add(user);
                    executorService.shutdown();


                }
                userDAO.saveAll(listUserEntity);
                return listUserEntity;




        } catch (ExecutionException | InterruptedException e) {
            throw new CustomException("Error accrued during fetching data",500);
        }


    }

// Method to fetch list of users from database according to limit and offset and perform
// Sorting according to sortType and SortOrder
// Returns a result entity containing list of user and page info.
    @Override
    public ResultEntity user(String sortType, String sortOrder, int limit, int offset) {
        PageEntity pageInfo=new PageEntity();

//      Setting values in pageInfo Entity.

        pageInfo.setTotal(userDAO.total());

        if(offset>=1){
            pageInfo.setHasPreviousPage("true");
        }
        else{
            pageInfo.setHasPreviousPage("false");
        }
        if(limit+offset<pageInfo.getTotal()){
            pageInfo.setHasNextPage("false");
        }
        else{
            pageInfo.setHasNextPage("false");
        }

        pageInfo.setTotal(userDAO.total());

//  Fetching List of users according to limti and offset
        List<UserEntity> userList=userDAO.findUsersWithLimitAndOffset(limit, offset);
        SortContext sortContext=new SortContext(new AgeSort(),"even");

//  Sorting according to the SortType and SortOrder
        if (sortType.equalsIgnoreCase("age")){
            if(sortOrder.equalsIgnoreCase("even")){
                sortContext.setSort(new AgeSort(),"even");
                userList=sortContext.sort(userList,"even");
            }
            else{
                sortContext.setSort(new AgeSort(),"odd");
                userList=sortContext.sort(userList,"odd");


            }
        }
        else{
            if(sortOrder.equalsIgnoreCase("even")){
                sortContext.setSort(new NameSort(),"even");
                userList=sortContext.sort(userList,"even");

            }
            else{
                sortContext.setSort(new NameSort(),"odd");
                userList=sortContext.sort(userList,"odd");


            }

        }

//      Initializing a new result entity containing userlist and pageinfo.
        ResultEntity resultEntity=new ResultEntity(userList,pageInfo);


        return resultEntity;
    }


}

