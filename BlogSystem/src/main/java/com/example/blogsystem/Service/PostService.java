package com.example.blogsystem.Service;
import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.CategoryRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

 final private PostRepository postRepository;
final private UserRepository userRepository;
final private CategoryRepository categoryRepository;
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {

        User oleUser = userRepository.findUserById(post.getUserId());
        if (oleUser ==null){
            throw new ApiException("User not found");
        }
        Category oldCategory = categoryRepository.giveMeById(post.getCategoryId());
        if (oldCategory==null){
            throw new ApiException("Category not found ");
        }
        postRepository.save(post);
    }

    public void updatePost(Integer id, Post post) {
        Post oldPost = postRepository.findUserById(id);
        if (oldPost == null) {
            throw new ApiException("Post not found");
        }
        oldPost.setContent(post.getContent());
        oldPost.setTitle(post.getTitle());
        postRepository.save(oldPost);
    }
    public void deletePost(Integer id) {
        Post oldPost = postRepository.findUserById(id);
        if (oldPost == null) {
            throw new ApiException("Post not found");
        }
        postRepository.delete(oldPost);
    }

    public Post getPostByTitle(String title) {
        Post post = postRepository.findPostByTitle(title);
        if (post == null) {
            throw new ApiException("Post not found");
        }

        return post;
    }

    public Post getPostByTitleAndContent(String title,String content){
        Post oldPost =postRepository.findPostByTitleAndContent(title,content);
        if (oldPost == null){
            throw new ApiException("title incorrect or content incorrect");
        }
        return oldPost;
    }


}

