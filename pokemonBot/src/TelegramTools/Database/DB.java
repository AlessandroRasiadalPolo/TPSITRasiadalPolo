package TelegramTools.Database;

import Entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class DB {


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



}
