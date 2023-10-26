package itis.rossi.quintaAII.tinder;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PageForm  extends JDialog {

    private String images;

    private JPanel pnlHomePage;

    public PageForm(JFrame parent) {
        super(parent);
        setTitle("HomePage");
        setContentPane(pnlHomePage);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



    }

    private void initializeImages() {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/utenti_tinder";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT link-images FROM images AS img INNER JOIN users AS usr ON img.categoria = usr.preferenze";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //preparedStatement.setString(1, images);

            ResultSet resultSet = preparedStatement.executeQuery();

            /*if(resultSet.next()){
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            } */

            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
