package org.example.servise;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
IOFileReaderAndWriter ioFileReaderAndWriter = new IOFileReaderAndWriter();
    private static final String PATH = "user.xml";
    Users users = new Users();

    public User add(User user) {
        users.setUsers(ioFileReaderAndWriter.read(PATH,User.class));
        if (hasUser(user)) {
            users.getUsers().add(user);
            ioFileReaderAndWriter.write(PATH,users);
            return user;
        }
        return null;
    }

    private boolean hasUser(User user) {
        for (User user1 : users.getUsers()) {
            if (user1.getUserName().equals(user.getUserName())) {
                return false;
            }
        }
        return true;
    }
    public User login(String userName,String  password){
        users.setUsers(ioFileReaderAndWriter.read(PATH, User.class));
        for (User user1: users.getUsers()){
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)){
                return user1;
            }
        }
        return null;
    }


}
