package com.example.blogsystem.Service;
import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
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

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {
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

