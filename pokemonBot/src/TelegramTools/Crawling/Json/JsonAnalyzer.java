package TelegramTools.Crawling.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonAnalyzer {

    public static String extractJsonIncrementally(String scriptContent) {
        //Tramite questo procedimento isolo SOLO la variabile dexSettings all'interno del tag script
        String regex = "dexSettings\\s*=\\s*(\\{[^;]+\\})\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(scriptContent);

        StringBuilder jsonBuilder = new StringBuilder();
        //Comincio a creare la stringa del risultato
        while (matcher.find()) {
            System.out.println("Found JSON part: " + matcher.group(1));
            // Aggiungi i caratteri della variabile JSON uno alla volta
            jsonBuilder.append(matcher.group(1));
        }

        return jsonBuilder.toString();
    }

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
