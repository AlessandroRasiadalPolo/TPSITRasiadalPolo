import TelegramTools.Crawling.Crawler;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Crawler.crawl("https://www.smogon.com/dex/", new ArrayList<String>());
        Crawler.crawlPokemonPage("https://pokemondb.net/pokedex/", new ArrayList<String>());
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramTools.PokemonBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    }

