package TelegramTools.Crawling;

import Entities.Pokemon;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.simple.parser.*;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
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

    private static void processJson(String filePath) {
        // Puoi implementare qui la logica per elaborare la stringa JSON

        // Scrivi il JSON formattato in un file
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void visualizeJson(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            // Se il nodo è un oggetto, scorri attraverso i suoi campi
            jsonNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();

                // Se il campo è "pokemon", verifica se il valore è un array
                if ("pokemon".equals(fieldName) && fieldValue.isArray()) {
                    for (JsonNode pokemon : fieldValue) {
                        // Stampa ogni elemento Pokemon
                        System.out.println(pokemon.get("name"));
                    }
                } else {
                    // Se il campo non è "pokemon", continua la ricerca ricorsiva
                    visualizeJson(fieldValue);
                }
            });
        } else if (jsonNode.isArray()) {
            // Se il nodo è un array, scorri attraverso i suoi elementi
            jsonNode.forEach(JsonAnalyzer::visualizeJson);
        }
    }
}
