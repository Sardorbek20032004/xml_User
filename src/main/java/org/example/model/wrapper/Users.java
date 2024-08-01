package org.example.model.wrapper;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "users")
public class Users  {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "user")
    private ArrayList<User> users = new ArrayList<>();
//    IOFileReaderAndWriter ioFileReaderAndWriter = new IOFileReaderAndWriter();
//    private static final String PATH = "user.xml";
//
//    public void read(){
//        setUsers(ioFileReaderAndWriter.read(PATH,User.class));
//    }
//    public void write(){
//        ioFileReaderAndWriter.write(PATH,getUsers());
//    }

}
