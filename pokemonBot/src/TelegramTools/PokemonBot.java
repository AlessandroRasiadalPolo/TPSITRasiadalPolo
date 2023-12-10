package TelegramTools;

import Entities.Move;
import Entities.Pokemon;
import TelegramTools.Database.DB;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    private enum BotState {
        WAITING_FOR_COMMAND,
        WAITING_FOR_POKEMON_NAME,
        WAITING_FOR_POKEMON_ABILITY,
        WAITING_FOR_ABILITY_PER_POKEMON,
        WAITING_FOR_GENERATION,
        WAITING_FOR_MOVES,
        WAITING_FOR_MOVE
    }

    private BotState botState = BotState.WAITING_FOR_COMMAND;
    private String chatIdWaitingForName;

    public void onUpdateReceived(Update update) {
        try {


            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                String chatId = update.getMessage().getChatId().toString();


                switch (botState) {
                    case WAITING_FOR_COMMAND:
                        if (messageText.equals("/show_pokemon_description")) {
                            // Se ricevi /show_pokemon, passa allo stato "WAITING_FOR_POKEMON_NAME"
                            botState = BotState.WAITING_FOR_POKEMON_NAME;
                            chatIdWaitingForName = chatId;

                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(chatId);
                            sendMessage.setText("Inserisci il nome del Pokémon:");
                            execute(sendMessage);
                        }

                        if(messageText.equals("/show_abilities")){
                            botState =  BotState.WAITING_FOR_POKEMON_ABILITY;
                            chatIdWaitingForName = chatId;

                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(chatId);
                            sendMessage.setText("Inserisci l'abilità:");
                            execute(sendMessage);

                        }

                        if(messageText.equals("/show_pokemon_abilities")){
                            botState =  BotState.WAITING_FOR_ABILITY_PER_POKEMON;
                            chatIdWaitingForName = chatId;

                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(chatId);
                            sendMessage.setText("Inserisci il nome del pokemon:");
                            execute(sendMessage);

                        }
                        if(messageText.equals("/show_pokemon_generations")){
                            botState = BotState.WAITING_FOR_GENERATION;
                            chatIdWaitingForName =  chatId;
                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(chatId);
                            sendMessage.setText("Inserisci la generazione (nome completo o abbreviato):");
                            execute(sendMessage);

                        }

                        if(messageText.equals("/show_pokemon_moves")){
                            botState = BotState.WAITING_FOR_MOVES;
                            chatIdWaitingForName =  chatId;
                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(chatId);
                            sendMessage.setText("Inserisci il nome del pokemon:");
                            execute(sendMessage);
                        }
                        if(messageText.equals("/show_move_description")){
                            botState = BotState.WAITING_FOR_MOVE;
                            chatIdWaitingForName =  chatId;
                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(chatId);
                            sendMessage.setText("Inserisci il nome della mossa:");
                            execute(sendMessage);
                        }
                        break;
                    case WAITING_FOR_POKEMON_NAME:
                            // Usa il testo del messaggio come il nome del Pokémon
                            String pokemonName = messageText.trim();

                            // Esegui la tua logica per mostrare il Pokémon
                            HashMap<String, String> pokemonDetails = showSelectedPokemon(pokemonName);

                            // Invia l'animazione con la descrizione
                            SendAnimation sendAnimation = new SendAnimation();
                            sendAnimation.setChatId(chatIdWaitingForName);
                            sendAnimation.setAnimation(new InputFile(pokemonDetails.get("gifURL")));
                            sendAnimation.setCaption(pokemonDetails.get("Description"));
                            execute(sendAnimation);

                        // Ripristina lo stato per aspettare il prossimo comando
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                    case WAITING_FOR_POKEMON_ABILITY:
                        String pokemonAbility = messageText.trim();

                        String pokemons = showPokemonsPerAbility(pokemonAbility);
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(chatIdWaitingForName);
                        sendMessage.setText(pokemons);
                        execute(sendMessage);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                    case WAITING_FOR_ABILITY_PER_POKEMON:
                        String pokemon = messageText.trim();
                        String abilities = showSelectedPokemonAbilites(pokemon);
                        SendMessage sendMessageAbilities = new SendMessage();
                        sendMessageAbilities.setChatId(chatIdWaitingForName);
                        sendMessageAbilities.setText(abilities);
                        execute(sendMessageAbilities);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;


                    case WAITING_FOR_GENERATION:
                        String generazione = messageText.trim();
                        String pokemonPerGeneration = showPokemonPerGeneration(generazione);
                        SendMessage sendMessagePerGeneration = new SendMessage();
                        sendMessagePerGeneration.setChatId(chatIdWaitingForName);
                        sendMessagePerGeneration.setText(pokemonPerGeneration);
                        execute(sendMessagePerGeneration);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                    case WAITING_FOR_MOVES:
                        String pokemon_move = messageText.trim();
                        String pokemonMoves = showPokemonMoves(pokemon_move);
                        SendMessage sendMessageMove = new SendMessage();
                        sendMessageMove.setChatId(chatIdWaitingForName);
                        sendMessageMove.setText(pokemonMoves);
                        execute(sendMessageMove);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                    case WAITING_FOR_MOVE:
                        String move = messageText.trim();
                        String moveDescription = showMoveDetails(move);
                        SendMessage sendMessageMoveDesc = new SendMessage();
                        sendMessageMoveDesc.setChatId(chatIdWaitingForName);
                        sendMessageMoveDesc.setText(moveDescription);
                        execute(sendMessageMoveDesc);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo per formattare la lista
    public static String formatList(List<?> lista) {
        return lista.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private String showPokemonsPerAbility(String ability){
        String ris = "I pokemon che possono avere l'abilità " + ability + "sono:" + "\n";
        ArrayList<String> pokemons = DB.ottieniAbilità(ability);
        for(String s : pokemons)
            ris += s + "\n";

        return ris;
    }

    private HashMap<String, String> showSelectedPokemon(String pokemonName){
        Pokemon pokemon = DB.ottieniPokemon(pokemonName);

        if(pokemon == null)
            return null;

        HashMap<String, String> result = new HashMap<>();

        result.put("gifURL", pokemon.getIcon());
        String getDescription = pokemon.toString();

        result.put("Description", getDescription);

        return result;


    }

    private String showSelectedPokemonAbilites(String pokemon){
        String ris = "Le abilità che " + pokemon + " può imparare sono le seguenti: \n";
        String[] abilities = DB.ottieniAbilitàperPokemon(pokemon);

        for(String s : abilities)
        {
            if(s != null)
                ris+= s + "\n";
        }

        return ris;
    }

    private String showPokemonPerGeneration(String generazione){
        generazione = generazione.toLowerCase();

        switch (generazione){
            case "diamante e perla":
                generazione = "DP";
                break;

            case "bianco e nero":
                generazione = "BW";
                break;

            case "oro e argento":
                generazione = "GS";
                break;

            case "rosso e blu":
                generazione = "RB";
                break;

            case "scarlatto e violetto":
                generazione = "SV";
                break;

            case "sole e luna":
                generazione = "SM";
                break;

            case "x e y":
                generazione = "XY";
                break;

            case "spada e scudo":
                generazione = "SS";
                break;

            case "rubino e zaffiro":
                generazione = "RS";
                break;

        }

        ArrayList<String> pokemons = DB.ottieniPokemonperGenerazione(generazione);
        String ris = "I pokemon appartenenti alla generazione " + generazione + " sono: \n";

        for(String s : pokemons)
            ris += s + "\n";

        return ris;

    }

    public String showPokemonMoves(String pokemon){

        ArrayList<Move> mosse = DB.ottieniMosse(pokemon);
        String ris = "Le mosse che " + pokemon + " può imparare sono: ";
        for(Move mossa :  mosse)
            ris += mossa.getName() + "\n";

        return ris;
    }

    public String showMoveDetails(String mossa){
        Move move = DB.ottieniDettagliMossa(mossa);

        return move.toString();

    }

}
