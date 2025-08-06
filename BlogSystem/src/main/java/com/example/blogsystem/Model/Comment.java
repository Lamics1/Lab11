package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message="userId must be not null !")
    @Column(columnDefinition = "int not null ")
    private Integer userId;

    @NotNull(message="postId must be not null !")
    @Column(columnDefinition = "int not null ")
    private Integer postId;

    @NotEmpty(message="content must be not Empty !")
    @Column(columnDefinition = "varchar(150) not null ")
    private String content;

    @Column(columnDefinition = "Date")
    private LocalDate commentDate;

    // = LocalDate.now();
}
