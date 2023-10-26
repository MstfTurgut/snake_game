import javax.swing.*;

public class GameScreen extends JFrame {

    public GameScreen(String title) {
        super(title);
    }

    public static void main(String[] args) {

        GameScreen gameScreen = new GameScreen("Snake Game");

        gameScreen.setResizable(false);
        gameScreen.setFocusable(false);

        gameScreen.setSize(800 , 600);

        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();

        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);

        game.setFocusTraversalKeysEnabled(false);


        gameScreen.add(game);
        gameScreen.setVisible(true);
    }
}
