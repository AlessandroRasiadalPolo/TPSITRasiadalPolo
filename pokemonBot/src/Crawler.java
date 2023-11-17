import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class Crawler {

    static Elements pokemontable;
    private static ArrayList<String> rows = new ArrayList<String>();

    public static void crawl(int level, String url, ArrayList<String> urlVisited){
        Document doc = null;
        doc = request(url, urlVisited);
        if(doc != null) {
            pokemontable = doc.select("table");
            Elements rows = pokemontable.select("tr"); // ottieni tutte le righe della tabella

            for (Element row : rows) {
                Elements columns = row.select("td"); // ottieni tutte le colonne della riga
                for (Element column : columns) {
                    // fai qualcosa con il contenuto della colonna, ad esempio stampalo a schermo
                    System.out.print(column.text() + "\t");
                }
                System.out.println(); // vai a capo dopo aver stampato tutte le colonne di una riga
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
