import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

       String siteName;
        Scanner sc = new Scanner(System.in);

        /*System.out.println("Inserisci il nome del sito: ");
          siteName = sc.next();*/


        /*if(!siteUrl.equals("")){
            System.out.println("Sito trovato! Inizio crawling");
            Crawler.crawl(0,siteUrl, new ArrayList<String>());
        }

        else
            System.out.println("Sito non trovato! Riprova");

         */


        Crawler.crawl(0,"https://it.wikipedia.org/wiki/Pagina_principale", new ArrayList<String>());






    }
}