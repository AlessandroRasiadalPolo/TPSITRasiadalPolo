package Database.Connector;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DB {

    private Connection connect() {
        //Prima cerco di instaurare una connessione e dopo l'utente potrà decidere come interrogare il DB
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/utenti";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Connection conn;

        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    private boolean userExists(String Nickname){
        boolean ris = false;
        try{
            Connection conn = connect();
            String sql = "SELECT Count(*) FROM utenti WHERE Nickname = ?";
            Statement stmt = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, Nickname);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.getInt(1) == 1)
                ris = true;

        }catch(SQLException e)
        {
            System.out.println("Connessione fallita!");
            e.printStackTrace();
        }
        return ris;

    }

    public int register(String Name, String Surname, String Nickname, String password, int age) throws SQLException {
        //Controllo che i valori passati non siano voti o siano accettabili
        if(Name.isEmpty() || Surname.isEmpty() || Nickname.isEmpty() || password.isEmpty() || age <= 0)
            return -1;

        //Controllo se esiste già un utente con lo stesso Nickname
        if(userExists(Nickname))
            return -2;

        Connection conn = connect();
        Statement stmt = conn.createStatement();


        String sql = "INSERT INTO utenti (Name, Surname, Nickname, Password, age) VALUES " +
                     "(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, Name);
        preparedStatement.setString(2, Surname);
        preparedStatement.setString(3, Nickname);
        preparedStatement.setString(4, password);
        preparedStatement.setInt(5, age);

        preparedStatement.executeUpdate();

        stmt.close();
        conn.close();

        return 1;

    }

    public int login(String Nickname, String password) {
        if(Nickname.isEmpty() || password.isEmpty())
            return -1;

        Connection conn = connect();


        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM utenti WHERE Nickname =? AND Password =?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Nickname);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                System.out.println("Login avvenuto con successo!");
                stmt.close();
                conn.close();
                return 1;
            }

            else{
                stmt.close();
                conn.close();
                System.out.println("Login fallito");
                return -1;
            }





        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Login fallito");
            return -1;
        }


    }
}
