package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    @Query("select u FROM User u where u.id =?1")
    User giveMeById(Integer id);

    User findUserByEmail(String email);

    @Query("select u from User u where u.registrationDate < ?1")
    List<User> findAllUsersBeforeDate(LocalDate date);

    @Query("SELECT u FROM User u WHERE u.id BETWEEN :startId AND :endId")
    List<User> findUsersByIdBetween(@Param("startId") Integer startId, @Param("endId") Integer endId);



}
