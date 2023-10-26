import java.util.ArrayList;

public class Snake {

    public ArrayList<SnakeBlock> snakeArray = new ArrayList<>();
    Direction direction = Direction.RIGHT;

    public Snake() {

        snakeArray.add(new SnakeBlock(100 , 100));
        snakeArray.add(new SnakeBlock(80 , 100));
        snakeArray.add(new SnakeBlock(60 , 100));
        snakeArray.add(new SnakeBlock(40 , 100));

    }

    public void increaseSize() {

        if(snakeArray.get(snakeArray.size() - 1).x == snakeArray.get(snakeArray.size()-2).x) {

            snakeArray.add(new SnakeBlock(snakeArray.get(snakeArray.size() - 1).x , snakeArray.get(snakeArray.size() - 1).y - 20));

        } else {
            snakeArray.add(new SnakeBlock(snakeArray.get(snakeArray.size() - 1).x - 20 , snakeArray.get(snakeArray.size() - 1).y));

        }


    }


}