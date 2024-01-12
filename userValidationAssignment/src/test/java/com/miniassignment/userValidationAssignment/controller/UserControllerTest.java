package com.miniassignment.userValidationAssignment.controller;

import com.miniassignment.userValidationAssignment.entity.PageEntity;
import com.miniassignment.userValidationAssignment.entity.ResultEntity;
import com.miniassignment.userValidationAssignment.entity.UserEntity;
import com.miniassignment.userValidationAssignment.exceptions.CustomException;
import com.miniassignment.userValidationAssignment.service.UserServiceImplementation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;


    @Mock
    UserServiceImplementation userServiceImplementation;

    private MockMvc mockMvc;
    LocalDate dob = LocalDate.now();
    LocalDateTime time=LocalDateTime.now();

    UserEntity u1=new UserEntity(1L,"TestName1",69,"Female",dob,"TR","Verified", time, time);
    UserEntity u2=new UserEntity(2L,"TestNameTwo",70,"Male",dob,"BR","Verified", time, time);
    UserEntity u3=new UserEntity(3L,"TestName3",71,"Male",dob,"CR","To Be Verified", time, time);
    UserEntity u4=new UserEntity(4L,"TestNameFour",72,"Female",dob,"OR","To Be Verified", time, time);


    List<UserEntity> userList= List.of(u1,u2,u3,u4);
    int size = 4;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
//        this.mockMvc =MockMvcBuilders.webAppContextSetup(userController).build();
    }


//  POST API TEST

//  Status Tets
    @Test
    public void postStatusTest() throws Exception {
        Mockito.when(userServiceImplementation.users(size)).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/").contentType(MediaType.APPLICATION_JSON).content("4"))
                .andExpect(status().isOk());
    }

//  size of output test
    @Test
    public void postSizeTest() throws Exception{
        Mockito.when(userServiceImplementation.users(size)).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/").contentType(MediaType.APPLICATION_JSON).content("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(4)));
    }

//  Exception test based on size
    @Test
    public void postSizeExceptionTest() throws Exception{


        Throwable sizeGreater = assertThrows(CustomException.class,
                ()->{userController.user("6");} );
        Throwable sizeLesser = assertThrows(CustomException.class,
                ()->{userController.user("-1");} );


    }

//  Exception test based numeric validator
    @Test
    public void postNumericExceptionTest() throws Exception{

        Throwable sizeGreater = assertThrows(CustomException.class,
                ()->{userController.user("four");} );

    }

//  Exception test based on null value in input
    @Test
    public void postNullValueExceptionTest() throws Exception{

        Throwable sizeGreater = assertThrows(CustomException.class,
                ()->{userController.user("");} );

    }


//  Get Test
    PageEntity pageInfo=new PageEntity("false","false",4);
    ResultEntity resultEntity=new ResultEntity(userList,pageInfo);

//  Status test
    @Test
    public void getStatusTest() throws Exception {
        Mockito.when(userServiceImplementation.user("name","even",4, 0)).thenReturn(resultEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .param("sortType","name").param("sortOrder","even")
                        .param("limit","4").param("offset","0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//  Size output of result
    @Test
    public void getLimitTest() throws Exception{
        Mockito.when(userServiceImplementation.user("name","even",4, 0)).thenReturn(resultEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/")
                        .param("sortType","name").param("sortOrder","even")
                        .param("limit","4").param("offset","0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",hasSize(4)));

    }

//  Exception test based on range of limit
    @Test
    public void getLimitExceptionTest() throws Exception{

        Throwable limitGreater = assertThrows(CustomException.class,
                ()->{userController.user("name","even","6","0");} );
        Throwable limitLesser = assertThrows(CustomException.class,
                ()->{userController.user("name","even","0","0");} );

    }

//  Exception test based on lower limit of offset
    @Test
    public void getOffsetExceptionTest() throws Exception{

        Throwable limitGreater = assertThrows(CustomException.class,
                ()->{userController.user("name","even","4","-1");} );

    }

//  Exception test based on alphabet validation
    @Test
    public void getAlphabetExceptionTest() throws Exception{

        Throwable alphabetSortTypeException = assertThrows(CustomException.class,
                ()->{userController.user("name2","even","4","0");} );
        Throwable alphabetOrderTypeException = assertThrows(CustomException.class,
                ()->{userController.user("name","even2","4","0");} );


    }

//  Exception test based on numeric validation.
    @Test
    public void getNumericExceptionTest() throws Exception{

        Throwable numericLimitTypeException = assertThrows(CustomException.class,
                ()->{userController.user("name","even","four","0");} );
        Throwable numericOffsetTypeException = assertThrows(CustomException.class,
                ()->{userController.user("name","even","4","zero");} );

    }

//  Exception based on null value entered in input
    @Test
    public void getNullValueExceptionTest() throws Exception{

        Throwable numericLimitTypeException = assertThrows(CustomException.class,
                ()->{userController.user("","","","");} );


    }





}
