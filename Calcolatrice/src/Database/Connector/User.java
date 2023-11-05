package Database.Connector;

public class User {
    private String Name;
    private String Surname;
    private int age;
    private String Nickname;
    private String password;

    public User()
    {
        Nickname = "";
        password = "";
        Name = "";
        Surname = "";
        age = 1;
    }

    public User(String name, String surname, int age, String nickname, String password) {
        Name = name;
        Surname = surname;
        this.age = age;
        Nickname = nickname;
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

    public String getSurname() {
        return Surname;
    }

    public int getAge() {
        return age;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }
}