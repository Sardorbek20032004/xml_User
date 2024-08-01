package org.example.servise;

import org.example.model.User;
import org.example.model.wrapper.Users;
import org.example.utils.XmlSerializableDeSerializableUtil;

public class UserService {
    private static final String PATH = "/Users/sardor/Desktop/xml_User/src/main/java/org/example/file/user.xml";
    public User add(User user) {
        Users users = new Users();
        users.setUsers(XmlSerializableDeSerializableUtil.read(PATH,User.class));
        if (hasUser(user,users)) {
            users.getUsers().add(user);
            XmlSerializableDeSerializableUtil.write(PATH,users);
            return user;
        }
        return null;
    }

    private boolean hasUser(User user,Users users) {
        for (User user1 : users.getUsers()) {
            if (user1.getUserName().equals(user.getUserName())) {
                return false;
            }
        }
        return true;
    }
    public User login(String userName,String  password){
        Users users = new Users();
        users.setUsers(XmlSerializableDeSerializableUtil.read(PATH, User.class));
        for (User user1: users.getUsers()){
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)){
                return user1;
            }
        }
        return null;
    }


}
