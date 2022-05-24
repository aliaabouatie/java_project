package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.nio.file.*;

public class NewPlayer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfPlayername;

    public NewPlayer() {
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        //setLocationRelativeTo(null);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    private void onOK() {
        if (!tfPlayername.getText().equals("")) {
            try {
                Path playerDatei = Paths.get("players.csv");
                BufferedWriter meinWriter = Files.newBufferedWriter(playerDatei, StandardOpenOption.APPEND);

                meinWriter.write(tfPlayername.getText() + "\n");
                meinWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        MainMenu.closeWindow();
        MainMenu.main(null);
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        NewPlayer dialog = new NewPlayer();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
