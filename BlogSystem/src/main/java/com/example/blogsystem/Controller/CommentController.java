package com.example.blogsystem.Controller;
import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
final private CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllComment() {
        return ResponseEntity.status(200).body(commentService.getAllComment());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment added successfully "));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateComment(@PathVariable Integer id ,@Valid @RequestBody Comment comment , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id,comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted successfully"));
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable Integer postId) {
        return ResponseEntity.status(200).body(commentService.getAllCommentsByPostId(postId));
    }


}



