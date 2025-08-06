package com.example.blogsystem.Controller;

import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

final private PostService postService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllPost() {
        return ResponseEntity.status(200).body(postService.getAllPost());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post added successfully "));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updatePost(@PathVariable Integer id ,@Valid @RequestBody Post post , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(id,post);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted successfully"));
    }
    @GetMapping("/get/all/{title}")
    public ResponseEntity<?> getPostByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }

    @GetMapping("/get/title-content/{title}/{content}")
    public ResponseEntity<?>getUserByUsernameAndPassword(@PathVariable String title,@PathVariable String content){
        return ResponseEntity.status(200).body(postService.getPostByTitleAndContent(title,content));
    }

}

