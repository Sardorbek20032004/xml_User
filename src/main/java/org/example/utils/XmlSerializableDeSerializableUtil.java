package org.example.utils;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@UtilityClass
public class XmlSerializableDeSerializableUtil {

    XmlMapper xmlMapper = new XmlMapper();

    public static <T> ArrayList<T> read(String PATH, Class<T> clazz) {
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

    public static <T> void write(String PATH, T t) {
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            String str = xmlMapper.writeValueAsString(t);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH));
            bufferedWriter.write(str);
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
}
