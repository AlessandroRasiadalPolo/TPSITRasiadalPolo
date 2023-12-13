package TelegramTools.Database;

import Entities.Move;
import Entities.Pokemon;
import Entities.PokemonTeam;
import Entities.Stats;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class DbObtainer extends DB{

    public static Pokemon ottieniPokemon(String nomePokemon){
        if(nomePokemon.isEmpty())
            return null;

        Connection con = connect();

        try{
            Statement stmt = con.createStatement();
            String sql = "SELECT P.NomePokemon AS NP, P.PokedexId, P.NomeTipo1, P.NomeTipo2, P.Generazione, P.Icon, S.NomeAbilità, A.Effetto, ST.Atk, ST.Spe, ST.Def, ST.SAtk, ST.SDef, ST.PS  FROM Pokemon AS P INNER JOIN Sviluppa AS S ON (P.NomePokemon = S.NomePokemon) INNER JOIN Abilità AS A ON (A.Nome = S.NomeAbilità) INNER JOIN Statistica AS ST ON(P.NomePokemon = ST.PokemonName) WHERE P.NomePokemon = ?;";

            PreparedStatement preparedStatementInsert = con.prepareStatement(sql);
            preparedStatementInsert.setString(1, nomePokemon);

            ResultSet resultSet = preparedStatementInsert.executeQuery();
            Pokemon pokemon = new Pokemon();
            while(resultSet.next()){

                //Salvo le prime informazioni
                pokemon.setPokemonName(resultSet.getString("NP"));
                pokemon.setPokedexNumber(resultSet.getInt("PokedexId"));
                pokemon.setGeneration(resultSet.getString("Generazione"));
                pokemon.setIcon(resultSet.getString("Icon"));
                pokemon.setStats(new Stats(resultSet.getInt("PS"),
                        resultSet.getInt("SAtk"),
                        resultSet.getInt("SDef"),
                        resultSet.getInt("Def"),
                        resultSet.getInt("Atk"),
                        resultSet.getInt("Spe"))
                );

                //Salvo tutte le abilità
                pokemon.getAbilities().add(resultSet.getString("NomeAbilità") + ": " + resultSet.getString("Effetto") + "\n");

                pokemon.setPrimaryType(resultSet.getString("NomeTipo1"));
                pokemon.setSecondaryType(resultSet.getString("NomeTipo2"));
            }

            stmt.close();
            con.close();

            return pokemon;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ArrayList<String> ottieniAbilità(String ability){
        if(ability == null)
            return null;
        ArrayList<String> pokemons = new ArrayList<>();
        Connection con = connect();

        try{
            Statement smt = con.createStatement();
            String sql = "SELECT P.NomePokemon FROM Pokemon P INNER JOIN Sviluppa AS S ON (P.NomePokemon = S.NomePokemon) INNER JOIN Abilità AS A ON (A.Nome = S.NomeAbilità) WHERE S.NomeAbilità = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ability);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                pokemons.add(resultSet.getString("NomePokemon"));
            }

            smt.close();
            con.close();

            return pokemons;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static String[] ottieniAbilitàperPokemon(String pokemon){

        if(pokemon == null)
            return null;

        String[] abilità = new String[3];
        Connection con = connect();

        try{


            Statement smt = con.createStatement();
            String sql = "SELECT A.Nome FROM Abilità A INNER JOIN Sviluppa S ON (A.Nome = S.NomeAbilità) INNER JOIN Pokemon P ON (P.NomePokemon = S.NomePokemon) WHERE P.NomePokemon = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, pokemon);
            ResultSet resultSet = preparedStatement.executeQuery();

            int i = 0;

            while(resultSet.next()){
                abilità[i] = resultSet.getString("Nome");
                i++;
            }

            smt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return abilità;
    }

    public static ArrayList<String> ottieniPokemonperGenerazione(String generazione){

        if(generazione == null)
            return null;

        ArrayList<String> pokemons = new ArrayList<>();
        //Sistemo i valori

        Connection con = connect();

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT NomePokemon FROM Pokemon WHERE Generazione = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, generazione);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
                pokemons.add(resultSet.getString("NomePokemon"));


            stmt.close();
            con.close();

            return pokemons;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static ArrayList<Move> ottieniMosse(String stringa){

        if(stringa == null)
            return null;

        ArrayList<Move> moves = new ArrayList<Move>();

        Connection con = connect();

        try{
            Statement stmt = con.createStatement();
            String sql = "SELECT M.* FROM Mossa M INNER JOIN IMPARA I ON (I.NomeMossa = M.Nome) INNER JOIN Pokemon P ON (I.NomePokemon = P.NomePokemon) WHERE P.NomePokemon = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, stringa);

            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){

                Move mossa = new Move();
                mossa.setName(resultSet.getString("Nome"));
                mossa.setPp(resultSet.getInt("Pp"));
                mossa.setEffetto(resultSet.getString("Effetto"));
                mossa.setPower(resultSet.getInt("Potenza"));
                mossa.setAccuracy(resultSet.getInt("Precisione"));
                mossa.setPriority(resultSet.getInt("Priorità"));
                mossa.setcategory(resultSet.getString("Categoria"));
                mossa.setTipo(resultSet.getString("Tipo"));

                moves.add(mossa);
            }
            stmt.close();
            con.close();

            return moves;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Move ottieniDettagliMossa(String mossa){

        if(mossa == null)
            return null;

        Move move = new Move();

        Connection con = connect();

        try{
            Statement stmt = con.createStatement();
            String sql = "SELECT M.* FROM Mossa M WHERE M.Nome = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, mossa);

            ResultSet resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){

                move.setName(resultSet.getString("Nome"));
                move.setPp(resultSet.getInt("Pp"));
                move.setEffetto(resultSet.getString("Effetto"));
                move.setPower(resultSet.getInt("Potenza"));
                move.setAccuracy(resultSet.getInt("Precisione"));
                move.setPriority(resultSet.getInt("Priorità"));
                move.setcategory(resultSet.getString("Categoria"));
                move.setTipo(resultSet.getString("Tipo"));


            }
            stmt.close();
            con.close();

            return move;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public PokemonTeam[] ottieniListaTeam(){

        Connection con = connect();

        try {
            Statement stmt = con.createStatement();
            String sql = "";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static LinkedList<String> savePokemonMoves() {

        Connection conn = connect();


        try {
            Statement stmt = conn.createStatement();

            LinkedList<String> pokemons = new LinkedList<String>();
            String sql = "SELECT NomePokemon FROM Pokemon";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pokemons.add(resultSet.getString("NomePokemon"));
            }

            stmt.close();
            conn.close();

            return pokemons;

        } catch (SQLException e) {
            return null;
        }
    }
    public static boolean obtainUser(String userName){

        Connection con = connect();

        try{
            Statement stmt = con.createStatement();
            String sql = "SELECT Id FROM Utente WHERE Id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                stmt.close();
                con.close();
                return true;
            }

            else {
                stmt.close();
                con.close();
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
