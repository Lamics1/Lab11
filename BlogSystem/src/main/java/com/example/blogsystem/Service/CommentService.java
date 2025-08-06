package com.example.blogsystem.Service;
import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

final private CommentRepository commentRepository;

    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
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