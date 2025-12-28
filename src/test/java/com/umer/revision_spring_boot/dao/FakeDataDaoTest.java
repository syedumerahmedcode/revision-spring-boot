/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.umer.revision_spring_boot.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.umer.revision_spring_boot.model.User;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatTemporal;

/**
 *
 * @author syed-umer.ahmed
 */
public class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;
    private Optional<User> selectUserByUserId;

    @BeforeEach
    public void setUp() {
        fakeDataDao = new FakeDataDao();

    }

    /**
     * Test of selectAllUsers method, of class FakeDataDao.
     */
    @Test
    public void testShouldSelectAllUsers() {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);
        User user = users.get(0);
        assertThat(user.getAge()).isEqualTo(25);
        assertThat(user.getFirstname()).isEqualTo("firstName1");
        assertThat(user.getLastname()).isEqualTo("lastName1");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("firstName1@email.com");
    }

    /**
     * Test of selectUserByUserId method, of class FakeDataDao.
     */
    @Test
    public void testShouldSelectUserByUserId() {
        UUID annaUUId = UUID.randomUUID();
        User annaUser = new User(annaUUId, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        fakeDataDao.insertUser(annaUUId, annaUser);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
        Optional<User> annaOptional = fakeDataDao.selectUserByUserId(annaUUId);
        assertThat(annaOptional.isPresent()).isTrue();
        assertThat(annaOptional.get()).isEqualToComparingFieldByField(annaUser);
    }

    @Test
    public void testShouldNotSelectUserByRandomUserId() {
        UUID randomUserId = UUID.randomUUID();
        Optional<User> userOptional = fakeDataDao.selectUserByUserId(randomUserId);
        assertThat(userOptional.isPresent()).isFalse();
    }

    /**
     * Test of updateUser method, of class FakeDataDao.
     */
    @Test
    public void shouldUpdateUser() {
        UUID joeUserId = fakeDataDao.selectAllUsers().get(0).getUserId();
        User newUser = new User(joeUserId, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        fakeDataDao.updateUser(newUser);
        Optional<User> user = fakeDataDao.selectUserByUserId(joeUserId);
        assertThat(user.isPresent()).isTrue();
        assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
        assertThat(user.get()).isEqualToComparingFieldByField(newUser);

    }

    /**
     * Test of deleteUserByUserId method, of class FakeDataDao.
     */
    @Test
    public void testDeleteUserByUserId() {
        UUID joeUserId = fakeDataDao.selectAllUsers().get(0).getUserId();
        fakeDataDao.deleteUserByUserId(joeUserId);
        assertThat(fakeDataDao.selectUserByUserId(joeUserId).isPresent()).isFalse();
        assertThat(fakeDataDao.selectAllUsers()).isEmpty();
    }

    /**
     * Test of insertUser method, of class FakeDataDao.
     */
    @Test
    public void testInsertUser() {
        UUID randomUserId = UUID.randomUUID();
        User newUser = new User(randomUserId, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        fakeDataDao.insertUser(randomUserId, newUser);
        List<User> selectAllUsers = fakeDataDao.selectAllUsers();
        assertThat(selectAllUsers).hasSize(2);

    }

}
