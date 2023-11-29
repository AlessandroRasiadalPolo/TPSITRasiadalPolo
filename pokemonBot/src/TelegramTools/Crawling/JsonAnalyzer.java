package TelegramTools.Crawling;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonAnalyzer {


    public  static void readJsonLineByLine(String scriptContent, String outputFilePath) {
        // Usa un BufferedReader per leggere il contenuto dello script riga per riga
        try (BufferedReader reader = new BufferedReader(new StringReader(scriptContent))) {
            String line;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                while ((line = reader.readLine()) != null) {
                    // Processa la riga JSON
                    writer.write(line);
                    writer.newLine(); // Aggiungi un ritorno a capo dopo ogni riga
                }
                //Dato che il file json risulta incompleto e devo mettere manaulmente le parentesi necessarie
                writer.write("]}]]}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private static void processJson(String json) {
        // Puoi implementare qui la logica per elaborare la stringa JSON

        // Scrivi il JSON formattato in un file
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
