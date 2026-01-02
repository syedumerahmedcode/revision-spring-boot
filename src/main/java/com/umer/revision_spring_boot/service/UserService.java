package com.umer.revision_spring_boot.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umer.revision_spring_boot.dao.UserDao;
import com.umer.revision_spring_boot.model.User;

@Service
public class UserService {

    private UserDao userDao;
    private Optional<User> user2;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers(Optional<String> gender) {
        List<User> users = userDao.selectAllUsers();
        if (!gender.isPresent()) {
            return users;
        }
        try {
            // Gender theGender = Gender.valueOf(gender.get());
            return users.stream()
                    .filter((var user) -> user.getGender().equals(gender.get()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException("Invalid gender", e);
        }
    }

    public Optional<User> getUser(UUID userId) {
        return userDao
                .selectUserByUserId(userId);
    }

    public int updateUser(User user) {
        Optional<User> optionalUser = getUser(user.getUserId());
        if (optionalUser.isPresent()) {
            return userDao.updateUser(user);
        }
        return -1;
    }

    public int removeUser(UUID userId) {
        Optional<User> optionalUser = getUser(userId);
        if (optionalUser.isPresent()) {
            return userDao.deleteUserByUserId(userId);
        }
        return -1;
    }

    public int insertUser(User user) {
        UUID uuid = UUID.randomUUID();
        return userDao.insertUser(uuid, User.newUser(uuid, user));
    }

}
