package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MainMenu(){

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
                    TicTacToeUI frame = new TicTacToeUI();
                    frame.setContentPane(new TicTacToeUI().rootPanel);
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                if (PlayerVsComp.isSelected()){
                    TicTacToeUI frame = new TicTacToeUI();
                    frame.setContentPane(new TicTacToeUI().rootPanel);
                    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(new MainMenu().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}
