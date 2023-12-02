package Entities;

import java.awt.*;

public class Pokemon {

    private int pokedexNumber;
    private String generation;
    private String pokemonName;
    private String primaryType;
    private String secondaryType;
    private String weigth;
    private Stats stats;
    private Image icon;


    public Pokemon(int pokedexNumber, String generation, String pokemonName, String primaryType, String secondaryType, String weigth, Stats stats, Image icon) {
        this.pokedexNumber = pokedexNumber;
        this.generation = generation;
        this.pokemonName = pokemonName;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.weigth = weigth;
        this.stats = stats;
        this.icon = icon;
    }

    public Pokemon() {
        pokedexNumber = 0;
        generation = "";
        pokemonName = "";
        primaryType = "";
        secondaryType = "";
        weigth = "";
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

    public String getWeigth() {
        return weigth;
    }

    public void setWeigth(String weigth) {
        this.weigth = weigth;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
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
}
