package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5,message = "Must be Length of name more than 4 ")
    @NotEmpty(message="username must be not Empty !")
    @Column(columnDefinition = "varchar(50) unique not null ")
    private String username;

    @NotEmpty(message = "password must be not empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String password;

    @NotEmpty(message="username must be not Empty !")
    @Column(columnDefinition = "varchar(70) unique not null ")
    private String email;


   @Column(columnDefinition = "Date")
    private LocalDate registrationDate;

// = LocalDate.now();
}
