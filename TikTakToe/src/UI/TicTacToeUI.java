package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeUI extends JFrame{
    private static final String Kreis= "KREIS";
    private static final String Kreuz = "KREUZ";
    public JPanel rootPanel;
    private JPanel TicTacToeField;
    private JButton newGame;
    private String [] [] spielfeld = new String[3][3];
    private String SymbolAnDerReihe = Kreis;

    public TicTacToeUI() {
        TicTacToeField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX()+ " " + e.getY());
                setSpielfeld(e.getX(),e.getY());
            }
        });

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zeichneLeeresSpielfeld();
            }
        });


    }

    private void zeichneLeeresSpielfeld(){
        Graphics2D g2d = (Graphics2D) TicTacToeField.getGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,TicTacToeField.getWidth(),TicTacToeField.getHeight());
        g2d.setColor(Color.black);
        g2d.drawLine(100,0,100,300);
        g2d.drawLine(200,0,200,300);
        g2d.drawLine(0,100,300,100);
        g2d.drawLine(0,200,300,200);

    }



    private void setSpielfeld(int x,int y){

        if (x < 100){
            x = 0;
        }else{
            if (x > 100 && x < 200){
                x = 1;
            }else{
                if(x > 200 && x < 300){
                    x = 2;
                }
            }
        }

        if (y < 100){
            y = 0;
        }else{
            if (y > 100 && y < 200){
                y = 1;
            }else{
                if(y > 200 && y < 300){
                    y = 2;
                }
            }
        }

        if (spielfeld[y][x] != null){
            return;
        }

        spielfeld[y][x] = SymbolAnDerReihe;

        if (SymbolAnDerReihe.equals(Kreis)){
            zeichneKreis(x,y);
            SymbolAnDerReihe = Kreuz;
        }else{
            zeichneKreuz(x,y);
            SymbolAnDerReihe = Kreis;
        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(spielfeld[i][j] + " | ");
            }
            System.out.println();
        }
    }

    private void zeichneKreis(int x, int y){
        Graphics2D g2d = (Graphics2D) TicTacToeField.getGraphics();
        g2d.setColor(Color.black);

        switch (x){
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
        switch (y){
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
        g2d.drawOval(x - 40,y - 40,80,80);

    }

    private void zeichneKreuz(int x, int y){
        Graphics2D g2d = (Graphics2D) TicTacToeField.getGraphics();
        g2d.setColor(Color.black);

        switch (x){
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
        switch (y){
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

        g2d.drawLine(x + 10,y + 10 ,x + 90,y + 90);
        g2d.drawLine(x+90, y + 10,x + 10,y+90);
    }


    public static void main(String[] args) {
        //JFrame frame = new JFrame("TicTacToeUI");
        TicTacToeUI frame = new TicTacToeUI();
        frame.setContentPane(new TicTacToeUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



}
