package Database.Connector;

public class User {
    private String Nickname;
    private String password;

    public User()
    {
        Nickname = "";
        password = "";
    }

    public User(String Nickname, String password) {
        this.Nickname = Nickname;
        this.password = password;
    }


    public String getName() {
        return Nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.Nickname = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}