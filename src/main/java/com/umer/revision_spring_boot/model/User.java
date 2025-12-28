package com.umer.revision_spring_boot.model;

import java.util.UUID;

public class User {

    private final UUID userId;
    private final String firstname;
    private final String lastname;
    private final Gender gender;
    private final Integer age;
    private final String email;

    


    public User(UUID userId, String firstname, String lastname, Gender gender, Integer age, String email) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    


    public UUID getUserId() {
        return userId;
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

    


    @Override
    public String toString() {
        return "User {userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender
                + ", age=" + age + ", email=" + email + "}";
    }




    enum Gender {
        MALE,
        FEMALE
    }



}
