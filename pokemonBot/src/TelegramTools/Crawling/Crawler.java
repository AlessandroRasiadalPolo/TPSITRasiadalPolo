package TelegramTools.Crawling;

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

            //Analizzo solo un link
            int count = 0;

            //Scorro gli ul
            for(Element link : elements) {
                //Seleziono i li
                Elements li = link.select("a[href]");
                //Per ogni li prendo i gli url
                for (Element element : li) {
                    //Devo scorrere gli elementi ma dato che il json è uguale per tutti i link posso analizzarne solo uno
                    if(count == 0) {
                        crawlPokemon(element.absUrl("href"), new ArrayList<String>());
                        count++;
                    }
                }
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
                    String scriptToRead = JsonAnalyzer.extractJsonIncrementally(scriptContent);
                    if(!scriptToRead.isEmpty())
                        JsonAnalyzer.readJsonLineByLine(scriptToRead, "src/Files/pokemon.json");

                    }
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
