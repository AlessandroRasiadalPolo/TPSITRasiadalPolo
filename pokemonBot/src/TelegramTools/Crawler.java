package TelegramTools;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Crawler {


    /*
    I link che sto cercando di analizzare si trovano all'interno di funzioni javascript Onclick, quindi devo ricavare glu url dalla funzione
     */



    public static void crawl(String url, ArrayList<String> urlVisited){

        Document doc = null;
        doc = request(url, urlVisited);
        if(doc != null) {
            //Prendo gli elementi all'interno dei tag ul
            Elements elements = doc.select("ul");

            //Scorro gli ul
            for(Element link : elements){
                //Seleziono i li
                Elements li = link.select("a[href]");
                //Per ogni li prendo i gli url
                for(Element element : li)
                    crawlPokemon(element.absUrl("href"), new ArrayList<String>());
            }

        }
    }

        private static void crawlPokemon(String pokemonUrl, ArrayList<String> strings){
            Document pokemonDoc = request(pokemonUrl, strings);
            //Il documento analizzato è un .json, quindi posso gestirlo come tale
            if (pokemonDoc != null) {
                var scripts = pokemonDoc.getElementsByTag("script");
                for (Element script : scripts) {
                    // Ottenere il contenuto dello script
                    String scriptContent = script.data();

                    // Processa la stringa JSON
                    String scriptToRead = extractJsonIncrementally(scriptContent);
                    if(!scriptToRead.isEmpty())
                        readJsonLineByLine(scriptToRead, "src/Files/pokemon.json");

                    }
                }
            }

    private static void readJsonLineByLine(String scriptContent, String outputFilePath) {
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

    private static String extractJsonIncrementally(String scriptContent) {
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




    private static Document request(String url, ArrayList<String> urlvisited){

        try{
            Connection conn = Jsoup.connect(url);
            Document doc = conn.get();

            if(conn.response().statusCode() == 200){
                System.out.println("link: " + url);
                System.out.println(doc.title());
                urlvisited.add(url);

                return doc;
            }
            return null;

        }catch (IOException e){
            return null;
        }

    }



}
