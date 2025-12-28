package com.umer.revision_spring_boot.dao;

import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import com.umer.revision_spring_boot.model.User;
import com.umer.revision_spring_boot.model.User.Gender;

public class FakeDataDao implements UserDao {

    private static Map<UUID, User> database;

    static {
        database = new HashMap<>();
        UUID user1Id = UUID.randomUUID();
        database.put(user1Id, new User(user1Id, "firstName1", "lastName1", Gender.MALE, 25, "firstName1@email.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public User getUser(UUID userId) {
        return database.get(userId);
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserId(), user);
        return 1;
    }

    @Override
    public int removeUser(UUID userId) {
        database.remove(userId);
        return 1;
    }

    @Override
    public int insertUser(UUID userId, User user) {
        database.put(userId, user);
        return 1;
    }

}
