public class Persona {
    private String nome;
    private String cognome;
    private String sesso;
    private int age;

    public Persona(String nome, String cognome, String sesso, int age) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.age = age;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
