package TelegramTools;

import Entities.Pokemon;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.ArrayList;


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
                    crawl(element.absUrl("href"), urlVisited);
            }

        }
    }

    private static void crawlPokemon(String pokemonUrl){
        Document pokemonDoc = request(pokemonUrl, new ArrayList<>());
        if (pokemonDoc != null) {
            Elements section = pokemonDoc.select("section pokedex-pokemon-details");
            for(Element element : section)
                System.out.println(element.text());
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
