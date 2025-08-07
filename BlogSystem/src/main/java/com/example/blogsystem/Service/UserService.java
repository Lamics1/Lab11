package com.example.blogsystem.Service;
import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    final private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null) {
            throw new ApiException("User not found");
        }
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setUsername(user.getUsername());
        oldUser.setRegistrationDate(user.getRegistrationDate());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id) {
        User oldUser = userRepository.giveMeById(id);
        if (oldUser == null) {
            throw new ApiException("user not found");
        }
        userRepository.delete(oldUser);
    }

    public User getUserByEmail(String email){

        User oldUser = userRepository.findUserByEmail(email);
        if (oldUser == null){
            throw new ApiException("User not found");

        }
        return oldUser;
    }

    public User getRegistrationDate(Integer userId) {

        User olduser = userRepository.findUserById(userId);
        if (olduser == null) {
            throw new ApiException("User not found");
        }
        return olduser;
    }

    public List<User> getAllUsersBeforeDate(LocalDate date) {
        return userRepository.findAllUsersBeforeDate(date);
    }

    public List<User> getUsersByIdRange(Integer startId, Integer endId) {
        return userRepository.findUsersByIdBetween(startId, endId);
    }



}
