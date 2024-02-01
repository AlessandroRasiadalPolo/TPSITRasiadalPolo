import java.sql.*;

public class DB {


    private Connection connect(){

        final String USERNAME = "root";
        final String PASSWORD = "";
        final String DB_NAME = "dbEsempio";
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, USERNAME, PASSWORD);
            System.out.println("Connessione avvenuta");

            return connection;
        } catch (SQLException e) {
            System.out.println("Connessione fallita");

        }
        return null;
    }


    public String selectAll(){

        Connection conn = connect();
        String ris = "I dati del database sono:\n";


        String q = "Select * FROM Persone";
        try {
            Statement stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery(q);

            while(resultSet.next()){
                ris += resultSet.getInt("id") +
                        "\t\n" + resultSet.getString("nome") + "\t\n" +
                        resultSet.getString("cognome") + "\t\n" + resultSet.getInt("eta") + "\t\n" + resultSet.getString("sesso");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return ris;
    }


    public int insert(Persona p){

        Connection con = connect();

        String q = "Insert into persone (nome, cognome, eta, sesso) values (?,?,?,?)";

        try {

            PreparedStatement preparedStatement = con.prepareStatement(q);
            preparedStatement.setString(1, p.getNome());
            preparedStatement.setString(2, p.getCognome());
            preparedStatement.setInt(3, p.getAge());
            preparedStatement.setString(4, p.getSesso());
            preparedStatement.executeUpdate();
            return 1;

        } catch (SQLException e) {
            return -1;
        }
    }


    public int updateByName(String nome, String newName) {
        Connection con = connect();

        String q = "UPDATE Persone SET Nome = ? WHERE Nome = ?";

        try {

            PreparedStatement preparedStatement = con.prepareStatement(q);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, nome);
            preparedStatement.executeUpdate();

            return 1;

        } catch (SQLException e) {
            return -1;
        }
    }

    public int updateByNameOnce(String nome, String newName) {

        Connection con = connect();

        String q = "UPDATE Persone SET Nome = ? WHERE Nome = ? LIMIT 1";

        try {

            PreparedStatement preparedStatement = con.prepareStatement(q);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, nome);
            preparedStatement.executeUpdate();

            return 1;

        } catch (SQLException e) {
            return -1;
        }

    }

    public int deleteByName(String nome) {
        Connection con = connect();

        String q = "DELETE FROM Persone WHERE Nome = ?";

        try {

            PreparedStatement preparedStatement = con.prepareStatement(q);
            preparedStatement.setString(1, nome);
            preparedStatement.executeUpdate();

            return 1;

        } catch (SQLException e) {
            return -1;
        }
    }
}
