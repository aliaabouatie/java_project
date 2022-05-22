package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private Spielbrett spielfeld = new Spielbrett();
    private Marker Kreis = new Marker('0');
    private Marker Kreuz = new Marker('X');
    private Marker SymbolAnDerReihe = Kreis;
    private MinMaxAI AI = new MinMaxAI();
    private Integer player1_score_int = 0;
    private Integer player2_score_int = 0;

    public TicTacToeUI(String Modus) {
        if ("Player".equals(Modus)) {
            TicTacToeField.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    setSpielfeld(e.getX(), e.getY());
                    if (spielfeld.checkSpielfeldVoll()) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        ;
                        spielfeld.fuelleFelder();
                        zeichneLeeresSpielfeld();
                    }

                    if (spielfeld.checkWinZeichen(Kreis.getZeichen())) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        ;
                        spielfeld.fuelleFelder();
                        zeichneLeeresSpielfeld();
                        System.out.println("Kreis hat gewonnen");
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
                    }

                }
            });

            newGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    zeichneLeeresSpielfeld();
                    System.out.println(spielfeld);
                }
            });
        }

        if ("Computer".equals(Modus)) {
            TicTacToeField.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    setSpielfeld(e.getX(), e.getY());

                    if (spielfeld.checkWinZeichen(Kreis.getZeichen())) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        spielfeld.fuelleFelder();
                        zeichneLeeresSpielfeld();
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
                    }

                    if (spielfeld.checkSpielfeldVoll()) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        ;
                        spielfeld.fuelleFelder();
                        zeichneLeeresSpielfeld();
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                    }
                    ;

                    int[] ergebnisArray = new int[2];
                    ergebnisArray = AI.sucheBestenZug(spielfeld);
                    int reihePos = ergebnisArray[0];
                    int zeilePos = ergebnisArray[1];
                    spielfeld.setzeMarker(reihePos, zeilePos, SymbolAnDerReihe.getZeichen());

                    if (SymbolAnDerReihe.equals(Kreis)) {
                        zeichneKreis(zeilePos, reihePos);
                        SymbolAnDerReihe = Kreuz;
                    } else {
                        zeichneKreuz(zeilePos, reihePos);
                        SymbolAnDerReihe = Kreis;
                    }

                    if (spielfeld.checkWinZeichen(Kreis.getZeichen())) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        ;
                        spielfeld.fuelleFelder();
                        zeichneLeeresSpielfeld();
                        System.out.println("Kreis hat gewonnen");
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
                    }
                    if (spielfeld.checkSpielfeldVoll()) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                        }
                        ;
                        spielfeld.fuelleFelder();
                        zeichneLeeresSpielfeld();
                    }
                }
            });

            newGame.addActionListener(new

                                              ActionListener() {
                                                  @Override
                                                  public void actionPerformed(ActionEvent e) {
                                                      zeichneLeeresSpielfeld();
                                                      System.out.println(spielfeld);
                                                  }
                                              });
        }
    }

    private void zeichneLeeresSpielfeld() {
        Graphics2D g2d = (Graphics2D) TicTacToeField.getGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, TicTacToeField.getWidth(), TicTacToeField.getHeight());
        g2d.setColor(Color.black);
        g2d.drawLine(100, 0, 100, 300);
        g2d.drawLine(200, 0, 200, 300);
        g2d.drawLine(0, 100, 300, 100);
        g2d.drawLine(0, 200, 300, 200);

    }

    private void setSpielfeld(int x, int y) {

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

        //if (spielfeld[y][x] != null){
        //  return;
        //}
        if (spielfeld.holeMarker(y, x).equals('N')) {
            return;
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
    }

    private void zeichneKreis(int x, int y) {
        Graphics2D g2d = (Graphics2D) TicTacToeField.getGraphics();
        g2d.setColor(Color.black);

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


    public static void main(String[] args) {
        //JFrame frame = new JFrame("TicTacToeUI");
        TicTacToeUI frame = new TicTacToeUI("Player");
        frame.setContentPane(new TicTacToeUI("Player").rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}
