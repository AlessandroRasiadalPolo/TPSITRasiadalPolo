package Calcolatrice.RNP;

import Database.Connector.DB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorForm {
    public JPanel pnlCalcolatrice;
    private JLabel lblOutput;
    private JButton a0Button;
    private JButton btn7;
    private JButton btn6;
    private JButton btn4;
    private JButton btn9;
    private JButton btn3;
    private JButton a1Button;
    private JButton btnMultiply;
    private JButton btn8;
    private JButton btn5;
    private JButton a2Button;
    private JButton btnDivide;
    private JButton btnAdd;
    private JButton btnMinus;
    private JButton btnExecute;
    private JRadioButton rdbNormal;
    private JRadioButton rdbRPN;
    private JButton btnOpenPar;
    private JButton btnRemove;
    private JButton bntClosePar;
    private List<String> equazione;
    private Calcolatrice calcolatrice;
    boolean isOperator = false;
    private int indexS = 0;


    private String setValues(String s) {
        String str = "";
        if(s.charAt(0) == '[')
            str = s.replaceAll("[\\[\\] ,]", "");

        return str;

    }




    public CalculatorForm() {
        equazione = new ArrayList<>();
        calcolatrice = new Calcolatrice();

        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("7");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;

                }
                else
                    equazione.set(indexS,equazione.get(indexS)+"7");

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("8");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;

                }

                else
                    equazione.set(indexS,equazione.get(indexS)+"8");

                lblOutput.setText(setValues(equazione.toString()));


            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("9");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;

                }
                else
                    equazione.set(indexS,equazione.get(indexS)+"9");

                lblOutput.setText(setValues(equazione.toString()));


            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("4");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;

                }
                else
                    equazione.set(indexS,equazione.get(indexS)+"4");

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("5");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;

                }
                else
                    equazione.set(indexS,equazione.get(indexS)+"5");

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("6");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;

                }
                else
                    equazione.set(indexS,equazione.get(indexS)+"6");

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("3");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;

                }

                else
                    equazione.set(indexS,equazione.get(indexS)+"3");

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("2");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;
                }
                else
                    equazione.set(indexS,equazione.get(indexS)+"2");

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(equazione.isEmpty() || isOperator || rdbRPN.isSelected()) {
                    equazione.add("1");
                    if(isOperator) {
                        indexS++;
                        equazione.add("");
                    }
                    isOperator = false;

                }
                else
                    equazione.set(indexS,equazione.get(indexS)+"1");

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!equazione.isEmpty()) {
                    equazione.set(indexS, equazione.get(indexS) + "0");
                }

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btnDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!equazione.isEmpty()) {
                    equazione.add("/");
                    indexS++;
                    isOperator = true;
                }

                lblOutput.setText(setValues(equazione.toString()));


            }
        });
        btnMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!equazione.isEmpty()) {
                    equazione.add("*");
                    indexS++;
                    isOperator = true;
                }

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btnExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB db = new DB();
                float ris = 0;

                //Rimuove TUTTI i valori che soddisfano la condizione
                equazione.removeIf(String::isEmpty);
                if(rdbNormal.isSelected()) {
                    try {
                        String[] equazioneArray = equazione.toArray(new String[0]);
                        ris = calcolatrice.calcolaRPM(calcolatrice.traduciRPN(equazioneArray));

                    } catch (Exception ex) {
                        ris = -104;
                    }
                }
                else if(rdbRPN.isSelected()) {
                    try {
                        String[] equazioneArray = equazione.toArray(new String[0]);
                        ris = calcolatrice.calcolaRPM(equazioneArray);

                    } catch (Exception ex) {
                        ris = -104;
                    }

                }

                //Salvo il risultato ottenuto
                db.registraCronologia(lblOutput.getText());

                lblOutput.setText(Float.toString(ris));
                equazione.clear();
                indexS = 0;

            int scelta = JOptionPane.showConfirmDialog(null, "Vuoi vedere la tua cronologia?", "si", JOptionPane.YES_NO_OPTION);
             if(scelta == JOptionPane.YES_OPTION)
                 JOptionPane.showMessageDialog(null, db.stampaInformazioni());

            }
        });
        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!equazione.isEmpty()) {
                    equazione.add("-");
                    indexS++;
                    isOperator = true;
                }

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!equazione.isEmpty()) {
                    equazione.add("+");
                    indexS++;
                    isOperator = true;
                }

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btnOpenPar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!equazione.isEmpty()) {
                    equazione.add("(");
                    indexS++;
                    isOperator = true;
                }

                lblOutput.setText(setValues(equazione.toString()));
            }
        });
        bntClosePar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!equazione.isEmpty()) {
                    indexS++;
                    equazione.add(")");
                    isOperator = true;
                }

                lblOutput.setText(setValues(equazione.toString()));

            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if(!equazione.isEmpty())
                    equazione.remove(equazione.size()-1);
                if(equazione.get(indexS).isEmpty())
                    indexS--;

                lblOutput.setText(setValues(equazione.toString()));*/
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CalculatorForm");
        frame.setContentPane(new CalculatorForm().pnlCalcolatrice);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,450);
        frame.setVisible(true);
    }
}
