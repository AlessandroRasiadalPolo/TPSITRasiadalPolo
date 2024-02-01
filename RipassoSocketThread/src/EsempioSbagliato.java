import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableExample implements Callable<String> {
    @Override
    public String call() throws Exception {
        // Esegui la tua computazione qui
        return "Risultato della computazione";
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Creazione di un oggetto Callable
        Callable<String> myCallable = new CallableExample();
        Callable<String> myCallable2 = new CallableExample();

        // Sottoposizione del Callable all'ExecutorService
        Future<String> future = executorService.submit(myCallable);
        Future<String> future2 = executorService.submit(myCallable2);

        try {
            // Attendi il risultato della computazione
            String result = future.get();
            System.out.println("Risultato: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Chiudi l'ExecutorService
            executorService.shutdown();
        }
    }
}