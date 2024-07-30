package org.example.servise;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;

public class IOFileReaderAndWriter {

    XmlMapper xmlMapper = new XmlMapper();

    public <T> ArrayList<T> read(String PATH, Class<T> clazz) {
        File file = new File(PATH);
        ArrayList<T> list = new ArrayList<>();
        if (file.exists()) {
            try {
                if (file.length() != 0) {
                    list = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
                }
            } catch (IOException e) {

            }
        }
        return list.size() != 0 ? list : new ArrayList<>();
    }

    public <T> void write(String PATH, ArrayList<T> list) {
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String str = xmlMapper.writeValueAsString(list);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH));
            bufferedWriter.write(str);
            bufferedWriter.close(); // Faylni yopishni unutmaslik kerak
        } catch (IOException e) {

        }
    }
}
