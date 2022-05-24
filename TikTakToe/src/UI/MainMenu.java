package UI;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.nio.file.*;
import java.util.*;


public class MainMenu {
    public JPanel rootPanel;
    private JLabel tictactoe;
    private JRadioButton PlayerVsPlayer;
    private JRadioButton PlayerVsComp;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton newPlayerButton;
    private JButton startGameButton;
    private JTable leaderboard;
    private static JFrame frame = new JFrame("MainMenu");

    public MainMenu() {
        //$$$setupUI$$$();
        createUIComponents();
        addComboBoxen();
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
                    TicTacToeUI frame = new TicTacToeUI("Player", String.valueOf(comboBox1.getSelectedItem()), String.valueOf(comboBox2.getSelectedItem()));
                }
                if (PlayerVsComp.isSelected()) {
                    TicTacToeUI frame = new TicTacToeUI("Computer", String.valueOf(comboBox1.getSelectedItem()), String.valueOf(comboBox2.getSelectedItem()));
                }
            }
        });

        PlayerVsComp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PlayerVsComp.isSelected()) {
                    comboBox2.setSelectedIndex(0);
                    comboBox2.setEnabled(false);
                }
            }
        });
        PlayerVsPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PlayerVsPlayer.isSelected()) {
                    comboBox2.setEnabled(true);
                }
            }
        });
    }

    private ArrayList<String> getPlayers() {
        ArrayList<String> players = new ArrayList<String>();
        try {
            Path PlayerDatei = Paths.get("players.csv");

            BufferedReader meinReader = Files.newBufferedReader(PlayerDatei);
            String zeile = meinReader.readLine(); // erste Zeile lesen while (zeile != null) { // null wenn am Dateiende
            zeile = meinReader.readLine(); // naechste Zeile lesen
            while (zeile != null) {
                players.add(zeile);
                zeile = meinReader.readLine();
            }

            meinReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    private void addComboBoxen() {
        ArrayList<String> players = getPlayers();
        for (int i = 0; i < players.size(); i++) {
            comboBox1.addItem(players.get(i));
            comboBox2.addItem(players.get(i));
        }
    }

    public String[][] getLeaderboard() {
        String[][] leaderboardArr = new String[6][3];
        ArrayList<String> player = getPlayers();
        String[][] wins = new String[getPlayers().size()][2];
        for (int i = 0; i < player.size(); i++) {
            wins[i][0] = player.get(i);
            wins[i][1] = "0";
        }
        try {
            Path StatisticDatei = Paths.get("statistics.csv");

            BufferedReader meinReader = Files.newBufferedReader(StatisticDatei);
            String zeile = meinReader.readLine(); // erste Zeile lesen while (zeile != null) { // null wenn am Dateiende
            zeile = meinReader.readLine(); // naechste Zeile lesen

            while (zeile != null) {
                StringTokenizer st = new StringTokenizer(zeile, ",");
                String Player1 = st.nextToken();
                String Player2 = st.nextToken();
                String result1 = st.nextToken();
                zeile = meinReader.readLine();
                st = new StringTokenizer(zeile, ",");
                st.nextToken();
                st.nextToken();
                String result2 = st.nextToken();

                for (int i = 0; i < player.size(); i++) {
                    if (result1.equals("win")) {

                        if (wins[i][0].equals(Player1)) {
                            wins[i][1] = String.valueOf(Integer.parseInt(wins[i][1]) + 1);
                        }

                    } else {
                        if (result2.equals("win")) {
                            if (wins[i][0].equals(Player2)) {
                                wins[i][1] = String.valueOf(Integer.parseInt(wins[i][1]) + 1);
                            }
                        }
                    }
                }
                zeile = meinReader.readLine();
            }

            meinReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Arrays.sort(wins, Comparator.comparingInt(o -> Integer.parseInt(o[1])));
        int rank = 1;
        leaderboardArr[0][0] = "Rank";
        leaderboardArr[0][1] = "Player";
        leaderboardArr[0][2] = "Wins";
        for (int i = player.size() - 1; i > player.size() - 6; i--) {
            leaderboardArr[rank][0] = String.valueOf(rank);
            leaderboardArr[rank][1] = wins[i][0];
            leaderboardArr[rank][2] = wins[i][1];
            rank += 1;

        }
        return leaderboardArr;
    }

    public static void closeWindow() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public static void main(String[] args) {
        //JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().rootPanel);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String columns[] = {"Rank", "Player", "Wins"};
        leaderboard = new JTable(getLeaderboard(), columns);


    }

}
