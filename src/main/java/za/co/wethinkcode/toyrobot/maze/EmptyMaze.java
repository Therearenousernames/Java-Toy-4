package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;
import java.util.ArrayList;
import java.util.List;


public class EmptyMaze extends BaseMaze{
    private List<Obstacle> Obstacles;
    public static List<List<Integer>> Paths = new ArrayList<>();


    public EmptyMaze() {
        super("EmptyMaze");
        createObstacles();
    }


    /**
     * This function creates an array list of obstacles
     */
    public void createObstacles() {
        Obstacles = new ArrayList<>();
    }


    @Override
    public List<Obstacle> getObstacles() {
        return this.Obstacles;
    }

    /**
     * If any of the obstacles in the list of obstacles blocks the path between the two positions, return true
     *
     * @param a The starting position of the path
     * @param b The position of the end of the path
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
