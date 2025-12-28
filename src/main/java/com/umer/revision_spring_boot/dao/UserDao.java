package com.umer.revision_spring_boot.dao;
import com.umer.revision_spring_boot.model.User;
import java.util.List;
import java.util.UUID;

public interface UserDao {

    List<User> getAllUsers();

    User getUser(UUID userId);

    int updateUser(User user) ;

    int removeUser(UUID userId);

    int insertUser(User user);

    int insertUser(UUID userId, User user);

}
