package TelegramTools.Database;

import Entities.*;
import TelegramTools.Database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbSaver extends DB {


    public static int registerItems(ArrayList<Item> items){

        if (items == null)
            return -1;

        Connection conn = connect();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Strumento(Nome, Effetto) VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for (Item item : items) {
                preparedStatement.setString(1, item.getItemName());
                preparedStatement.setString(2, item.getItemDescription());
                preparedStatement.executeUpdate();
            }

            stmt.close();
            conn.close();

            return 1;

        } catch (SQLException e) {
            return -2;
        }
    }

    public static int registerStats(ArrayList<Pokemon> pokemons) {
        if (pokemons == null)
            return -1;

        Connection conn = connect();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Statistica(pokemonName,Atk, Spe, Def, SAtk, SDef, PS) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for (Pokemon pokemon : pokemons) {
                preparedStatement.setString(1, pokemon.getPokemonName());
                preparedStatement.setInt(2, pokemon.getStats().getAtt());
                preparedStatement.setInt(3, pokemon.getStats().getSpe());
                preparedStatement.setInt(4, pokemon.getStats().getDef());
                preparedStatement.setInt(5, pokemon.getStats().getSpa());
                preparedStatement.setInt(6, pokemon.getStats().getSpd());
                preparedStatement.setInt(7, pokemon.getStats().getPs());
                preparedStatement.executeUpdate();
            }

            stmt.close();
            conn.close();

            return 1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int registerAbility(ArrayList<Ability> abilities) {
        if (abilities == null)
            return -1;

        Connection conn = connect();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Abilità(Nome, Effetto) VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for (Ability ability : abilities) {
                preparedStatement.setString(1, ability.getAbilityName());
                preparedStatement.setString(2, ability.getAbilityEffect());
                preparedStatement.executeUpdate();
            }

            stmt.close();
            conn.close();

            return 1;

        } catch (SQLException e) {
            return -2;
        }
    }

    public static int registerType(ArrayList<Type> types) {

        if (types == null)
            return -1;

        Connection conn = connect();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Tipo(Nome) VALUES (?);";
            String sqlWeakTypes = "INSERT INTO Soffre(TipoDebole, TipoPokemon) VALUES (?,?) ";
            String sqlStrongTypes = "INSERT INTO Sopraffae(TipoForte, TipoPokemon) VALUES (?,?) ";
            String sqlImmuneTypes = "INSERT INTO Immune(TipoImmune, TipoPokemon) VALUES (?,?) ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            PreparedStatement preparedStatementWeakTypes = conn.prepareStatement(sqlWeakTypes);
            PreparedStatement preparedStatementStrongTypes = conn.prepareStatement(sqlStrongTypes);
            PreparedStatement preparedStatementImmuneType = conn.prepareStatement(sqlImmuneTypes);


            for (Type type : types) {

                preparedStatement.setString(1, type.getNome());
                preparedStatement.executeUpdate();

            }

            for (Type type : types) {
                for (String weakType : type.getDebolezze()) {
                    preparedStatementWeakTypes.setString(1, weakType);
                    preparedStatementWeakTypes.setString(2, type.getNome());
                    preparedStatementWeakTypes.executeUpdate();
                }

                for (String strongType : type.getPuntidiForza()) {
                    preparedStatementStrongTypes.setString(1, strongType);
                    preparedStatementStrongTypes.setString(2, type.getNome());
                    preparedStatementStrongTypes.executeUpdate();
                }

                for (String immuneType : type.getImmunità()) {
                    preparedStatementImmuneType.setString(1, immuneType);
                    preparedStatementImmuneType.setString(2, type.getNome());
                    preparedStatementImmuneType.executeUpdate();
                }
            }

            stmt.close();
            conn.close();

            return 1;

        } catch (SQLException e) {
            return -2;
        }


    }

    public static int registerMoves(ArrayList<Move> moves) {
        if (moves == null)
            return -1;

        Connection conn = connect();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Mossa(Nome, Pp, Tipo, Effetto, Potenza, Precisione, Priorità, Categoria) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for (Move move : moves) {
                preparedStatement.setString(1, move.getName());
                preparedStatement.setInt(2, move.getPp());
                preparedStatement.setString(3, move.getTipo());
                preparedStatement.setString(4, move.getEffetto());
                preparedStatement.setInt(5, move.getPower());
                preparedStatement.setInt(6, move.getAccuracy());
                preparedStatement.setInt(7, move.getPriority());
                preparedStatement.setString(8, move.getcategory());
                preparedStatement.executeUpdate();
            }

            stmt.close();
            conn.close();

            return 1;

        } catch (SQLException e) {
            return -2;
        }


    }


    public static int RegisterPokemon(ArrayList<Pokemon> pokemons){

        if (pokemons == null)
            return -1;

        Connection conn = connect();

        try {
            Statement stmt = conn.createStatement();
            String sqlAbilities = "INSERT INTO Sviluppa(NomePokemon, NomeAbilità) VALUES (?,?)";
            String sqlSingleType = "INSERT INTO pokemon(NomePokemon,PokedexId,NomeTipo1,Generazione, Icon) VALUES (?,?,?,?,?)";
            String sqlDoubleType = "INSERT INTO pokemon(NomePokemon,PokedexId,NomeTipo1, NomeTipo2, Generazione, Icon) VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatementSingleType = conn.prepareStatement(sqlSingleType);
            PreparedStatement preparedStatementDoubleType = conn.prepareStatement(sqlDoubleType);
            PreparedStatement preparedStatementAbilities = conn.prepareStatement(sqlAbilities);

            for (Pokemon pokemon : pokemons) {
                if (!pokemon.getSecondaryType().isEmpty()) {
                    preparedStatementDoubleType.setString(1, pokemon.getPokemonName());
                    preparedStatementDoubleType.setInt(2, pokemon.getPokedexNumber());
                    preparedStatementDoubleType.setString(3, pokemon.getPrimaryType());
                    preparedStatementDoubleType.setString(4, pokemon.getSecondaryType());
                    preparedStatementDoubleType.setString(5, pokemon.getGeneration());
                    preparedStatementDoubleType.setString(6, pokemon.getIcon());

                    preparedStatementDoubleType.executeUpdate();
                } else {
                    preparedStatementSingleType.setString(1, pokemon.getPokemonName());
                    preparedStatementSingleType.setInt(2, pokemon.getPokedexNumber());
                    preparedStatementSingleType.setString(3, pokemon.getPrimaryType());
                    preparedStatementSingleType.setString(4, pokemon.getGeneration());
                    preparedStatementSingleType.setString(5, pokemon.getIcon());

                    preparedStatementSingleType.executeUpdate();
                }

            }
            for (Pokemon pokemon : pokemons) {
                for (String ability : pokemon.getAbilities()) {
                    preparedStatementAbilities.setString(1, pokemon.getPokemonName());
                    preparedStatementAbilities.setString(2, ability);
                    preparedStatementAbilities.executeUpdate();
                }
            }

            stmt.close();
            conn.close();

            return 1;
        } catch (SQLException e) {
            return -2;
        }

    }

    public static int saveMoves(ArrayList<String> moves, String pokemon){

        if(moves == null || pokemon.isEmpty())
            return -1;

        Connection conn = connect();

        try{
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Impara(NomePokemon, NomeMossa) VALUES (?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for(String move : moves){
                preparedStatement.setString(1,pokemon);
                preparedStatement.setString(2,move);
                preparedStatement.executeUpdate();
            }

            stmt.close();
            conn.close();

            return 1;

        } catch (SQLException e) {
            return -2;
        }

    }

    public static int creaSquadra(ArrayList<PokemonTeam> pokemons, String teamName, String idUtente){

        if(idUtente.isEmpty() || teamName.isEmpty() || pokemons == null)
            return -2;

        Connection con = connect();

        try{
            Statement stmt = con.createStatement();
            String sqlinsertPokemon = "INSERT INTO PokemonSquadra(TeamName,PokemonName, Strumento, Abilità, Mossa1, Mossa3, Mossa3, Mossa4) VALUES (?,?,?,?,?,?,?,?)";
            String sqlInsertUser = "INSERT INTO Utente(Id) VALUES (?)";
            String sqlInsertTeam = "INSERT INTO Squadra(NomeSquadra) VALUES (?)";
            String sqlInsert = "INSERT INTO Forma(IdUtente, NomeSquadra) VALUES (?,?)";

            //Salvo l'utente
            PreparedStatement preparedStatementInserUser = con.prepareStatement(sqlInsertUser);
            preparedStatementInserUser.setString(1, idUtente);
            preparedStatementInserUser.executeUpdate();


            //Salvo il nome del team
            PreparedStatement preparedStatementInsertTeam = con.prepareStatement(sqlInsertTeam);
            preparedStatementInsertTeam.setString(1, teamName);
            preparedStatementInsertTeam.executeUpdate();

            //Salvo quale utente ha creato la squadra
            PreparedStatement preparedStatementInsert = con.prepareStatement(sqlInsert);
            preparedStatementInsert.setString(1, idUtente);
            preparedStatementInsert.setString(2, teamName);
            preparedStatementInsert.executeUpdate();

            //Salvo i pokemon che formano la squadra, gli strumenti, le abilità e le mosse
            for(PokemonTeam pokemon : pokemons){
                PreparedStatement preparedStatementInsertPokemon = con.prepareStatement(sqlinsertPokemon);
                preparedStatementInsertPokemon.setString(1,teamName);
                preparedStatementInsertPokemon.setString(2,pokemon.getPokemonName());
                preparedStatementInsertPokemon.setString(3,pokemon.getStrumento());
                preparedStatementInsertPokemon.setString(4,pokemon.getAbiltià());
                preparedStatementInsertPokemon.setString(5, pokemon.getMossa1());
                preparedStatementInsertPokemon.setString(6,pokemon.getMossa2());
                preparedStatementInsertPokemon.setString(7, pokemon.getMossa3());
                preparedStatementInsertPokemon.setString(8, pokemon.getMossa4());

                preparedStatementInsertPokemon.executeUpdate();

            }


            stmt.close();
            con.close();

            return 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public static int savePokemonTeam(ArrayList<PokemonTeam> pokemons, String userName, String nomeTeam){


        Connection con = connect();

        try{
            Statement stmt = con.createStatement();
            //Salvo il nome della squadra
            String sql = "INSERT INTO Squadra(NomeSquadra) VALUES (?)";
            PreparedStatement preparedStatementTeamName = con.prepareStatement(sql);
            preparedStatementTeamName.setString(1,nomeTeam);
            preparedStatementTeamName.execute();

            //Salvo tutti i pokemon della squadra
            sql = "INSERT INTO PokemonSquadra(TeamName, PokemonName, Strumento, Abilità, Mossa1, Mossa2, Mossa3, Mossa4) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatementPokemon = con.prepareStatement(sql);

            for(PokemonTeam p : pokemons) {
                preparedStatementPokemon.setString(1, nomeTeam);
                preparedStatementPokemon.setString(2,p.getPokemonName());
                preparedStatementPokemon.setString(3,p.getStrumento());
                preparedStatementPokemon.setString(4,p.getAbiltià());
                preparedStatementPokemon.setString(5,p.getMossa1());
                preparedStatementPokemon.setString(6,p.getMossa2());
                preparedStatementPokemon.setString(7,p.getMossa3());
                preparedStatementPokemon.setString(8,p.getMossa4());
                preparedStatementPokemon.execute();
            }

            sql = "INSERT INTO Forma(IdUtente,NomeSquadra) VALUES (?,?)";
            PreparedStatement preparedStatementForma = con.prepareStatement(sql);
            preparedStatementForma.setString(1,userName);
            preparedStatementForma.setString(2,nomeTeam);
            preparedStatementForma.execute();


            stmt.close();
            con.close();
            return 1;


        } catch (SQLException e) {
            return -2;
        }
    }

    public static int saveUser(String userName) {

        Connection con = connect();

        try {

            //Salvo lo username dell'utente
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO Utente(Id) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.execute();

            stmt.close();
            con.close();

            return 1;

        } catch (SQLException e) {
            return -2;
        }

    }


    }
