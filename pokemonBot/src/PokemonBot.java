import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static jdk.javadoc.internal.tool.Main.execute;

public class PokemonBot extends TelegramLongPollingBot {
    public String getBotUsername() {
        return "Echo Bot";
    }
    @Override
    public String getBotToken() {
        // inserire qui il proprio token
        return "6858737485:AAHy2vd7qx6tJW1I3w61CeDx2IxZvijs-LM";
    }
    public void onUpdateReceived(Update update) {
        String msg = update.getMessage().getText();
        String chatId=update.getMessage().getChatId().toString();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(msg);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            // gestione errore in invio
        }
    }
}
