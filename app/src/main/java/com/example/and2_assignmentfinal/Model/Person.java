package com.example.and2_assignmentfinal.Model;

public class Person {
    private String Username;
    private String Password;
    private String Fullname;

    public Person(String username, String password, String fullname) {
        Username = username;
        Password = password;
        Fullname = fullname;
    }

    public Person() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }
}
