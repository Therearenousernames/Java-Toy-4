package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMaze extends BaseMaze {

    List<Obstacle> Obstacles = new ArrayList<>();
    List<List<Integer>> Paths = new ArrayList<>();

    public RandomMaze() {
        super("RandomMaze");
        createObstacles();
    }


    /**
     * Create a random number of obstacles, and add them to the Obstacles list.
     */
    public void createObstacles() {
        Random random = new Random();
        int number = random.nextInt(10);

        for (int i = 0; i <= number; i++) {
            int x = random.nextInt(100) * (random.nextBoolean() ? -1 : 1);
            int y = random.nextInt(200) * (random.nextBoolean() ? -1 : 1);
            Obstacle obstacle = new SquareObstacle(x, y);
            Obstacles.add(obstacle);
        }
    }


    @Override
    public List<Obstacle> getObstacles() {
        return this.Obstacles;
    }


    /**
     * If any of the obstacles in the list of obstacles blocks the path between the two positions, return true
     *
     * @param a The starting position of the path
     * @param b The position of the end of the path.
     * @return A boolean value.
     */
    @Override
    public boolean blocksPath(Position a, Position b) {
        for (int i = 0; i < Obstacles.size(); i++) {
            if (Obstacles.get(i).blocksPath(a, b)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<List<Integer>> getPaths() {
        return Paths;
    }
}