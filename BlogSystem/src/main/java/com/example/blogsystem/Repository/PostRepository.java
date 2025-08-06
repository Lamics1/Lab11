package com.example.blogsystem.Repository;
import com.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findUserById(Integer id);

    @Query("select p FROM Post p where p.title = ?1")
    Post findPostByTitle(String title);

    Post findPostByTitleAndContent(String title, String content);

}
