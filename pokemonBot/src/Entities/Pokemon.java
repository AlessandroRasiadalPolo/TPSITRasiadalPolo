package Entities;

import java.awt.*;
import java.util.ArrayList;

public class Pokemon {

    private ArrayList<String> abilities;

    private int pokedexNumber;
    private String generation;
    private String pokemonName;
    private String primaryType;
    private String secondaryType;
    private Stats stats;
    private String icon;


    public Pokemon(ArrayList<String> abilities, int pokedexNumber, String generation, String pokemonName, String primaryType, String secondaryType, String weigth, Stats stats, String icon) {
        this.abilities = abilities;
        this.pokedexNumber = pokedexNumber;
        this.generation = generation;
        this.pokemonName = pokemonName;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.stats = stats;
        this.icon = icon;
    }

    public Pokemon() {
        abilities =  new ArrayList<String>();
        pokedexNumber = 0;
        generation = "";
        pokemonName = "";
        primaryType = "";
        secondaryType = "";
        stats = new Stats();
        icon = null;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public String getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(String secondaryType) {
        this.secondaryType = secondaryType;
    }



    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }
}
