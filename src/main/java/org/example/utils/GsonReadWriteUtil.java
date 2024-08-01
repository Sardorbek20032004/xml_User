package org.example.utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class GsonReadWriteUtil {
    ObjectMapper objectMapper = new ObjectMapper();
    public static <T> void writeToFile(List<T> list, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), list);
        } catch (IOException e) {
        }
    }

    public static <T> ArrayList<T> readFromFile(String filePath, TypeReference<ArrayList<T>> typeReference ) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectMapper.readValue(new File(filePath), typeReference);
        } catch (IOException e) {
            return null;
        }
    }

}
