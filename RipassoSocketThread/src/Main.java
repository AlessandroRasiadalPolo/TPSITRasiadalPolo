import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConcurrencyExampleSolutionWithReentrantLock {

    //Ricorsa condivisa, sezione critica di codice
    private static int counter = 0;
    //Oggetto che implementa l'interfaccia ReentrantLook, funziona come il classico mutex
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        //Dichiaro un thread che esegue una funzione anonima
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                //Richiamo una funzione per sincronizzare i thread
                incrementCounter();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                incrementCounter();
            }
        });

        // Avvio dei thread
        thread1.start();
        thread2.start();

        try {
            // Attendiamo che entrambi i thread abbiano terminato l'esecuzione
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stampiamo il valore finale della variabile counter
        System.out.println("Valore finale della variabile counter: " + counter);
    }

    private static void incrementCounter() {
        //Solo un thread riuscirà a prendere il mutex, l'altro sarà bloccato
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
}