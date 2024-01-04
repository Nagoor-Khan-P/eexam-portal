package com.exam.digital.eexam.services.impl;

import com.exam.digital.eexam.entities.Role;
import com.exam.digital.eexam.entities.User;
import com.exam.digital.eexam.entities.UserRole;
import com.exam.digital.eexam.repos.RoleRepository;
import com.exam.digital.eexam.repos.UserRepository;
import com.exam.digital.eexam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user) throws Exception {
        User currentUser = userRepository.findByUserName(user.getUserName());
        if(currentUser != null) {
            throw new Exception("User already present");
        } else {
            // add roles
            Set<UserRole> userRoleSet = new HashSet<>();
            Role role = new Role();
            role.setRoleId(1L);
            role.setRole("NORMAL");

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleSet.add(userRole);

            for (UserRole ur : userRoleSet) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoleSet);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User fetchuser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }


}
