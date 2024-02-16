import java.util.ArrayList;

public class Snake {

    // ArrayList to store the blocks of the snake
    public ArrayList<SnakeBlock> snakeArray = new ArrayList<>();

    // // Initial direction of the snake
    Direction direction = Direction.RIGHT;

    public Snake() { // Initialize the snake with four blocks

        snakeArray.add(new SnakeBlock(100 , 100));
        snakeArray.add(new SnakeBlock(80 , 100));
        snakeArray.add(new SnakeBlock(60 , 100));
        snakeArray.add(new SnakeBlock(40 , 100));

    }

    public void increaseSize() {

        // Check if the last two blocks are in the same column
        if(snakeArray.get(snakeArray.size() - 1).x == snakeArray.get(snakeArray.size()-2).x) {

            // If yes, add a new block above the last block
            snakeArray.add(new SnakeBlock(snakeArray.get(snakeArray.size() - 1).x , snakeArray.get(snakeArray.size() - 1).y - 20));
        } else {

            // If not, add a new block to the left of the last block
            snakeArray.add(new SnakeBlock(snakeArray.get(snakeArray.size() - 1).x - 20 , snakeArray.get(snakeArray.size() - 1).y));
        }


    }


}