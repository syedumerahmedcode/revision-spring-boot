package com.umer.revision_spring_boot.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umer.revision_spring_boot.model.User;
import com.umer.revision_spring_boot.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<User> fetchUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{userId}"
    )
    public User fetchUser(@PathVariable("userId") UUID userId) {
        return userService.getUser(userId).get();
    }

}
