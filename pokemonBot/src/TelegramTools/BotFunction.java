package TelegramTools;

import Entities.Move;
import Entities.Pokemon;
import Entities.PokemonTeam;
import TelegramTools.Database.DB;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BotFunction {
    //Metodo per formattare la lista
    public static String formatList(List<?> lista) {
        return lista.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    public static String showPokemonsPerAbility(String ability){
        String ris = "I pokemon che possono avere l'abilità " + ability + "sono:" + "\n";
        ArrayList<String> pokemons = DB.ottieniAbilità(ability);
        for(String s : pokemons)
            ris += s + "\n";

        return ris;
    }

    public static HashMap<String, String> showSelectedPokemon(String pokemonName){
        Pokemon pokemon = DB.ottieniPokemon(pokemonName);

        if(pokemon == null)
            return null;

        HashMap<String, String> result = new HashMap<>();

        result.put("gifURL", pokemon.getIcon());
        String getDescription = pokemon.toString();

        result.put("Description", getDescription);

        return result;


    }

    public static String showSelectedPokemonAbilites(String pokemon){
        String ris = "Le abilità che " + pokemon + " può imparare sono le seguenti: \n";
        String[] abilities = DB.ottieniAbilitàperPokemon(pokemon);

        for(String s : abilities)
        {
            if(s != null)
                ris+= s + "\n";
        }

        return ris;
    }

    public static String showPokemonPerGeneration(String generazione){
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

    public static String showPokemonMoves(String pokemon){

        ArrayList<Move> mosse = DB.ottieniMosse(pokemon);
        String ris = "Le mosse che " + pokemon + " può imparare sono: ";
        for(Move mossa :  mosse)
            ris += mossa.getName() + "\n";

        return ris;
    }

    public static String showMoveDetails(String mossa){
        Move move = DB.ottieniDettagliMossa(mossa);

        return move.toString();

    }

    public static String savePokemonTeam(ArrayList<PokemonTeam> pokemons, String nomeTeam, String userName){

        if(pokemons == null || nomeTeam == null || userName == null)
            return "Errore, uno dei parametri è nullo";

        if(DB.savePokemonTeam(pokemons, nomeTeam, userName) == 1)
            return "Il team è stato creato e salvato correttamente!";

        return null;

    }
}
