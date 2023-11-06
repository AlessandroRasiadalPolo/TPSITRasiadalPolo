package Database.Connector;

import Calcolatrice.RNP.Calcolatrice;
import Calcolatrice.RNP.CalculatorForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginForm {


    private JPanel pnlLogin;
    private JTextField txtNickname;
    private JPasswordField txtPassword;
    private JButton loginButton;
    private JButton registerButton;
    private DB db = new DB();

    public LoginForm() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = txtNickname.getText();
                String password = new String(txtPassword.getPassword());
                JFrame frameCalc;
                if(db.login(nickname, password) == 1){
                    frameCalc = new JFrame(DB.activeUser.getNickname());
                    frameCalc.setContentPane(new CalculatorForm().pnlCalcolatrice);
                    frameCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameCalc.setSize(450,450);
                    frameCalc.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Login fallito");
                    txtNickname.setText("");
                    txtPassword.setText("");
                }


            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registerForm = new JFrame("Registrazione");
                registerForm.setContentPane(new RegisterForm().pnlRegister);
                registerForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                registerForm.pack();
                registerForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().pnlLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
