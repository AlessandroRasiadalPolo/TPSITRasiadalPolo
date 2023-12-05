package TelegramTools.Crawling;

import TelegramTools.Crawling.Json.JsonAnalyzer;
import TelegramTools.Crawling.Json.JsonObtainer;
import TelegramTools.Crawling.TimeCounting.PeriodicCount;
import TelegramTools.Database.DB;
import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;


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
            Element uls = doc.select("ul").first();

            //Otterrei 3 elementi diversi ma derivano tutti dalla stessa variabile json
            Element li = uls.select("a[href]").first();
            //Posso analizzare solo il primo link
            crawlPokemon(li.absUrl("href"), new ArrayList<String>());

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
                    //String scriptToRead = JsonAnalyzer.extractJsonIncrementally(scriptContent);

                        //Attraverso una funzione mi ricavo un puntatore json
                        if(scriptContent.contains("dexSettings")) {
                            scriptContent = scriptContent.replace("dexSettings = ", "");
                            JsonNode jsonNode = JsonAnalyzer.prepareJson(scriptContent);

                            //Scorro ricorsivamente il json ottenuto
                            JsonObtainer.visualizeJson(jsonNode);
                        }
                    }

                    }
            }

            public static int crawlPokemonPage(String url, ArrayList<String> urls){

                Document doc = request(url, urls);

                if(doc != null){

                    LinkedList<String> pokemons = DB.savePokemonMoves();

                    while(!pokemons.isEmpty()){
                        String urlToVisit = url + pokemons.getFirst();
                        crawlPokemonMoves(urlToVisit, urls, pokemons.getFirst());
                        pokemons.removeFirst();
                    }
                    System.out.println("Mosse per ogni pokemon salvate!");

                    return 1;
                }
                return -1;
            }

    private static int crawlPokemonMoves(String url, ArrayList<String> urls, String pokemonName){

        Document doc = request(url, urls);

        if(doc != null){

            ArrayList<String> moves = new ArrayList<String>();

            LinkedList<String> pokemons = DB.savePokemonMoves();

            Elements table = doc.select("table");

            Elements tr = table.select("tr");

            Elements tds = tr.select("tr").select("td").select("a.ent-name");
            for(Element move : tds){
                moves.add(move.text());
            }
            DB.saveMoves(moves, pokemonName);

            return 1;
        }
        return -1;
    }


    private static Document request(String url, ArrayList<String> urlvisited){

        try{
            Connection conn = Jsoup.connect(url);
            Document doc = conn.get();

            if(conn.response().statusCode() == 200){
                urlvisited.add(url);

                return doc;
            }
            return null;

        }catch (IOException e){
            return null;
        }

    }



}
