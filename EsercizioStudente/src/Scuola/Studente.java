package Scuola;

import java.util.*;
import java.time.*;

public class Studente {

    //Per convenzione le variabili si inizializzano con la lettera minuscola
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private static int matricola = 0;
    private String corsoStudi;
    private float[] voti;

    //Essendo valori String non ci dovrebbero essere valori "errati" se non in caso si stringa vuota
    public String getNome() {
        return nome;
    }

    public void setNome(String value) {
        //Se la stringa è vuota inserisco sconosciuto
        //Dal costruttore però si è "obbligati" a inserire un valore
        if (value.isEmpty())
            value = "Sconosciuto";

        nome = value;
    }

    public void setCognome(String value) {
        //Se la stringa è vuota inserisco sconosciuto
        //Dal costruttore però si è "obbligati" a inserire un valore
        if (value.isEmpty())
            value = "Sconosciuto";

        cognome = value;
    }

    public String getCognome() {
        return cognome;
    }

    public void setData(LocalDate data) {
        //Dovrei controllare anche l'anno di inserimento, il quale non dovrebbe essere
        //maggiore dell'anno corrente e non minori di un certo limite
        dataNascita = data;
    }

    public LocalDate getData() {
        return dataNascita;
    }

    public int getMatricola() {
        //La matricola è un valore di sola lettura, quindi non ho bisogno del set
        //Essendo privata non è accessibile anche se static
        return matricola;
    }

    public void setCorsi(String value) {
        //Se la stringa è vuota inserisco sconosciuto
        //Dal costruttore però si è "obbligati" a inserire un valore
        if (value.isEmpty())
            value = "Sconosciuto";

        corsoStudi = value;
    }

    public String getCorsi() {
        return corsoStudi;
    }

    public float[] getVoti() {
        return voti;
    }

    public void setVoti(float[] Voti) {
        //Un voto non può essere negativo, quindi controllo tutti i valori
        //Sostituisco un valore non accettabile con uno utilizzabile
        for (int i = 0; i < Voti.length; i++)
            if (Voti[i] < 0 || Voti[i] > 10)
                Voti[i] = 1;

        this.voti = Voti;
    }

    public Studente() {
        //Nel costruttore di default va bene avere stringhe vuote
        voti = new float[10];
        nome = "";
        cognome = "";
        //Come dataDiNascita inserisco la data attuale
        dataNascita = LocalDate.now();
        matricola++;
    }

    public Studente(String nome, String cognome, LocalDate dataNascita, String corsoStudi, float[] voti) {
        setNome(nome);
        setCognome(cognome);
        this.dataNascita = dataNascita;
        setCorsi(corsoStudi);
        setVoti(voti);
        matricola++;
    }

    public boolean aggiungiVoto(int voto) {
        //Se tutti i voti hanno un valore diverso da 0 non posso più inserirne
        boolean success = false;

        for (int i = 0; i < this.voti.length && !success; i++)
            if (this.voti[i] == 0) {
                this.voti[i] = voto;
                success = true;
            }

        return success;
    }

    public void rimuoviVoti() {
        //importando l'intero package Java.util uso il metodo Arrays.fill
        //assegna a tutti gli elementi dell' array un valore che passo come parametro
        // insieme all' array

        Arrays.fill(this.voti, 0);
    }

    public float calcolaMediaVoti() {
        int count = 0;
        float somma = 0;

        //Per risparmiare tempo posso usare un foreach
        for (float v : this.voti) {
            if (v != 0)
                count++;

            somma += v;
        }


        return somma / count;
    }

    public int calcolaEta() {
        //Sottraggo dall'anno attuale l'anno di nascita dello studente
        return Period.between(dataNascita,LocalDate.now()).getYears();
    }

    //Per eseguire l' override di un metodo devo usare la notazione @Override
    //La O deve essere maiuscola

    //La @ è utile per la documentazione e per dare informazioni aggiuntive al compilatore

    @Override
    public String toString() {

        return "Nome: " + getNome() + "\n" +
                "Cognome: " + getCognome() + "\n" +
                "Età: " + calcolaEta() + "\n" +
                "Media Voti: " + calcolaMediaVoti() + "\n" +
                "DataNascita: " + this.getData().toString() + "\n" +
                "Corso di Studi: " + getCorsi() + "\n" +
                "Matricola: " + this.getMatricola() + "\n" +
                "Voti: " + Arrays.toString(this.voti);
    }


}
