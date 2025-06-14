package org.example.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
       String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\main\\java\\org\\example\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
       ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

//      //String to HashMap data

    }
}
