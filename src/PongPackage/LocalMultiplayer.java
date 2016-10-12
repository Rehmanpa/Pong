package PongPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;

/**
 * Created by Rehmanpa on 10/11/2016.
 */
public class LocalMultiplayer extends JPanel implements ActionListener, KeyListener{

    //Ball stuff and the speed the paddles move
    private int ballSpeed = 100;
    private int ballX = 250;
    private int ballY = 250;
    private int diameter = 20;
    private int ballDeltaX = -1;
    private int ballDeltaY = 3;
    private int paddleSpeed = 5;

    //Player 1 stuff
    private int playerOneX = 25;
    private int playerOneY=250;
    private int playerOneWidth = 10;
    private int playerOneHeight = 50;

    //Booleans
    private boolean wPressed = false;
    private boolean sPressed = false;

    //Constructs the class panel
    public LocalMultiplayer() {
        setBackground(Color.BLACK);
        //listens to key presses
        setFocusable(true);
        addKeyListener(this);

        //Determines the balls speed
        Timer timer = new Timer(1000 / ballSpeed, this); //Using the ballSpeed int allows the speed to be readjusted later in the program
        timer.start();
    }
    //This starts the movement class for the ball
    public void actionPerformed(ActionEvent e) {
        movement();
    }
    //This manages movement and collisions
    public void movement() {
        //This means that if the w key is pressed that the paddle will move up
        if (wPressed) { //Apparently wPressed == true is the same thing as wPressed
            if (playerOneY - paddleSpeed > 0) {
                playerOneY -= paddleSpeed;
            }
        }
        //This means that if the s key is pressed the paddle will move down
        if (sPressed) {
            if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) {
                playerOneY += paddleSpeed;
            }
        }

        //This should determine where the ball goes after it moves
        int nextBallLeft = ballX + ballDeltaX;
        int nextBallRight = ballX + ballDeltaX + diameter;
        int nextBallTop = ballY + ballDeltaY;
        int nextBallBottom = ballY + ballDeltaY + diameter;

        int playerOneRight = playerOneX + playerOneWidth;
        int playerOneTop = playerOneY;
        int playerOneBottom = playerOneY + playerOneHeight;

        //This is when the ball bounces off the top of the screen
        if (nextBallTop < 0 || nextBallBottom > getHeight()) {
            ballDeltaY *= -1;
        }

        if (nextBallLeft < playerOneRight) {
            //If the ball misses the first players paddle this this will trigger player 2 victory,
            //also moves the ball to the left side
            if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
                System.out.println("This is here to show that player 2 ended up winning");
                ballX = 250;
                ballY = 250;
            } else {
                ballDeltaX *= -1;
            }
        }
        //When the ball goes off to the right
        if (nextBallRight > getWidth()) {
            ballDeltaX*=-1;
        }
        //This moves the ball
        ballX += ballDeltaX;
        ballY += ballDeltaY;

        repaint();
    }

    //this is the paint method for painting everything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);

        g.fillOval(ballX, ballY, diameter, diameter); //draws the ball
        g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight); //draws the player 1 paddle
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == VK_W) {
            wPressed = true;
        } else if (e.getKeyCode() == VK_S) {
            sPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == VK_W) {
            wPressed = false;
        } else if (e.getKeyCode() == VK_S) {
            sPressed = false;
        }
    }
}
