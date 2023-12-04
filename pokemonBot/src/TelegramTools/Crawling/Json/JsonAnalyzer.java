package TelegramTools.Crawling.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonAnalyzer {

    public static JsonNode prepareJson(String json){
        try {


            //Creo e ritorno il file json ottenuto dalla stringa appena ottenuta dal crawler
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(json);

            return jsonNode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
