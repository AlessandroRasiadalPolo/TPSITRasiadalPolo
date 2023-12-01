package TelegramTools.Crawling.Json;

import Entities.Pokemon;
import Entities.Stats;
import TelegramTools.Database.DB;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class JsonObtainer {


    public static void visualizeJson(JsonNode jsonNode) {


        if (jsonNode.isObject()) {
            // Se il nodo è un oggetto, scorro attraverso i suoi campi
            jsonNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();

                // Se il campo è "pokemon", verifico se il valore è un array
                if ("pokemon".equals(fieldName) && fieldValue.isArray()) {
                    ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

                    for (JsonNode pokemon : fieldValue) {
                        //Tolto i pokemon inventati
                        if(pokemon.path("oob").path("index_number").asInt() >= 0) {
                            Pokemon pokemonToSave = new Pokemon();
                            pokemonToSave.setPokedexNumber(pokemon.path("oob").path("index_number").asInt());
                            pokemonToSave.setPokemonName(pokemon.get("name").textValue());
                            pokemonToSave.setStats(new Stats(pokemon.get("hp").asInt(),pokemon.get("spa").asInt(),
                                    pokemon.get("spd").asInt(),
                                    pokemon.get("def").asInt(),
                                    pokemon.get("atk").asInt(),
                                    pokemon.get("spe").asInt()));

                            pokemonToSave.setPrimaryType(pokemon.get("types").get(0).textValue());
                            if(pokemon.get("types").size() == 2)
                                pokemonToSave.setPrimaryType(pokemon.get("types").get(1).textValue());




                            pokemons.add(pokemonToSave);
                        }


                    }

                    if(DB.RegisterPokemon(pokemons) == 1)
                        System.out.println("Pokemon salvato correttamente!");
                    else
                        System.out.println("Errore durante il salvataggio!");
                } else {
                    // Se il campo non è "pokemon", continuo la ricerca ricorsiva
                    visualizeJson(fieldValue);
                }
            });
        } else if (jsonNode.isArray()) {
            // Se il nodo è un array, scorro attraverso i suoi elementi
            jsonNode.forEach(JsonObtainer::visualizeJson);
        }
    }
}
