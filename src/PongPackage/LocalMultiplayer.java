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
    private int ballSpeed = 200;
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

    //Player 2 stuff
    private int playerTwoX = 465;
    private int playerTwoY = 250;
    private int playerTwoWidth = 10;
    private int playerTwoHeight = 50;

    //Booleans for game states and keys being pressed
    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;

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
        //Player 1 paddle movement:
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
        //Player 2 paddle movement:
        if (upPressed) {
            if (playerTwoY - paddleSpeed > 0) {
                playerTwoY -= paddleSpeed;
            }
        }
        if (downPressed) {
            if (playerTwoY + paddleSpeed + playerTwoHeight < getHeight()) {
                playerTwoY += paddleSpeed;
            }
        }

        //This should determine where the ball goes after it moves
        int nextBallLeft = ballX + ballDeltaX;
        int nextBallRight = ballX + ballDeltaX + diameter;
        int nextBallTop = ballY + ballDeltaY;
        int nextBallBottom = ballY + ballDeltaY + diameter;

        //values for player one
        int playerOneRight = playerOneX + playerOneWidth;
        int playerOneTop = playerOneY;
        int playerOneBottom = playerOneY + playerOneHeight;

        //values for player two
        double playerTwoLeft = playerTwoX;
        double playerTwoTop = playerTwoY;
        double playerTwoBottom = playerTwoY + playerTwoHeight;

        //This is when the ball bounces off the top/bottom of the screen
        if (nextBallTop < 0 || nextBallBottom > getHeight()) {
            ballDeltaY *= -1;
        }
        //If the ball misses the first players paddle this this will trigger player 2 victory,
        //also moves the ball to the left side
        if (nextBallLeft < playerOneRight) {
            if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
                System.out.println("This is here to show that player 2 ended up winning");
                ballX = 250;
                ballY = 250;
            } else {
                ballDeltaX *= -1;
            }
        }
        //When the ball goes off to the right
        if (nextBallRight > playerTwoLeft) {
            //moves ball
            if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
                System.out.println("This is here to show that player 1 ended up winning");
                ballX = 250;
                ballY = 250;
            }
            else {
                ballDeltaX *= -1;
            }
        }
        //This moves the ball
        ballX += ballDeltaX;
        ballY += ballDeltaY;
        //This repaints the JPanel so that it updates when the ball is moved/redrawn
        repaint();
    }

    //this is the paint method for painting everything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white); //sets colors
        g.fillOval(ballX, ballY, diameter, diameter); //draws the ball
        g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight); //draws the player 1 paddle
        g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight); //draws the player 2 paddle
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == VK_W) {
            wPressed = true;
        } else if (e.getKeyCode() == VK_S) {
            sPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == VK_W) {
            wPressed = false;
        } else if (e.getKeyCode() == VK_S) {
            sPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }
}
