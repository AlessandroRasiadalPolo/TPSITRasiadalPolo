package TelegramTools.Crawling.Json;

import Entities.*;
import Entities.Stats;
import TelegramTools.Database.DB;
import TelegramTools.Database.DbSaver;
import com.fasterxml.jackson.databind.JsonNode;
import com.oracle.truffle.regex.tregex.util.json.Json;
import org.json.simple.JSONArray;
import org.jsoup.nodes.Entities;

import java.util.ArrayList;

public class JsonObtainer {


    public static void visualizeJson(JsonNode jsonNode) {


        if (jsonNode.isObject()) {

            // Se il nodo è un oggetto, scorro attraverso i suoi campi
            jsonNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();

                if ("items".equals(fieldName) && fieldValue.isArray()) {
                    ArrayList<Item> items = new ArrayList<Item>();

                    for (JsonNode item : fieldValue) {
                        Item itemTosave = new Item();
                        itemTosave.setItemName(item.get("name").asText());
                        itemTosave.setItemDescription(item.get("description").asText());

                        items.add(itemTosave);
                    }

                    if (DbSaver.registerItems(items) == 1)
                        System.out.println("Strumenti salvati correttamente!");
                    else
                        System.out.println("Errore durante il salvataggio degli strumenti");
                } else if ("types".equals(fieldName) && fieldValue.isArray()) {
                    ArrayList<Type> types = new ArrayList<Type>();

                    for (JsonNode type : fieldValue) {
                        Type typeToSave = new Type();

                        typeToSave.setNome(type.get("name").asText());
                        for (JsonNode node : type.path("atk_effectives")) {
                            if (node.get(1).asDouble() == 0)
                                typeToSave.getImmunità().add(node.get(0).asText());
                            else if (node.get(1).asDouble() == 2)
                                typeToSave.getPuntidiForza().add(node.get(0).asText());
                            else if (node.get(1).asDouble() == 0.5)
                                typeToSave.getDebolezze().add(node.get(0).asText());
                        }


                        types.add(typeToSave);
                    }
                    if (DbSaver.registerType(types) == 1)
                        System.out.println("Tipi registrati con successo!");
                    else
                        System.out.println("Errore nella registrazione dei tipi");

                }
                else if ("abilities".equals(fieldName) && fieldValue.isArray()) {
                    ArrayList<Ability> abilities = new ArrayList<Ability>();

                    for (JsonNode ability : fieldValue) {
                        Ability abilityTosave = new Ability();
                        abilityTosave.setAbilityName(ability.get("name").asText());
                        abilityTosave.setAbilityEffect(ability.get("description").asText());

                        abilities.add(abilityTosave);
                    }
                    if (DbSaver.registerAbility(abilities) == 1)
                        System.out.println("Abilità salvate correttamente!");
                    else
                        System.out.println("Errore salvataggio abilità");


                }


            });

            jsonNode.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();


                if ("moves".equals(fieldName) && fieldValue.isArray()) {
                    ArrayList<Move> moves = new ArrayList<Move>();

                    for (JsonNode move : fieldValue) {
                        Move moveTosave = new Move();

                        moveTosave.setName(move.get("name").asText());
                        moveTosave.setcategory(move.get("category").asText());
                        moveTosave.setAccuracy(move.get("accuracy").asInt());
                        moveTosave.setPp(move.get("pp").asInt());
                        moveTosave.setTipo(move.get("type").asText());
                        moveTosave.setPower(move.get("power").asInt());
                        moveTosave.setEffetto(move.get("description").asText());
                        moveTosave.setPriority(move.get("priority").asInt());

                        moves.add(moveTosave);
                    }

                    if(DbSaver.registerMoves(moves) == 1)
                        System.out.println("Mosse salvate correttamente!");
                    else
                        System.out.println("Errore durante memorizzazione mosse");
                }


                if ("pokemon".equals(fieldName) && fieldValue.isArray()) {
                    // Se il campo è "pokemon", verifico se il valore è un array
                    //Ho solo i nomi dei pokemon, il tipo, le abilità, le statistiche e la generazione
                    ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();


                    for (JsonNode pokemon : fieldValue) {
                        //Tolgo i pokemon inventati
                        if (pokemon.path("oob").path("dex_number").asInt() > 0) {
                            final String url = "https://www.smogon.com/dex/media/sprites/xy/";
                            Pokemon pokemonToSave = new Pokemon();
                            //salvo il numero del pokedex
                            pokemonToSave.setPokedexNumber(pokemon.path("oob").path("dex_number").asInt());
                            //Salvo il nome del pokemon
                            pokemonToSave.setPokemonName(pokemon.get("name").textValue());
                            //Salvo le statistiche dei pokemon
                            pokemonToSave.setStats(new Stats(pokemon.get("hp").asInt(), pokemon.get("spa").asInt(),
                                    pokemon.get("spd").asInt(),
                                    pokemon.get("def").asInt(),
                                    pokemon.get("atk").asInt(),
                                    pokemon.get("spe").asInt()));

                            //Salvo tutte le abilità possibili del pokemon
                            for(JsonNode ability : pokemon.get("abilities"))
                                pokemonToSave.getAbilities().add(ability.asText());

                            //Salvo i tipi dei pokemon
                            pokemonToSave.setPrimaryType(pokemon.get("types").get(0).textValue());
                            if (pokemon.get("types").size() == 2)
                                pokemonToSave.setSecondaryType(pokemon.get("types").get(1).textValue());


                            //Salvo la generazione
                            pokemonToSave.setGeneration(pokemon.path("oob").path("genfamily").get(0).asText());
                            pokemonToSave.setIcon(url + pokemonToSave.getPokemonName().toLowerCase() + ".gif");
                            pokemons.add(pokemonToSave);
                        }



                    }

                    if (DbSaver.RegisterPokemon(pokemons) == 1)
                        System.out.println("Pokemon salvato correttamente!");
                    else
                        System.out.println("Errore durante il salvataggio!");

                    if (DbSaver.registerStats(pokemons) == 1)
                        System.out.println("Statistiche del pokemon salvate correttamente!");
                    else
                        System.out.println("Errore salvataggio statistiche!");

                } else
                    visualizeJson(fieldValue);
            });


        } else if (jsonNode.isArray()) {
            // Se il nodo è un array, scorro attraverso i suoi elementi
            jsonNode.forEach(JsonObtainer::visualizeJson);
        }
    }
}