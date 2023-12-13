package TelegramTools.Database;

import Entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class DB {

    //Classe con comandi di base

    protected static Connection connect() {
        //Prima cerco di instaurare una connessione e dopo l'utente potrà decidere come interrogare il DB
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/competitivepokedex";
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

    public static boolean svuotaDatabase(){


        Connection con = connect();


        try {
            Statement showTablesStatement = con.createStatement();
            ResultSet resultSet = showTablesStatement.executeQuery("SHOW TABLES");

            // Disabilita temporaneamente le chiavi esterne
            Statement disableForeignKeyChecksStatement = con.createStatement();
            disableForeignKeyChecksStatement.execute("SET FOREIGN_KEY_CHECKS = 0;");

            // Esegui l'operazione DELETE per ogni tabella
            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                Statement deleteStatement = con.createStatement();
                deleteStatement.executeUpdate("DELETE FROM " + tableName);
                deleteStatement.close();
            }

            // Chiudi il ResultSet e lo statement per l'elenco delle tabelle
            resultSet.close();
            showTablesStatement.close();

            // Riabilita le chiavi esterne
            disableForeignKeyChecksStatement.execute("SET FOREIGN_KEY_CHECKS = 1;");

            // Chiudi le risorse rimanenti
            disableForeignKeyChecksStatement.close();
            con.close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
