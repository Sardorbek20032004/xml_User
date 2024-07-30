package org.example;

import org.example.model.User;
import org.example.servise.UserService;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        UserService userService = new UserService();
        userService.add(new User("Sardor","sardor0304", "123", 12));
        userService.add(new User("Abdulaziz","abd123", "123", 12));
        userService.add(new User("Aziz","aziz123", "123", 12));
    }
}