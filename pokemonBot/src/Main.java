import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new PokemonBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/
        Crawler.crawl(0,"https://pwtng.altervista.org/ricerca-pokedex/", new ArrayList<String>());
    }

    }

