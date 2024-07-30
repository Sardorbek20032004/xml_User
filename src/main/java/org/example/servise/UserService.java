package org.example.servise;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final String PATH = "user.xml";
    Users users = new Users();

    public User add(User user) {
        read();
        if (hasUser(user)) {
            users.getUsers().add(user);
            write();
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

    private void read() {
        File file = new File(PATH);
        if (file.exists()) {
            XmlMapper xmlMapper = new XmlMapper();
            try {
                users.setUsers(xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String str = xmlMapper.writeValueAsString(users);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH));
            bufferedWriter.write(str);
            bufferedWriter.close(); // Faylni yopishni unutmaslik kerak
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
