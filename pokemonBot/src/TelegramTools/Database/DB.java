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


    public static int registerItems(ArrayList<Item> items){

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

    public static int registerStats(ArrayList<Pokemon> pokemons){
        if(pokemons == null)
            return -1;

        Connection conn = connect();

        try{
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Statistica(pokemonName,Atk, Spe, Def, SAtk, SDef, PS) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for(Pokemon pokemon : pokemons){
                preparedStatement.setString(1,pokemon.getPokemonName());
                preparedStatement.setInt(2,pokemon.getStats().getAtt());
                preparedStatement.setInt(3, pokemon.getStats().getSpe());
                preparedStatement.setInt(4,pokemon.getStats().getDef());
                preparedStatement.setInt(5,pokemon.getStats().getSpa());
                preparedStatement.setInt(6,pokemon.getStats().getSpd());
                preparedStatement.setInt(7,pokemon.getStats().getPs());
                preparedStatement.executeUpdate();
            }

            return 1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int registerAbility(ArrayList<Ability> abilities){
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

    public static int registerType()


    public static int RegisterPokemon(ArrayList<Pokemon> pokemons) {

        if (pokemons == null)
            return -1;

        Connection conn = connect();

        try {
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO pokemon(NomePokemon,PokedexId,NomeTipo1, NomeTipo2, Generazione) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            for(Pokemon pokemon : pokemons) {
                preparedStatement.setString(1, pokemon.getPokemonName());
                preparedStatement.setInt(2,pokemon.getPokedexNumber());
                preparedStatement.setString(3,pokemon.getPrimaryType());
                preparedStatement.setString(4, pokemon.getSecondaryType());
                preparedStatement.setString(5,pokemon.getGeneration());

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
