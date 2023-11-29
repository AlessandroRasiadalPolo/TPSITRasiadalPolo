import TelegramTools.Crawling.Crawler;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramTools.PokemonBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/
        Crawler.crawl("https://www.smogon.com/dex/", new ArrayList<String>());
    }

    }

