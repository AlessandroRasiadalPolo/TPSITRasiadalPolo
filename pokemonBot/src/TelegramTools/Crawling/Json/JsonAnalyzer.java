package TelegramTools.Crawling.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonAnalyzer {

    public static String extractJsonIncrementally(String scriptContent) {
        // Utilizza un'espressione regolare per estrarre il JSON in modo incrementale
        // Supponendo che il JSON sia all'interno di una variabile chiamata myJson
        String regex = "dexSettings\\s*=\\s*(\\{[^;]+\\})\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(scriptContent);

        StringBuilder jsonBuilder = new StringBuilder();

        while (matcher.find()) {
            System.out.println("Found JSON part: " + matcher.group(1));
            // Aggiungi i caratteri della variabile JSON uno alla volta
            jsonBuilder.append(matcher.group(1));
        }

        return jsonBuilder.toString();
    }

    public static JsonNode prepareJson(String json){
        try {
            // Creare un oggetto ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserializzare la stringa JSON in un oggetto JsonNode
            JsonNode jsonNode = objectMapper.readTree(json);

            return jsonNode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
