package itis.rossi.quintaAII.tinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;




public class LoginForm extends JDialog{
    private JButton btnSomma;
    private JTextField txtName;
    private JPanel pnlLogin;
    private JPasswordField txtPassword;


    private User validUser;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(pnlLogin);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnSomma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String password = new String(txtPassword.getPassword());

                validUser = getAutenticatedUser(name, password);

                if(validUser != null)
                    dispose();
                else
                    JOptionPane.showMessageDialog(pnlLogin, "password or email invalid!");

            }
        });
    }

    public User getValidUser() {
        return validUser;
    }


    private User getAutenticatedUser(String name, String password){
        User user = null;

        final String DATABASE_URL = "jdbc:mysql://localhost:3306/utenti_tinder";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
           // Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE users.name =? AND users.password =?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

            stmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return user;
    }


    public static void main(String[] args) {
        LoginForm form = new LoginForm(null);
        form.setVisible(true);

        if( form.getValidUser() != null) {
            System.out.println("Autenticazione di " + form.getValidUser().getName() + "\n Avvenuta con Successo!!");
            PageForm pageForm = new PageForm(null);
            pageForm.setVisible(true);
        }
        else
            System.out.println("Autenticazione fallita");
    }
}
