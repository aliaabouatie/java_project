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
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton newPlayerButton;
    private JList leaderboard;
    private JButton startGameButton;

    public MainMenu() {
        String[] PlayerArray = getPlayers();
        System.out.println(PlayerArray);
        String[] PlayerArray2 = {"Marc","Mia"};
        comboBox1 = new JComboBox(PlayerArray2);



        newPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewPlayer dialog = new NewPlayer();
                dialog.pack();
                dialog.setVisible(true);

            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PlayerVsPlayer.isSelected()) {
                    TicTacToeUI frame = new TicTacToeUI("Player", "Marc", "Mia");
                    frame.setContentPane(new TicTacToeUI("Player", "Marc", "Mia").rootPanel);
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                }
                if (PlayerVsComp.isSelected()) {
                    TicTacToeUI frame = new TicTacToeUI("Computer", "Marc", "Computer");
                    frame.setContentPane(new TicTacToeUI("Computer", "Marc", "Computer").rootPanel);
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                }
            }
        });
    }

    private String[] getPlayers() {
        ArrayList<String> PlayerArray= new ArrayList<String>();

        try {
            Path PlayerDatei = Paths.get("players.csv");

            BufferedReader meinReader = Files.newBufferedReader(PlayerDatei);
            String zeile = meinReader.readLine(); // erste Zeile lesen while (zeile != null) { // null wenn am Dateiende
            zeile = meinReader.readLine(); // naechste Zeile lesen
            while (zeile != null){
                PlayerArray.add(zeile);
                zeile = meinReader.readLine();
            }

            meinReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] PlayerList = new String[PlayerArray.size()];
        for(int i = 0; i < PlayerArray.size(); i++){
            PlayerList[i] = (PlayerArray.get(i));
            System.out.println(String.valueOf(PlayerArray.get(i)));
        }
        System.out.println(PlayerList.length);
        return PlayerList;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        //frame.getContentPane().setBackground(Color.blue);
    }

}
