package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TicTacToeUI extends JFrame {
    public JPanel rootPanel;
    private JPanel TicTacToeField;
    private JButton newGame;
    private JPanel Spielstand;
    private JLabel Player1_name;
    private JLabel Player2_name;
    private JLabel Vs;
    private JLabel numberfield_seperator;
    private JLabel Player2_score;
    private JLabel Player1_score;
    private JPanel SouthPanel;
    private JPanel EastPanel;
    private JPanel WestPanel;
    private Spielbrett spielfeld = new Spielbrett();
    private Marker Kreis = new Marker('O');
    private Marker Kreuz = new Marker('X');
    private Marker SymbolAnDerReihe = Kreis;
    private MinMaxAI AI = new MinMaxAI();
    private Integer player1_score_int = 0;
    private Integer player2_score_int = 0;
    private Player Player1;
    private Player Player2;


    public TicTacToeUI(String Modus, String player1_name, String player2_name) {
        Player1 = new Player(player1_name, Kreis, true);
        Player2 = new Player(player2_name, Kreuz, false);
        Player1_name.setText(Player1.getName() + ": " + Player1.getMarker().getZeichen() + "  ");
        Player2_name.setText(Player2.getName() + ": " + Player2.getMarker().getZeichen() + "  ");
        Player1_score.setText(String.valueOf(player1_score_int));
        Player2_score.setText(String.valueOf(player2_score_int));

        if ("Player".equals(Modus)) {
            TicTacToeField.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    setSpielfeld(e.getX(), e.getY());
                    checkWin();
                    checkVollesSpielfeld();
                }
            });

            newGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    zeichneLeeresSpielfeld();
                    spielfeld.fuelleFelder();
                    System.out.println(spielfeld);
                    System.out.println(Player1.getName() + " " + Player1.getMarker());
                    System.out.println(Player2.getName() + " " + Player2.getMarker());
                }
            });
        }

        if ("Computer".equals(Modus)) {
            TicTacToeField.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {

                    boolean feld_frei = setSpielfeld(e.getX(), e.getY());
                    if (feld_frei) {
                        checkWin();
                        if (checkVollesSpielfeld()) {

                        } else {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                            }
                            ;
                        }
                        ComputerSpielt(spielfeld, SymbolAnDerReihe);
                        if(checkWin()){
                            if(Player2.isStartet()){
                                ComputerSpielt(spielfeld,SymbolAnDerReihe);
                            }
                        }
                        if(checkVollesSpielfeld()){
                            if(Player2.isStartet()){
                                ComputerSpielt(spielfeld,SymbolAnDerReihe);
                            }
                        }
                    }
                }
            });

            newGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    zeichneLeeresSpielfeld();
                    spielfeld.fuelleFelder();
                    System.out.println(spielfeld);
                    System.out.println(Player1.getName() + " " + Player1.getMarker());
                    System.out.println(Player2.getName() + " " + Player2.getMarker());
                }
            });
        }


    }

    private void zeichneLeeresSpielfeld() {
        Graphics2D g2d = (Graphics2D) TicTacToeField.getGraphics();
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(4));
        g2d.fillRect(0, 0, TicTacToeField.getWidth(), TicTacToeField.getHeight());
        g2d.setColor(Color.black);
        g2d.drawLine(100, 0, 100, 300);
        g2d.drawLine(200, 0, 200, 300);
        g2d.drawLine(0, 100, 300, 100);
        g2d.drawLine(0, 200, 300, 200);

    }

    private boolean setSpielfeld(int x, int y) {

        if (x < 100) {
            x = 0;
        } else {
            if (x > 100 && x < 200) {
                x = 1;
            } else {
                if (x > 200 && x < 300) {
                    x = 2;
                }
            }
        }

        if (y < 100) {
            y = 0;
        } else {
            if (y > 100 && y < 200) {
                y = 1;
            } else {
                if (y > 200 && y < 300) {
                    y = 2;
                }
            }
        }
        if (!String.valueOf(spielfeld.holeMarker(y, x).getZeichen()).equals("N")) {
            return false;
        }

        spielfeld.setzeMarker(y, x, SymbolAnDerReihe.getZeichen());

        if (SymbolAnDerReihe.equals(Kreis)) {
            zeichneKreis(x, y);
            SymbolAnDerReihe = Kreuz;
        } else {
            zeichneKreuz(x, y);
            SymbolAnDerReihe = Kreis;
        }

        spielfeld.gibSpielbrettAusTemp();
        return true;
    }

    private void zeichneKreis(int x, int y) {
        Graphics2D g2d = (Graphics2D) TicTacToeField.getGraphics();
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(7));

        switch (x) {
            case 0:
                x = 50;
                break;
            case 1:
                x = 150;
                break;
            case 2:
                x = 250;
                break;
        }
        switch (y) {
            case 0:
                y = 50;
                break;
            case 1:
                y = 150;
                break;
            case 2:
                y = 250;
                break;
        }
        g2d.drawOval(x - 40, y - 40, 80, 80);

    }

    private void zeichneKreuz(int x, int y) {
        Graphics2D g2d = (Graphics2D) TicTacToeField.getGraphics();
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(7));

        switch (x) {
            case 0:
                x = 0;
                break;
            case 1:
                x = 100;
                break;
            case 2:
                x = 200;
                break;
        }
        switch (y) {
            case 0:
                y = 0;
                break;
            case 1:
                y = 100;
                break;
            case 2:
                y = 200;
                break;
        }

        g2d.drawLine(x + 10, y + 10, x + 90, y + 90);
        g2d.drawLine(x + 90, y + 10, x + 10, y + 90);
    }

    private boolean checkWin() {
        if (spielfeld.checkWinZeichen(Kreis.getZeichen())) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            ;
            spielfeld.fuelleFelder();
            zeichneLeeresSpielfeld();
            System.out.println("Kreis hat gewonnen");
            Player Winner = CheckWinner(Kreis);
            switchPlayerStartet();
            return true;
        }

        if (spielfeld.checkWinZeichen(Kreuz.getZeichen())) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            ;
            spielfeld.fuelleFelder();
            zeichneLeeresSpielfeld();
            System.out.println("Kreuz hat gewonnen");
            Player Winner = CheckWinner(Kreuz);
            switchPlayerStartet();
            return true;
        }
        return false;
    }

    private boolean checkVollesSpielfeld() {
        if (spielfeld.checkSpielfeldVoll()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            ;
            spielfeld.fuelleFelder();
            zeichneLeeresSpielfeld();
            switchPlayerStartet();
            return true;
        }
        return false;
    }

    private void switchPlayerStartet() {
        if (Player1.isStartet()) {
            Player1.setStartet(false);
            Player2.setStartet(true);
            SymbolAnDerReihe = Player2.getMarker();
        } else {
            Player1.setStartet(true);
            Player2.setStartet(false);
            SymbolAnDerReihe = Player1.getMarker();
        }
    }

    private Player CheckWinner(Marker Marker) {
        if (Marker.getZeichen() == Player1.getMarker().getZeichen()) {
            player1_score_int += 1;
            Player1_score.setText(String.valueOf(player1_score_int));
            return Player1;
        } else {
            player2_score_int += 1;
            Player2_score.setText(String.valueOf(player2_score_int));
            return Player2;
        }
    }

    private void ComputerSpielt(Spielbrett spielfeld, Marker zeichen) {
        int[] ergebnisArray = new int[2];
        ergebnisArray = AI.sucheBestenZug(spielfeld);
        int reihePos = ergebnisArray[0];
        int zeilePos = ergebnisArray[1];
        spielfeld.setzeMarker(reihePos, zeilePos, zeichen.getZeichen());

        if (zeichen.equals(Kreis)) {
            zeichneKreis(zeilePos, reihePos);
            SymbolAnDerReihe = Kreuz;
        } else {
            zeichneKreuz(zeilePos, reihePos);
            SymbolAnDerReihe = Kreis;
        }
    }

    public static void main(String[] args) {
        //JFrame frame = new JFrame("TicTacToeUI");
        TicTacToeUI frame = new TicTacToeUI("Player", "Marc", "Mia");
        frame.setContentPane(new TicTacToeUI("Player", "Marc", "Mia").rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}
