import TelegramTools.Crawling.Crawler;
import TelegramTools.Database.DB;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Main {

    private static Instant ultimoEsecuzioneCrawl = Instant.now();
    private static Instant ultimoEsecuzioneCrawlPokemonPage = Instant.now();

    public static void main(String[] args) {

        //Eseguo le 2 funzioni periodicamente
        eseguiCrawl();
        eseguiCrawlPokemonPage();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramTools.PokemonBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private static void eseguiCrawl() {
        Instant now = Instant.now();
        Duration duration = Duration.between(ultimoEsecuzioneCrawl, now);

        if (duration.toDays() >= 730) {  // 2 anni hanno circa 730 giorni

            if(DB.svuotaDatabase())
                System.out.println("Drop del database avvenuto correttamente");
            else
                System.out.println("Errore drop del database");

            Crawler.crawl("https://www.smogon.com/dex/", new ArrayList<String>());
            ultimoEsecuzioneCrawl = now;
        } else {
            System.out.println("Non eseguo crawl, sono passati meno di 2 anni dall'ultima esecuzione.");
        }
    }
    private static void eseguiCrawlPokemonPage() {
        Instant now = Instant.now();
        Duration duration = Duration.between(ultimoEsecuzioneCrawlPokemonPage, now);

        if (duration.toDays() >= 730) {  // 2 anni hanno circa 730 giorni
            Crawler.crawlPokemonPage("https://pokemondb.net/pokedex/", new ArrayList<String>());
            ultimoEsecuzioneCrawlPokemonPage = now;
        } else {
            System.out.println("Non eseguo crawlPokemonPage, sono passati meno di 2 anni dall'ultima esecuzione.");
        }
    }

    }

