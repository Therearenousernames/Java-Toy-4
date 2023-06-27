package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

import java.util.*;

public class TextWorld extends AbstractWorld{

    private Maze maze;


    public TextWorld(Maze maze) {
        this.maze = maze;

    }


    /**
     * Updates the position of your robot in the world by moving the nrSteps in the robots current direction.
     *
     * @param nrSteps steps to move in current direction
     * @return true if this does not take the robot over the world's limits, or into an obstacle.
     */
    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.UP.equals(this.currentDirection)) {
            newY += nrSteps;
        } else if (Direction.RIGHT.equals(this.currentDirection)) {
            newX += nrSteps;
        } else if (Direction.DOWN.equals(this.currentDirection)) {
            newY -= nrSteps;
        } else if (Direction.LEFT.equals(this.currentDirection)) {
            newX -= nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (isNewPositionAllowed(newPosition)){
            if (this.maze.blocksPath(position, newPosition)) {
                return UpdateResponse.FAILED_OBSTRUCTED;
            } else {
                this.position = newPosition;
                return UpdateResponse.SUCCESS;
            }
        }
        return UpdateResponse.FAILED_OUTSIDE_WORLD;
    }


    /**
     * Updates the current direction your robot is facing in the world by cycling through the directions UP, RIGHT, BOTTOM, LEFT.
     *
     * @param turnRight if true, then turn 90 degrees to the right, else turn left.
     */
    @Override
    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            if (this.currentDirection.equals(Direction.UP)) {
                this.currentDirection = Direction.RIGHT;
            } else if (this.currentDirection.equals(Direction.RIGHT)) {
                this.currentDirection = Direction.DOWN;
            } else if (this.currentDirection.equals(Direction.DOWN)) {
                this.currentDirection = Direction.LEFT;
            } else if (this.currentDirection.equals(Direction.LEFT)) {
                this.currentDirection = Direction.UP;
            }
        } else {
            if (this.currentDirection.equals(Direction.UP)) {
                this.currentDirection = Direction.LEFT;
            } else if (this.currentDirection.equals(Direction.LEFT)) {
                this.currentDirection = Direction.DOWN;
            } else if (this.currentDirection.equals(Direction.DOWN)) {
                this.currentDirection = Direction.RIGHT;
            } else if (this.currentDirection.equals(Direction.RIGHT)) {
                this.currentDirection = Direction.UP;
            }
        }
    }


    /**
     * The reset function sets the current direction to up and the position to the centre
     */
    @Override
    public void reset() {
        this.currentDirection = Direction.UP;
        this.position = CENTRE;
    }


    /**
     * @return the list of obstacles, or an empty list if no obstacles exist.
     */
    @Override
    public List<Obstacle> getObstacles() {
        return this.maze.getObstacles();
    }


    /**
     * Gives opportunity to world to draw or list obstacles.
     */
    @Override
    public void showObstacles() {

        if (!this.maze.getObstacles().isEmpty()) {
            System.out.println("There are some some obstacles:");
            for (int i = 0; i < this.maze.getObstacles().size(); i++) {
                int x = this.maze.getObstacles().get(i).getBottomLeftX();
                int y = this.maze.getObstacles().get(i).getBottomLeftY();
                System.out.println("- At position "+x+ ","+y+" (to "+(x+5)+","+(y+5)+")");
            }
        }

    }

}
