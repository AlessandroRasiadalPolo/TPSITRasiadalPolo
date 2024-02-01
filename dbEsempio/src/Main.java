public class Main {
    public static void main(String[] args) {
        DB db = new DB();
        System.out.println(db.selectAll());
        Persona p = new Persona("Alessandro", "Rasia", "M", 10);
        if(db.insert(p) == 1)
            System.out.println(db.selectAll());
        else
            System.out.println("C'è stato un errore");

        if(db.updateByName("Alessandro", "Giacomo") == 1)
            System.out.println(db.selectAll());
        else
            System.out.println("C'è stato un errore");

        if(db.updateByNameOnce("Giacomo", "Alessandro") == 1)
            System.out.println(db.selectAll());
        else
            System.out.println("C'è stato un errore");

        if(db.deleteByName("Giacomo") == 1)
            System.out.println(db.selectAll());
        else
            System.out.println("C'è stato un errore");



    }
}