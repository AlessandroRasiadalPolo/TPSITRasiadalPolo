package TelegramTools;

import Entities.Move;
import Entities.Pokemon;
import Entities.PokemonTeam;
import TelegramTools.Database.DB;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
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
        WAITING_FOR_MOVE,
        WAITING_FOR_TEAM_NAME,
        WAITING_FOR_TEAM_MEMBER_DETAILS
    }

    private BotState botState = BotState.WAITING_FOR_COMMAND;
    private String chatIdWaitingForName;

    public void onUpdateReceived(Update update) {
        try {

            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                Message message = update.getMessage();
                User user = message.getFrom();
                String username = user.getUserName();
                String chatId = update.getMessage().getChatId().toString();
                //Se l'utente vuole creare un team tengo pronta la variabile
                ArrayList<PokemonTeam> pokemonTeamMember = new ArrayList<>();
                String teamName = "";

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

                        if(messageText.equals("/create_team")){
                            botState = BotState.WAITING_FOR_TEAM_NAME;
                            chatIdWaitingForName = chatId;
                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(chatId);
                            sendMessage.setText("Inserisci il nome della squadra");
                            execute(sendMessage);

                        }
                        break;

                    case WAITING_FOR_POKEMON_NAME:
                        // Usa il testo del messaggio come il nome del Pokémon
                        String pokemonName = messageText.trim();

                        // Esegui la tua logica per mostrare il Pokémon
                        HashMap<String, String> pokemonDetails = BotFunction.showSelectedPokemon(pokemonName);

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

                        String pokemons = BotFunction.showPokemonsPerAbility(pokemonAbility);
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(chatIdWaitingForName);
                        sendMessage.setText(pokemons);
                        execute(sendMessage);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                    case WAITING_FOR_ABILITY_PER_POKEMON:
                        String pokemon = messageText.trim();
                        String abilities = BotFunction.showSelectedPokemonAbilites(pokemon);
                        SendMessage sendMessageAbilities = new SendMessage();
                        sendMessageAbilities.setChatId(chatIdWaitingForName);
                        sendMessageAbilities.setText(abilities);
                        execute(sendMessageAbilities);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;


                    case WAITING_FOR_GENERATION:
                        String generazione = messageText.trim();
                        String pokemonPerGeneration = BotFunction.showPokemonPerGeneration(generazione);
                        SendMessage sendMessagePerGeneration = new SendMessage();
                        sendMessagePerGeneration.setChatId(chatIdWaitingForName);
                        sendMessagePerGeneration.setText(pokemonPerGeneration);
                        execute(sendMessagePerGeneration);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                    case WAITING_FOR_MOVES:
                        String pokemon_move = messageText.trim();
                        String pokemonMoves = BotFunction.showPokemonMoves(pokemon_move);
                        SendMessage sendMessageMove = new SendMessage();
                        sendMessageMove.setChatId(chatIdWaitingForName);
                        sendMessageMove.setText(pokemonMoves);
                        execute(sendMessageMove);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                    case WAITING_FOR_MOVE:
                        String move = messageText.trim();
                        String moveDescription = BotFunction.showMoveDetails(move);
                        SendMessage sendMessageMoveDesc = new SendMessage();
                        sendMessageMoveDesc.setChatId(chatIdWaitingForName);
                        sendMessageMoveDesc.setText(moveDescription);
                        execute(sendMessageMoveDesc);
                        botState = BotState.WAITING_FOR_COMMAND;
                        break;

                    case WAITING_FOR_TEAM_NAME:

                        // L'utente ha inserito il nome della squadra
                        teamName = messageText.trim();
                        // Esegui la tua logica per gestire il nome della squadra

                        // Invia un messaggio per chiedere i dettagli del primo Pokémon
                        SendMessage sendFirstPokemonMessage = new SendMessage();
                        sendFirstPokemonMessage.setChatId(chatIdWaitingForName);
                        sendFirstPokemonMessage.setText("Inserisci i dettagli del primo Pokémon (Nome, Abilità, Strumento, Mossa1, Mossa2, Mossa3, Mossa4):");
                        execute(sendFirstPokemonMessage);

                        // Passa allo stato successivo per aspettare i dettagli del primo Pokémon
                        botState = BotState.WAITING_FOR_TEAM_MEMBER_DETAILS;
                        break;

                    case WAITING_FOR_TEAM_MEMBER_DETAILS:
                        // Usa il testo del messaggio come dettagli del Pokémon
                        String[] teamMemberDetails = messageText.trim().split(", ");
                        PokemonTeam pokemonTeam = new PokemonTeam();

                        //Se la sequenza data dall'utente è corretta so già l'ordine di inserimento
                        pokemonTeam.setPokemonName(teamMemberDetails[0]);
                        pokemonTeam.setAbiltià(teamMemberDetails[1]);
                        pokemonTeam.setStrumento(teamMemberDetails[2]);
                        pokemonTeam.setMossa1(teamMemberDetails[3]);
                        pokemonTeam.setMossa2(teamMemberDetails[4]);
                        pokemonTeam.setMossa3(teamMemberDetails[5]);
                        pokemonTeam.setMossa4(teamMemberDetails[6]);

                        pokemonTeamMember.add(pokemonTeam);

                        // Invia un messaggio di conferma o di richiesta di ulteriori dettagli
                        SendMessage sendTeamMemberMessage = new SendMessage();
                        sendTeamMemberMessage.setChatId(chatIdWaitingForName);
                        if(pokemonTeamMember.size() < 1)
                            sendTeamMemberMessage.setText("Pokemon aggiunto! Inserisci il prossimo");
                        else {
                            sendTeamMemberMessage.setText(BotFunction.savePokemonTeam(pokemonTeamMember, teamName, username));
                            botState = BotState.WAITING_FOR_COMMAND;
                        }

                        execute(sendTeamMemberMessage);

                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
