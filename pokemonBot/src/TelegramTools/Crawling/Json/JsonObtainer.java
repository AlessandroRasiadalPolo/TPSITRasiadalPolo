package TelegramTools.Crawling.Json;

import Entities.*;
import Entities.Stats;
import TelegramTools.Database.DB;
import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.nodes.Entities;

import java.util.ArrayList;

public class JsonObtainer {


    public static void visualizeJson(JsonNode jsonNode) {


        if (jsonNode.isObject()) {
            // Se il nodo è un oggetto, scorro attraverso i suoi campi
            jsonNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();

                if("items".equals(fieldName) && fieldValue.isArray()){
                    ArrayList<Item> items = new ArrayList<Item>();

                    for(JsonNode item : fieldValue)
                    {
                        Item itemTosave = new Item();
                        itemTosave.setItemName(item.get("name").asText());
                        itemTosave.setItemDescription(item.get("description").asText());

                        items.add(itemTosave);
                    }
                }

                else if("moves".equals(fieldName) && fieldValue.isArray()){
                    ArrayList<Move> moves = new ArrayList<Move>();

                    for(JsonNode move : fieldValue){
                        Move moveTosave = new Move();

                        moveTosave.setEffetto(move.get("name").asText());
                        moveTosave.setcategory(move.get("category").asText());
                        moveTosave.setAccuracy(move.get("accuracy").asInt());
                        moveTosave.setPp(move.get("pp").asInt());
                        moveTosave.setTipo(move.get("type").asText());
                        moveTosave.setPower(move.get("power").asInt());
                        moveTosave.setEffetto(move.get("description").asText());
                        moveTosave.setPriority(move.get("priority").asInt());
                    }
                }


                // Se il campo è "pokemon", verifico se il valore è un array
                //Ho solo i nomi dei pokemon, il tipo, le abilità, le statistiche e la generazione
                else if ("pokemon".equals(fieldName) && fieldValue.isArray()) {
                    ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();


                    for (JsonNode pokemon : fieldValue) {
                        //Tolgo i pokemon inventati
                        if(pokemon.path("oob").path("index_number").asInt() >= 0) {
                            Pokemon pokemonToSave = new Pokemon();
                            //salvo il numero del pokedex
                            pokemonToSave.setPokedexNumber(pokemon.path("oob").path("index_number").asInt());
                            //Salvo il nome del pokemon
                            pokemonToSave.setPokemonName(pokemon.get("name").textValue());
                            //Salvo le statistiche dei pokemon
                            pokemonToSave.setStats(new Stats(pokemon.get("hp").asInt(),pokemon.get("spa").asInt(),
                                    pokemon.get("spd").asInt(),
                                    pokemon.get("def").asInt(),
                                    pokemon.get("atk").asInt(),
                                    pokemon.get("spe").asInt()));

                            //Salvo i tipi dei pokemon
                            pokemonToSave.setPrimaryType(pokemon.get("types").get(0).textValue());
                            if(pokemon.get("types").size() == 2)
                                pokemonToSave.setPrimaryType(pokemon.get("types").get(1).textValue());

                            //Salvo la generazione
                            pokemonToSave.setGeneration(pokemon.path("oob").path("genfamily").get(0).asText());


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
