package com.umer.revision_spring_boot.model;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class User {

    private final UUID userId;

    @NotNull
    private final String firstname;

    @NotNull
    private final String lastname;

    @NotNull
    private final Gender gender;

    @NotNull
    @Max(value = 112)
    @Min(value = 0)
    private final Integer age;

    @NotNull
    @Email
    private final String email;

    public User(
            @JsonProperty("userId") UUID userId,
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("age") Integer age,
            @JsonProperty("email") String email) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    @JsonProperty("id")
    public UUID getUserId() {
        return userId;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

    public int getDateofBirth() {
        return LocalDate.now().minusYears(age).getYear();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public static User newUser(UUID userId, User user) {
        return new User(userId, user.getFirstname(), user.getLastname(), user.gender, user.getAge(), user.getEmail());
    }

    @Override
    public String toString() {
        return "User {userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
                + ", age=" + age + ", email=" + email + "}";
    }

    public enum Gender {
        MALE,
        FEMALE
    }

}
