package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName("test");
        user.setLastName("test");
        userRepository3.save(user);
        return user;

    }

    public void deleteUser(int userId){
        Optional<User> optionalUser=userRepository3.findById(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            user.getBlogList().clear();
        }
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        Optional<User> userOp = userRepository3.findById(id);
        if(userOp.isPresent()){
            User user = userOp.get();
            user.setPassword(password);
            userRepository3.save(user);
            return user;
        }
        return null;
    }
}
