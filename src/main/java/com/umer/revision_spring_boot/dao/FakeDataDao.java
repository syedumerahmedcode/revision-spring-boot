package com.umer.revision_spring_boot.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;

import com.umer.revision_spring_boot.model.User;
import com.umer.revision_spring_boot.model.User.Gender;

@Repository
public class FakeDataDao implements UserDao {

    private static Map<UUID, User> database;

    static {
        database = new HashMap<>();
        UUID user1Id = UUID.randomUUID();
        database.put(user1Id, new User(user1Id, "firstName1", "lastName1", Gender.MALE, 25, "firstName1@email.com"));
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<User> selectUserByUserId(UUID userId) {
        return Optional.ofNullable(database.get(userId));
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserId(), user);
        return 1;
    }

    @Override
    public int deleteUserByUserId(UUID userId) {
        database.remove(userId);
        return 1;
    }

    @Override
    public int insertUser(UUID userId, User user) {
        database.put(userId, user);
        return 1;
    }

}
