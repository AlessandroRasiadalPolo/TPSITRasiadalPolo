package Database.Connector;

import Calcolatrice.RNP.CalculatorForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterForm {
    private JButton registerButton;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtNickName;
    private JPasswordField txtPassword;
    private JTextField txtAge;
    public JPanel pnlRegister;


    public RegisterForm() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB db = new DB();
                JFrame frameCalc;
                String name = txtName.getText();
                String surname = txtSurname.getText();
                String nickname = txtNickName.getText();
                String password = new String(txtPassword.getPassword());
                int age = Integer.parseInt(txtAge.getText());

                try {
                    if(db.register(name, surname, nickname, password, age) == 1){
                        frameCalc = new JFrame("Calcolatrice");
                        frameCalc.setContentPane(new CalculatorForm().pnlCalcolatrice);
                        frameCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frameCalc.setSize(450,450);
                        frameCalc.setVisible(true);
                    }
                    else if(db.register(name, surname, nickname, password, age) == -2)
                        JOptionPane.showMessageDialog(null, "Questo Nickname è già in uso!");
                    else
                        JOptionPane.showMessageDialog(null, "Login fallito!");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RegisterForm");
        frame.setContentPane(new RegisterForm().pnlRegister);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
