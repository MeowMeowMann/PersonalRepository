package com.miniassignment.userValidationAssignment.dao;

import com.miniassignment.userValidationAssignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//Repository
public interface UserDAO extends JpaRepository<UserEntity, Long > {

//    To fetch list of users according to provided limit and offset.
@Query("SELECT u FROM UserEntity u ORDER BY u.id LIMIT :limit OFFSET :offset")
    List<UserEntity> findUsersWithLimitAndOffset(@Param("limit") int limit, @Param("offset") int offset);

//    To fetch the total number of users in the database.
@Query("SELECT COUNT(u) FROM UserEntity u")
    int total();

}
