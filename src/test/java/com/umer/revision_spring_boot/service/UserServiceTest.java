/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.umer.revision_spring_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import com.umer.revision_spring_boot.dao.FakeDataDao;
import com.umer.revision_spring_boot.model.User;

/**
 *
 * @author syed-umer.ahmed
 */
public class UserServiceTest {

    @Mock
    private FakeDataDao fakeDataDao;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    void shouldGetAllUsers() {
        UUID annaUUId = UUID.randomUUID();
        User annaUser = new User(annaUUId, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        List<User> users = new ArrayList<>();
        users.add(annaUser);
        given(fakeDataDao.selectAllUsers()).willReturn(users);
        List<User> allUsers = userService.getAllUsers();
        assertThat(allUsers).hasSize(1);

    }

    @Test
    void shouldGetUser() {
        UUID annaUUId = UUID.randomUUID();
        User annaUser = new User(annaUUId, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        given(fakeDataDao.selectUserByUserId(annaUUId)).willReturn(Optional.of(annaUser));
        Optional<User> user = userService.getUser(annaUUId);
        assertThat(user.isPresent()).isTrue();
        assertThat(user.get()).isEqualToComparingFieldByField(annaUser);

    }

    @Test
    void shouldUpdateUser() {
        UUID annaUUId = UUID.randomUUID();
        User annaUser = new User(annaUUId, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        given(fakeDataDao.selectUserByUserId(annaUUId)).willReturn(Optional.of(annaUser));
        given(fakeDataDao.updateUser(annaUser)).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        int updateResult = userService.updateUser(annaUser);

        verify(fakeDataDao).selectUserByUserId(annaUUId);
        verify(fakeDataDao).updateUser(captor.capture());

        User user = captor.getValue();

        assertThat(user.getFirstname()).isEqualTo("anna");
        assertThat(user.getLastname()).isEqualTo("montana");
        assertThat(user.getGender()).isEqualTo(User.Gender.FEMALE);
        assertThat(user.getAge()).isEqualTo(30);
        assertThat(user.getEmail()).isEqualTo("anna@gmail.com");

        assertThat(updateResult).isEqualTo(1);

    }

    @Test
    void shouldRemoveUser() {
        UUID annaUUId = UUID.randomUUID();
        User annaUser = new User(annaUUId, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        given(fakeDataDao.selectUserByUserId(annaUUId)).willReturn(Optional.of(annaUser));
        given(fakeDataDao.deleteUserByUserId(annaUUId)).willReturn(1);

        int deleteResult = userService.removeUser(annaUUId);

        verify(fakeDataDao).selectUserByUserId(annaUUId);
        verify(fakeDataDao).deleteUserByUserId(annaUUId);

        assertThat(deleteResult).isEqualTo(1);

    }

    @Test
    void shouldInsertUser() {
        User annaUser = new User(null, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");

        given(fakeDataDao.insertUser(any(UUID.class), eq(annaUser))).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        int insertResult = userService.insertUser(annaUser);
        verify(fakeDataDao).insertUser(any(UUID.class), captor.capture());
        User user = captor.getValue();

        assertThat(user.getFirstname()).isEqualTo("anna");
        assertThat(user.getLastname()).isEqualTo("montana");
        assertThat(user.getGender()).isEqualTo(User.Gender.FEMALE);
        assertThat(user.getAge()).isEqualTo(30);
        assertThat(user.getEmail()).isEqualTo("anna@gmail.com");

        assertThat(insertResult).isEqualTo(1);

    }

}
