package TelegramTools.Crawling.TimeCounting;
import java.time.LocalDate;

public class PeriodicCount {


    private LocalDate ultimaEsecuzione;


    public PeriodicCount() {
        // Inizializza la data dell'ultima esecuzione alla data corrente
        this.ultimaEsecuzione = LocalDate.now();
    }

    public LocalDate getUltimaEsecuzione() {
        return ultimaEsecuzione;
    }
}
