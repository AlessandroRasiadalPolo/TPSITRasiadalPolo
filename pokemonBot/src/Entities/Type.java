package Entities;

import java.util.ArrayList;

public class Type {

    private String nome;

    private ArrayList<String> debolezze;
    private ArrayList<String> puntidiForza;
    private ArrayList<String> immunità;

    public Type(){
        nome = "";
        debolezze = new ArrayList<String>();
        puntidiForza = new ArrayList<String>();
        immunità = new ArrayList<>();
    }


    public Type(String nome, ArrayList<String> debolezze, ArrayList<String> puntidiForza, ArrayList<String> immunità) {
        this.nome = nome;
        this.debolezze = debolezze;
        this.puntidiForza = puntidiForza;
        this.immunità = immunità;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getDebolezze() {
        return debolezze;
    }

    public void setDebolezze(ArrayList<String> debolezze) {
        this.debolezze = debolezze;
    }

    public ArrayList<String> getPuntidiForza() {
        return puntidiForza;
    }

    public void setPuntidiForza(ArrayList<String> puntidiForza) {
        this.puntidiForza = puntidiForza;
    }

    public ArrayList<String> getImmunità() {
        return immunità;
    }

    public void setImmunità(ArrayList<String> immunità) {
        this.immunità = immunità;
    }
}
