package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.DesignedMaze;
import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.Turtle.*;
import java.util.*;

public class TurtleWorld extends AbstractWorld{

    private Maze maze;
    private Turtle turtle;


    public TurtleWorld(Maze maze) {
        this.maze = maze;
        this.turtle = new Turtle();
        this.turtle.left(90);
        this.turtle.penSize(0.5);
        this.turtle.speed(0);


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
                this.turtle.goTo(this.position.getX(),this.position.getY());
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
            this.turtle.right(90);
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
            this.turtle.left(90);
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
     * Reset the world by:
     * - moving current robot position to center 0,0 coordinate
     * - removing all obstacles
     * - setting current direction to UP
     */
    @Override
    public void reset() {
        this.currentDirection = Direction.UP;
        this.position = CENTRE;
        this.turtle.bye();
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
        if (this.maze.getName().equals("DesignedMaze")) {
            drawDesignedObstacles();
        } else {
        drawObstacles();
        }
        drawBorder();
        if (!this.maze.getObstacles().isEmpty()) {
            System.out.println("There are some some obstacles:");
            for (int i = 0; i < this.maze.getObstacles().size(); i++) {
                int x = this.maze.getObstacles().get(i).getBottomLeftX();
                int y = this.maze.getObstacles().get(i).getBottomLeftY();
                System.out.println("- At position "+x+ ","+y+" (to "+(x+5)+","+(y+5)+")");
            }
        }
    }


    /**
     * The function draws the obstacles in the world by moving the turtle to the bottom left corner of each obstacle and
     * drawing a square
     */
    public void drawObstacles() {
        turtle.up();
        for (Obstacle obstacle: getObstacles()) {
            turtle.goTo(obstacle.getBottomLeftX(),obstacle.getBottomLeftY());
            turtle.down();
            for(int i = 0; i <4; i++) {
                turtle.forward(5);
                turtle.right(90);
            }
            turtle.up();
        }
        turtle.home();
        turtle.down();
        turtle.penColor("red");
    }


    /**
     * This function draws the obstacles that the user has designed
     */
    public void drawDesignedObstacles(){
        turtle.up();
        turtle.right(90);
        for (Obstacle obstacle: getObstacles()) {
            turtle.goTo(obstacle.getBottomLeftX(),obstacle.getBottomLeftY());
            turtle.down();
            for(int i = 0; i <4; i++) {
                turtle.forward(5);
                turtle.right(90);
            }
            turtle.up();
        }
        turtle.home();
        turtle.down();
        turtle.penColor("red");
    }


    /**
     * Draw a border around the canvas.
     */
    public void drawBorder() {
        turtle.up();
        turtle.goTo(-100, 200);
        turtle.down();
        turtle.penColor("green");
        for (int i =0; i<2; i++) {
            turtle.forward(200);
            turtle.right(90);
            turtle.forward(400);
            turtle.right(90);
        }
        turtle.up();
        turtle.home();
        turtle.down();
        turtle.left(90);

        turtle.penColor("red");
    }

}

