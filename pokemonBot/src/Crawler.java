import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class Crawler {



    public static void crawl(int level, String url, ArrayList<String> urlVisited){
        Document doc = null;
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
