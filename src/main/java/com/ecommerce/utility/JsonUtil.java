package com.ecommerce.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class JsonUtil {
    private static final String fileLocationTestData = "src/main/resources/testdata.json";

    public static TestData loadTestData(int testId) {
        return load(fileLocationTestData, TestData.class).get(testId);
    }

    private static <T> List<T> load(String fileLocation, Class<T> type) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = new FileInputStream(fileLocation);
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, type);
            return objectMapper.readValue(inputStream, collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
