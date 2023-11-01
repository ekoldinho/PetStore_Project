package com.pojo;



public class User {

public int id;
public String username;
public String firstName;
public String lastName;
public String email;
public String password;
public String phone;
public int userStatus;

    public void setInfo(int userId, String username1, String userFirstName, String userLastName, String userEmail, String userPassword, String userPhone, int userStatus1) {

        id = userId;
        username = username1;
        firstName = userFirstName;
        lastName = userLastName;
        email = userEmail;
        password = userPassword;
        phone = userPhone;
        userStatus = userStatus1;

    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
