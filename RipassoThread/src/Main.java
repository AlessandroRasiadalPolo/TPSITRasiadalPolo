public class Main {
    public static void main(String[] args) {

        //Istanzio un oggetto della classe thread
        //Come parametro posso pasare una funzione come facevamo in C#
        Thread thread = new Thread(() -> {System.out.println("This is a function");});
        //Per farlo partire richiamo il metodo start
        thread.start();

        //istanzio un oggetto di una classe che estende la classe Thread
        EreditaThread ereditaThread = new EreditaThread();
        //Per farlo partire richiamo il metodo start
        ereditaThread.start();

        //Istanzio un oggetto di una classe che implementa l'interfaccia Runnable
        ImplementaThread implementaThread = new ImplementaThread();
        //Per farlo partire richiamo il metodo run
        implementaThread.run();

        //Sincronizziamo i thread
        // All'interno del programma si possono trovare delle aree critiche, ovvero delle aree all'interno delle quali
        //l'accesso alle risorse deve essere controllato e limitato
        //Eseguendo dei metodi sincronizzati della classe EreditaThread si accede in modo controllato alle risorse tramite un mutex

        int x = 0;  //E' la risorsa critica

        //istanziamo 2 thread
        EreditaThread thread1 = new EreditaThread();

        while(true){

        }

    }
}