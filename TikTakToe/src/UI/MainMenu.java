package UI;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainMenu {
    private JPanel rootPanel;
    private JLabel tictactoe;
    private JRadioButton PlayerVsPlayer;
    private JRadioButton PlayerVsComp;
    private String[] PlayerArray = {"Marc"};
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton newPlayerButton;
    private JButton startGameButton;

    public MainMenu() {
        getPlayers();
        newPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewPlayer dialog = new NewPlayer();
                dialog.pack();
                dialog.setVisible(true);
                comboBox1.addItem("Marc");

            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PlayerVsPlayer.isSelected()) {
                    TicTacToeUI frame = new TicTacToeUI("Player",  String.valueOf(comboBox1.getSelectedItem()), String.valueOf(comboBox2.getSelectedItem()));
                    frame.setContentPane(new TicTacToeUI("Player",   String.valueOf(comboBox1.getSelectedItem()), String.valueOf(comboBox2.getSelectedItem())).rootPanel);
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                }
                if (PlayerVsComp.isSelected()) {
                    TicTacToeUI frame = new TicTacToeUI("Computer",  String.valueOf(comboBox1.getSelectedItem()), String.valueOf(comboBox2.getSelectedItem()));
                    frame.setContentPane(new TicTacToeUI("Computer", String.valueOf(comboBox1.getSelectedItem()),String.valueOf(comboBox2.getSelectedItem())).rootPanel);
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                }
            }
        });

        PlayerVsComp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PlayerVsComp.isSelected()){
                    comboBox2.setSelectedIndex(0);
                    comboBox2.setEnabled(false);
                }
            }
        });
        PlayerVsPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PlayerVsPlayer.isSelected()){
                    comboBox2.setEnabled(true);
                }
            }
        });
    }

    private void getPlayers() {

        try {
            Path PlayerDatei = Paths.get("players.csv");

            BufferedReader meinReader = Files.newBufferedReader(PlayerDatei);
            String zeile = meinReader.readLine(); // erste Zeile lesen while (zeile != null) { // null wenn am Dateiende
            zeile = meinReader.readLine(); // naechste Zeile lesen
            while (zeile != null){
                comboBox1.addItem(zeile);
                comboBox2.addItem(zeile);
                zeile = meinReader.readLine();
            }

            meinReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        //frame.getContentPane().setBackground(Color.blue)

    }

}
