package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;
import java.util.ArrayList;
import java.util.List;


public class SimpleMaze extends BaseMaze{
    List<Obstacle> Obstacles = new ArrayList<>();
    List<List<Integer>> Paths = new ArrayList<>();

    public SimpleMaze() {
        super("SimpleMaze");
        createObstacles();
    }


    /**
     * It creates a square obstacle at (1,1) and then creates a grid of points that are 5 units apart.
     */
    public void createObstacles() {
        Obstacle obstacle = new SquareObstacle(1,1);
        Obstacles.add(obstacle);
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


    public List<List<Integer>> getPaths() {return Paths;}
}
