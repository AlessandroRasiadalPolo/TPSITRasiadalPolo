package Entities;

public class Move {

    private String name;

    private int power;
    private String effetto;
    private String category; //O speciale o fisica
    private int accuracy;
    private String tipo;
    private int priority;
    private int pp;

    public Move()
    {
        name = "";
        pp = 0;
        priority = 0;
        accuracy = 0;
        tipo = "";
        power = 0;
        effetto = "";
        category = "";
    }

    public Move(String name, int pp, int priority, int accuracy, String type, int power, String effetto, String category) {
        this.name = name;
        this.power = power;
        this.effetto = effetto;
        this.category = category;
        this.accuracy = accuracy;
        this.tipo = type;
        this.pp = pp;
        this.priority = priority;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getEffetto() {
        return effetto;
    }

    public void setEffetto(String effetto) {
        this.effetto = effetto;
    }

    public String getcategory() {
        return category;
    }

    public void setcategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString(){
        return "Nome: " + this.name + "\t Power: " + this.power + "\n" +
                "Priorità: " + this.priority + "\t Tipo: " + this.tipo + "\n" +
                "Precisione: " + this.accuracy + "\t Categoria: " + this.category + "\n" +
                "Effetto: " + this.effetto + "\n";
    }
}
