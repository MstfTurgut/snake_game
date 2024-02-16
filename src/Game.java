import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(100, this); // Timer to control game updates
    Random random = new Random();

    Snake snake = new Snake();

    private int pointX = 500;
    private int pointY = 500;
    private int pointCount = 0; // Initial point coordinates and count


    public Game() {
        setBackground(Color.BLACK);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.white);

        g.drawString("Score : " + pointCount , 20 , 20); // Display the current score

        // Draw the snake
        for(int i = 0; i < snake.snakeArray.size() ; i++) {

            if(i == 0) {
                g.setColor(Color.decode("#8E8F24")); // Head of the snake
                g.fillRect(snake.snakeArray.get(i).x , snake.snakeArray.get(i).y , 20 , 20);
            }else {
                g.setColor(Color.decode("#747514")); // Body of the snake
                g.fillRect(snake.snakeArray.get(i).x , snake.snakeArray.get(i).y , 20 , 20);
            }
        }

        g.setColor(Color.decode("#4C1B5F"));

        g.fillOval(pointX , pointY , 15 , 15); // Draw the point
    }

    public boolean snakeAtePoint() {

        SnakeBlock head = snake.snakeArray.get(0);

        return new Rectangle(head.x, head.y, 20, 20).intersects(new Rectangle(pointX, pointY, 15, 15));
    }

    public boolean gameOver() {

        ArrayList<SnakeBlock> sa = snake.snakeArray;

        SnakeBlock head = sa.get(0);

        // Check if the head collides with the body
        for (int i = 1 ; i < sa.size() ; i++) {
            SnakeBlock bodyBlock = sa.get(i);
            if(new Rectangle(head.x , head.y , 20 , 20).intersects(new Rectangle(bodyBlock.x , bodyBlock.y , 20 , 20))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SnakeBlock head = snake.snakeArray.get(0);
        ArrayList<SnakeBlock> sa = snake.snakeArray;

        int snakeDir = 20;
        switch (snake.direction) {  // Move the snake up, down, left, right
            case UP -> {
                if(head.y > 0) {
                    for(int i = sa.size() - 1 ; i > 0 ; i--) {
                        sa.get(i).x = sa.get(i - 1).x;
                        sa.get(i).y = sa.get(i - 1).y;
                    }
                    head.y -= snakeDir;
                } else {
                    head.y = 540;
                }
            }
            case DOWN -> {
                if(head.y < 540) {
                    for(int i = snake.snakeArray.size() - 1 ; i > 0 ; i--) {
                        sa.get(i).x = sa.get(i - 1).x;
                        sa.get(i).y = sa.get(i - 1).y;
                    }
                    head.y += snakeDir;
                } else {
                    head.y = 0;
                }
            }
            case LEFT -> {
                if(head.x > 0) {
                    for(int i = snake.snakeArray.size() - 1 ; i > 0 ; i--) {
                        sa.get(i).x = sa.get(i - 1).x;
                        sa.get(i).y = sa.get(i - 1).y;
                    }
                    head.x -= snakeDir;
                } else {
                    head.x = 750;
                }

            }
            case RIGHT -> {
                if(head.x < 750) {
                    for(int i = snake.snakeArray.size() - 1 ; i > 0 ; i--) {
                        sa.get(i).x = sa.get(i - 1).x;
                        sa.get(i).y = sa.get(i - 1).y;
                    }
                    head.x += snakeDir;
                } else {
                    head.x = 0;
                }
            }
        }

        if(snakeAtePoint()) {

            // Increase the score

            pointCount++;

            // Sound effect

            soundEffect("eating.wav");

            // Increase snake length

            snake.increaseSize();

            // Move the point to a new random location

            pointX = random.nextInt(750);
            pointY = random.nextInt(535);

        }

        repaint();

        if(gameOver()) {
            timer.stop();

            String message = "GAME OVER :/"
                    + "\n Your Score : " + pointCount;

            soundEffect("gameover.wav");

            JOptionPane.showMessageDialog(this , message);

            System.exit(0);

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        // Change snake direction based on key input

        if(c == KeyEvent.VK_RIGHT && snake.direction != Direction.LEFT) {
            snake.direction = Direction.RIGHT;
        }

        if(c == KeyEvent.VK_LEFT && snake.direction != Direction.RIGHT) {
            snake.direction = Direction.LEFT;
        }

        if(c == KeyEvent.VK_UP && snake.direction != Direction.DOWN) {
            snake.direction = Direction.UP;
        }

        if(c == KeyEvent.VK_DOWN && snake.direction != Direction.UP) {
            snake.direction = Direction.DOWN;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void repaint() {super.repaint();}

    @Override
    public void keyTyped(KeyEvent e) {}


    public void soundEffect(String fileName) {
        try {

            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(fileName));

            Clip clip = AudioSystem.getClip();

            clip.open(stream);

            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

