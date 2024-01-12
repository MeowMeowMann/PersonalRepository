package com.miniassignment.userValidationAssignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.miniassignment.userValidationAssignment.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


//API Service Implementaion
//Contains methods to fetch random user, nationality and gender from API.
@Service
public class APIServiceImplementation implements APIServiceInterface {

    private final WebClient randomUserWebClient;
    private final WebClient userNationalityWebClient;
    private final WebClient userGenderWebClient;


    @Autowired
    private APIServiceImplementation(WebClient randomUserWebClient,WebClient userNationalityWebClient,WebClient userGenderWebClient) {
        this.randomUserWebClient = randomUserWebClient;
        this.userNationalityWebClient =userNationalityWebClient;
        this.userGenderWebClient=userGenderWebClient;
    }


//  To fetch random user from API and storing it into userEntity
//  using JsonPath and returning a userEntity(user).
    @Override
    public UserEntity getRandomUser(){


        String userDetails=this.randomUserWebClient.get().retrieve().bodyToMono(String.class).block();

        Object jsonNode = JsonPath.read(userDetails, "$");

        String name = JsonPath.read(jsonNode, "$.results[0].name.first") + " " + JsonPath.read(jsonNode, "$.results[0].name.last");
        int age = JsonPath.read(jsonNode, "$.results[0].dob.age");
        String gender = JsonPath.read(jsonNode, "$.results[0].gender");

        String s=JsonPath.read(jsonNode,"$.results[0].dob.date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        s=s.substring(0,10);
        LocalDate dob = LocalDate.parse(s, formatter);

        String nationality = JsonPath.read(jsonNode, "$.results[0].nat");
        String verificationStatus = "to be verified";
        LocalDateTime dateCreated = LocalDateTime.now();
        LocalDateTime dateModified = LocalDateTime.now();

        UserEntity user = new UserEntity(null, name, age, gender, dob, nationality, verificationStatus, dateCreated, dateModified);

        return user;

    }


//  To fetch nationality list of the user according to the user's name
//  and storing it into a list and returning it.
    @Override
    public List<String>getNationalityList(UserEntity user) {

        String[] names = user.getName().split("\\s+");
        String userFirstName=names[0];
        String nationalityDetails=this.userNationalityWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/").queryParam("name", userFirstName).build())
                .retrieve().bodyToMono(String.class).block();



        List<String> nationalityList = JsonPath.read(nationalityDetails, "$.country[*].country_id");
        return nationalityList;

    }


//  To fetch gender of the user according to the user's name
//  and storing it into a variable and returning it.
    @Override
    public String getUsersGender(UserEntity user){

        String[] names = user.getName().split("\\s+");
        String userFirstName=names[0];

        String genderDetails=this.userGenderWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/").queryParam("name", userFirstName).build())
                .retrieve().bodyToMono(String.class).block();

        String gender = JsonPath.read(genderDetails, "$.gender");
        return gender;


    }


}
