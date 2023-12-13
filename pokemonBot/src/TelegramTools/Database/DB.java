package TelegramTools.Database;

import Entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

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


    public static int registerItems(ArrayList<Item> items) {

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


    public static int RegisterPokemon(ArrayList<Pokemon> pokemons) {

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

    public static int savePokemonTeam(ArrayList<PokemonTeam> pokemons, String userName, String nomeTeam){


        Connection con = connect();

        try{
            //Salvo lo username dell'utente
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO Utente(Id) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.execute();

            //Salvo il nome della squadra
            String sqlTeamName = "INSERT INTO Squadra(NomeSquadra) VALUES (?)";
            PreparedStatement preparedStatementTeamName = con.prepareStatement(sqlTeamName);
            preparedStatementTeamName.setString(1,nomeTeam);
            preparedStatementTeamName.execute();

            //Salvo tutti i pokemon della squadra
            String sqlPokemon = "INSERT INTO PokemonSquadra(TeamName, PokemonName, Strumento, Abilità, Mossa1, Mossa2, Mossa3, Mossa4) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatementPokemon = con.prepareStatement(sqlPokemon);

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

            String sql = "INSERT INTO Squadra(NomeSquadra) VALUES (?)";
            PreparedStatement preparedStatementTeamName = con.prepareStatement(sqlTeamName);
            preparedStatementTeamName.setString(1,nomeTeam);
            preparedStatementTeamName.execute();


            stmt.close();
            con.close();
            return 1;


        } catch (SQLException e) {
            return -2;
        }
    }
}
