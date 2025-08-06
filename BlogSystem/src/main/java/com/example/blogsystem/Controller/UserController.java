package com.example.blogsystem.Controller;
import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    final private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully "));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateUser(@PathVariable Integer id ,@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
    }

    @GetMapping("get-user-by-email/{email}")
    public ResponseEntity<?>getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("/get/registration-date/{id}")
    public ResponseEntity<?> getRegistrationDate(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(userService.getRegistrationDate(id));
    }

    @GetMapping("/get/before/{date}")
    public ResponseEntity<?> getUsersBeforeDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(200).body(userService.getAllUsersBeforeDate(date));
    }

    @GetMapping("/get/users/between/{startId}/{endId}")
    public ResponseEntity<?> getUsersByIdRange(@PathVariable Integer startId, @PathVariable Integer endId) {
        return ResponseEntity.ok(userService.getUsersByIdRange(startId, endId));
    }




}
