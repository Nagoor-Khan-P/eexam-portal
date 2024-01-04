package com.exam.digital.eexam.services;

import com.exam.digital.eexam.entities.User;
import com.exam.digital.eexam.entities.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    //Creating user
    User createUser(User user) throws Exception;
    User fetchuser(Long id);
    //Delete user by userId
    void deleteUserById(Long id);

    User updateUser(User user);

    List<User> fetchAllUsers();
}
