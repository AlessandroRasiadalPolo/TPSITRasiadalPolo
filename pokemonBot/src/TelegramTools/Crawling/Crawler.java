package TelegramTools.Crawling;

import TelegramTools.Crawling.Json.JsonAnalyzer;
import TelegramTools.Crawling.Json.JsonObtainer;
import TelegramTools.Crawling.TimeCounting.PeriodicCount;
import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class Crawler {

    //Prendo la data di oggi
    private static LocalDate today;
    private static PeriodicCount periodicCount = new PeriodicCount();


    /*
    I link che sto cercando di analizzare si trovano all'interno di funzioni javascript Onclick, quindi devo ricavare glu url dalla funzione
     */



    public static void crawl(String url, ArrayList<String> urlVisited){

        today = LocalDate.now();

        //Devo salvarmi la prima data in cui faccio partire il programma

        /*currentDate = LocalDate.now();
        if(currentDate == currentDate.plusDays(40))

         */


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
                    if(!scriptToRead.isEmpty()) {
                        //Alcuni elementi del json non vengono presi, quindi devo completarlo manualmente
                        String finalJson = scriptToRead + "]}]]}";
                        //Attraverso una funzione mi ricavo un puntatore json
                        JsonNode jsonNode = JsonAnalyzer.prepareJson(finalJson);

                        //Scorro ricorsivamente il json ottenuto
                        JsonObtainer.visualizeJson(jsonNode);
                    }

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
