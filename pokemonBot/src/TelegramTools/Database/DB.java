package TelegramTools.Database;

import Entities.Ability;
import Entities.Item;
import Entities.Pokemon;

import java.sql.*;
import java.util.ArrayList;

public class DB {


    private static Connection connect() {
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


    public int registerItems(ArrayList<Item> items){

        if(items == null)
            return -1;

        Connection conn = connect();

        try{
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Strumento(Nome, Effetto) VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for(Item item : items){
                preparedStatement.setString(1, item.getItemName());
                preparedStatement.setString(2, item.getItemDescription());
                preparedStatement.executeUpdate();
            }

            return 1;

        }catch(SQLException e){
            return -2;
        }
    }

    public int registerAbility(ArrayList<Ability> abilities){
        if(abilities == null)
            return -1;

        Connection conn = connect();

        try{
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Abilità(Nome, Effetto) VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for(Ability ability : abilities){
                preparedStatement.setString(1, ability.getAbilityName());
                preparedStatement.setString(2, ability.getAbilityEffect());
                preparedStatement.executeUpdate();
            }

            return 1;

        }catch(SQLException e){
            return -2;
        }
    }


    public static int RegisterPokemon(ArrayList<Pokemon> pokemons) {

        if (pokemons == null)
            return -1;

        Connection conn = connect();

        try {
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO pokemon(NomePokemon) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            for(Pokemon pokemon : pokemons) {
                preparedStatement.setString(1, pokemon.getPokemonName());
                preparedStatement.setInt(2,pokemon.getPokedexNumber());

                preparedStatement.executeUpdate();
            }

            stmt.close();
            conn.close();

            return 1;
        } catch (SQLException e) {
            return -2;
        }

    }
}
