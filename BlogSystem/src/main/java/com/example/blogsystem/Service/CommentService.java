package com.example.blogsystem.Service;
import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.CommentRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

final private CommentRepository commentRepository;
final private UserRepository userRepository;
final private PostRepository postRepository;
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        User oleUser = userRepository.findUserById(comment.getUserId());
        if (oleUser ==null){
            throw new ApiException("User not found");
        }
        Post oldPost = postRepository.findUserById(comment.getPostId());
        if (oldPost == null) {
            throw new ApiException("Post not found");
        }

        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment) {
        Comment oldComment = commentRepository.giveMeById(id);
        if (oldComment == null) {
            throw new ApiException("Comment not found");
        }
        oldComment.setContent(comment.getContent());
        commentRepository.save(oldComment);
    }
    public void deleteComment(Integer id) {
        Comment oldComment = commentRepository.findUserById(id);
        if (oldComment == null) {
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(oldComment);
    }

    public List<Comment> getAllCommentsByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        if (comments == null) {
            throw new ApiException("No comments found for this post");
        }

        return comments;
    }


}