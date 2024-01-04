package com.exam.digital.eexam.controllers;

import com.exam.digital.eexam.entities.User;
import com.exam.digital.eexam.services.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api(tags = "User API", description = "Operations related to managing users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "Create a User", notes = "Get details of a book by providing its ID", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created user"),
            @ApiResponse(code = 404, message = "Not able to create a user")
    })
    @PostMapping
    public User createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @ApiOperation(value = "Fetch a user by userId", notes = "Get details of a user by providing its Id", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched user"),
            @ApiResponse(code = 404, message = "Not able to create a user")
    })
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.fetchuser(userId);
    }

    @ApiOperation(value = "Delete a User", notes = "Delete a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted user"),
            @ApiResponse(code = 404, message = "Not able to create a user")
    })
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
    }

    @ApiOperation(value = "Update User", notes = "Update user details", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user"),
            @ApiResponse(code = 404, message = "Not able to update a user")
    })
    @PatchMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @ApiOperation(value = "Fetch All Users", notes = "Fetch all users details", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returns users list"),
            @ApiResponse(code = 404, message = "Not able to return users list")
    })
    @RequestMapping("/fetchAll")
    public List<User> fetchAllUsers() {
        return userService.fetchAllUsers();
    }

}
