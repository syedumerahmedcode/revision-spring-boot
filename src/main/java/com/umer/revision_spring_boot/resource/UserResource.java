package com.umer.revision_spring_boot.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umer.revision_spring_boot.model.User;
import com.umer.revision_spring_boot.service.UserService;

import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON
    )
    public List<User> fetchUsers(@QueryParam("gender") String gender) {
        return userService.getAllUsers(Optional.ofNullable(gender));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON,
            path = "{userId}"
    )
    public ResponseEntity<?> fetchUser(@PathVariable("userId") UUID userId) {
        Optional<User> userOptional = userService.getUser(userId);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user " + userId + " is not found.");
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public ResponseEntity<Integer> insertNewUser(@RequestBody User user) {
        int result = userService.insertUser(user);
        if (result == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        if (result == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{userId}"
    )
    public ResponseEntity<Integer> removeUser(@PathVariable("userId") UUID userId
    ) {
        int result = userService.removeUser(userId);
        if (result == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
