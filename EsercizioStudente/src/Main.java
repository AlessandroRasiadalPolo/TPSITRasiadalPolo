
import Scuola.Studente;

import java.time.*;

public class Main {

    public static void main(String[] args) {

        Studente primo = new Studente("Pippo", "Baudo", LocalDate.of(2004, 4, 10), "Informatica", new float[] {5, 10, 3, 7, 0, 0, 0, 0, 0, 0}  );
        Studente secondo = new Studente("Pippo2", "Baudo2", LocalDate.of(1995, 5, 22), "Economia", new float[] {7, 2, 8, 0, 0, 0, 0, 0, 0, 0});

        //test toString
        System.out.println("test toString:");
        System.out.println(primo.toString() + "\n");
        System.out.println(secondo.toString() + "\n");

        //test aggiunta voti
        primo.aggiungiVoto(10);
        primo.aggiungiVoto(9);
        System.out.println(primo.toString() + "\n");

        //test rimozione voti
        secondo.rimuoviVoti();
        System.out.println(secondo.toString());

    }
}