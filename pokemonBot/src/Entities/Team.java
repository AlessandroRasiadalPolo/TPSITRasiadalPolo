package Entities;

public class Team {

    private String teamName;
    private String pokemonName1;
    private String pokemonName2;
    private String pokemonName3;
    private String pokemonName4;


    public Team(){
        teamName = "";
        pokemonName1 = "";
        pokemonName2 = "";
        pokemonName3 = "";
        pokemonName4 = "";
    }


    public Team(String teamName, String pokemonName1, String pokemonName2, String pokemonName3, String pokemonName4) {
        this.teamName = teamName;
        this.pokemonName1 = pokemonName1;
        this.pokemonName2 = pokemonName2;
        this.pokemonName3 = pokemonName3;
        this.pokemonName4 = pokemonName4;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPokemonName1() {
        return pokemonName1;
    }

    public void setPokemonName1(String pokemonName1) {
        this.pokemonName1 = pokemonName1;
    }

    public String getPokemonName2() {
        return pokemonName2;
    }

    public void setPokemonName2(String pokemonName2) {
        this.pokemonName2 = pokemonName2;
    }

    public String getPokemonName3() {
        return pokemonName3;
    }

    public void setPokemonName3(String pokemonName3) {
        this.pokemonName3 = pokemonName3;
    }

    public String getPokemonName4() {
        return pokemonName4;
    }

    public void setPokemonName4(String pokemonName4) {
        this.pokemonName4 = pokemonName4;
    }
}
