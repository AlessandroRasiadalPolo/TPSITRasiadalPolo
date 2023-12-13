package Entities;

public class PokemonTeam extends Pokemon{
    private int id;
    private String strumento;
    private String abiltià;
    private String mossa1;
    private String mossa2;
    private String mossa3;
    private String mossa4;
    private String teamName;

    public PokemonTeam(){
        id = 0;
        strumento = "";
        abiltià = "";
        mossa1 = "";
        mossa2 = "";
        mossa3 = "";
        mossa4 = "";
        teamName = "";
    }

    public PokemonTeam(String nome, int id, String strumento, String abiltià, String mossa1, String mossa2, String mossa3, String mossa4, String teamName) {
        this.id = id;
        this.pokemonName = nome;
        this.strumento = strumento;
        this.abiltià = abiltià;
        this.mossa1 = mossa1;
        this.mossa2 = mossa2;
        this.mossa3 = mossa3;
        this.mossa4 = mossa4;
        this.teamName = teamName;
    }

    public String getStrumento() {
        return strumento;
    }

    public void setStrumento(String strumento) {
        this.strumento = strumento;
    }

    public String getAbiltià() {
        return abiltià;
    }

    public void setAbiltià(String abiltià) {
        this.abiltià = abiltià;
    }

    public String getMossa1() {
        return mossa1;
    }

    public void setMossa1(String mossa1) {
        this.mossa1 = mossa1;
    }

    public String getMossa2() {
        return mossa2;
    }

    public void setMossa2(String mossa2) {
        this.mossa2 = mossa2;
    }

    public String getMossa3() {
        return mossa3;
    }

    public void setMossa3(String mossa3) {
        this.mossa3 = mossa3;
    }

    public String getMossa4() {
        return mossa4;
    }

    public void setMossa4(String mossa4) {
        this.mossa4 = mossa4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
