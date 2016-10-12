package PongPackage;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Main {
    static int whichProgramToStart; //This int tells the PSVM which program to start
    //The choice class gives the user the option to play either local multiplayer, single player with ai, or networked multiplayer
    static void choice() {
        //This starts the opening option list
        JFrame choice = new JFrame();
        Object[] startOptions = {"Single Player", "Multiplayer", "Exit"};
        int z = JOptionPane.showOptionDialog(choice, "Do you want to play Single Player or Multiplayer?", "Choose the game type", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, startOptions, startOptions[2]);
        //The null represents the Cancel button
        if (z==2) {
            int exit = JOptionPane.showConfirmDialog(choice, "Exit Pong?");
                if (exit == 0) {
                    System.exit(0); //Exits program
                } else {
                    choice(); //Restarts program
                }
            }
        //Starts the multiplayer
        else if (z == 1) {
            Object[] options = {"Network Multiplayer", "Local Multiplayer", "Exit"};
            int n = JOptionPane.showOptionDialog(choice, "Do you want to play online Multiplayer or local Multiplayer?", "Choose the Multiplayer type", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
            if (n == 0) {
                //What happens if they chose the online option?
                whichProgramToStart=1;
                System.out.println("Player is playing online multiplayer");
            } else if (n == 1) {
                //What happens if they chose local option?
                whichProgramToStart=2;
                System.out.println("Player is playing locally");
            } else if (n == 2) {
                int zz = JOptionPane.showConfirmDialog(choice, "Are you sure you want to quit?");
                if (zz == 0) {
                    JOptionPane.showMessageDialog(choice, "Thanks for playing");
                    System.exit(0); //Exits the program
                } else {
                    choice(); //Restarts the program
                }
            }
        }
        //Starts the SinglePlayer with AI
        else if (z == 0) {
            whichProgramToStart=0;
            System.out.println("Player is playing with AI");
        }
        //Gives the user 1 last option to not quit the game before doing so
        else {
            int exit = JOptionPane.showConfirmDialog(choice, "Are you sure you want to quit?");
            if (exit == 0) {
                JOptionPane.showMessageDialog(choice, "Thanks for playing");
                System.exit(0); //Ends the program upon initilization
            } else {
                choice(); //Restarts the program if they decide not to quit
            }
        }
    }
    //Manages all the program running and the base overarching program JFrame
    public static void main(String[] args) {
        //Starts the choice class
        choice();

        //This runs the single player mode
        if (whichProgramToStart == 0) {

            JFrame frame = new JFrame("Single Player");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            SinglePlayerPongPanel singlePongPanel = new SinglePlayerPongPanel();
            frame.add(singlePongPanel, BorderLayout.CENTER);
            frame.setResizable(false);
            frame.setSize(500, 500);
            frame.setVisible(true);

        } //This runs the online multiplayer mode
        else if (whichProgramToStart == 1) {

            JFrame frame = new JFrame("Online Multiplayer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            OnlineMultiplayer onlineMultiplayer = new OnlineMultiplayer();
            frame.add(onlineMultiplayer, BorderLayout.CENTER);
            frame.setResizable(false);
            frame.setSize(500, 500);
            frame.setVisible(true);

        } //This runs the local multiplayer mode
        else if (whichProgramToStart == 2) {

            JFrame frame = new JFrame("Local Multiplayer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            LocalMultiplayer localMultiplayer = new LocalMultiplayer();
            frame.add(localMultiplayer, BorderLayout.CENTER);
            frame.setResizable(false);
            frame.setSize(500, 500);
            frame.setVisible(true);
        }
    }
}