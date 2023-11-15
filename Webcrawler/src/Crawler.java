import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Crawler {

    //Voglio che l'utente inserisca all'interno del programma il nome del sito, come faccio?
    //Provo a utilizzare google come motore di ricerca ed ottenere dei risultati

    /*public static String getSiteUrl(String siteName) {
        String searchUrl = "https://api.duckduckgo.com/?q=" + URLEncoder.encode(siteName, StandardCharsets.UTF_8) + "&format=json";

        try {
            Connection connection = Jsoup.connect(searchUrl);
            Document document = connection.ignoreContentType(true).get();

            // Ottieni la risposta JSON ed estrai l'URL
            String jsonString = document.body().text();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONArray resultsArray = (JSONArray) jsonObject.get("Results");

            // Controlla se ci sono risultati
            if (resultsArray != null && resultsArray.size() > 0) {
                // Ottieni il primo risultato
                JSONObject firstResult = (JSONObject) resultsArray.get(0);

                // Ora puoi ottenere il valore del campo "FirstURL"
                String siteUrl = (String) firstResult.get("FirstURL");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private static String extractUrlFromGoogleUrl(String googleUrl) {
        // Estrai l'URL dalla stringa di reindirizzamento di Google
        // Ad esempio, da "/url?q=https://www.example.com" a "https://www.example.com"
        return googleUrl.replace("/url?q=", "").split("&")[0];
    }
    */


    public static void crawl(int level, String url, ArrayList<String> urlVisited){
        Document doc = null;
        if(level <=5)
            doc = request(url, urlVisited);

        if(doc != null)
            for(Element link : doc.select("a[href]")){
                String next_link = link.absUrl("href");
                if(!urlVisited.contains(next_link))
                    crawl(level++, next_link, urlVisited);
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
