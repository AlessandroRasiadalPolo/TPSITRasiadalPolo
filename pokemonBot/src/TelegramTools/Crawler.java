package TelegramTools;

import Entities.Pokemon;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Crawler {

    static Elements pokemontable;
    private static ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
    private static ArrayList<String> rows = new ArrayList<String>();

    public static void crawl(int level, String url, ArrayList<String> urlVisited){
        Document doc = null;
        doc = request(url, urlVisited);
        if(doc != null) {
            pokemontable = doc.select("table");
            Elements rows = pokemontable.select("tr"); // ottieni tutte le righe della tabella

            for (Element row : rows) {
                Pokemon newPokemon = new Pokemon();
                Elements columns = row.select("td"); // ottieni tutte le colonne della riga
                for (Element column : columns) {

                    int types = 0;
                    String tagClass = column.className();
                    if(tagClass.equals("nomeleft"))
                        newPokemon.setPokemonName(column.text());

                    else if(tagClass.contains("boldBianco") && types == 0) {
                        newPokemon.setPrimaryType(column.text());
                        types++;
                    }
                    else if(tagClass.contains("boldBianco") && types == 1)
                        newPokemon.setSecondaryType(column.text());


                }
                if(!newPokemon.getPokemonName().isEmpty())
                    pokemons.add(newPokemon);

            }
           /* for(Entities.Pokemon p : pokemons)
                System.out.println(p.getPokemonName());
            */


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
