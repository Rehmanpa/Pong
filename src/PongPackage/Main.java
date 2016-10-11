package PongPackage;

import javax.swing.*;
import java.awt.*;

public class Main {

    static void choice() {
        JFrame choice = new JFrame("What would you like?");
        String z = JOptionPane.showInputDialog(choice, "Do you want to play Single or Multiplayer Pong? Please type 'Singleplayer/Multiplayer'");
        if (z.equals("Singleplayer") || z.equals("singleplayer")) {
            System.out.println("Single Player");
        } else if (z.equals("Multiplayer") || z.equals("multiplayer")) {
            System.out.println("Multiplayer");
        } else {
            JOptionPane.showMessageDialog(choice, "That is not a valid option. Please type in either 'Singleplayer' or 'Multiplayer'");
            choice();
        }
    }

    //Starts the entire program
    public static void main(String[] args) {
        //Starts the choice class
        choice();
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        SinglePlayerPongPanel singlePongPanel = new SinglePlayerPongPanel();
        frame.add(singlePongPanel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}