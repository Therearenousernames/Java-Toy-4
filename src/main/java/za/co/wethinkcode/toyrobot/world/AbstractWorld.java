package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;

public abstract class AbstractWorld implements IWorld {
    protected final Position TOP_LEFT = new Position(-100, 200);
    protected final Position BOTTOM_RIGHT = new Position(100, -200);

    protected Position position;
    protected Direction currentDirection;

    public AbstractWorld() {
        this.position = CENTRE;
        this.currentDirection = Direction.UP;
    }


    public static AbstractWorld checkArg(String arg, Maze maze) {

        if (arg.equalsIgnoreCase("text")) {
            return new TextWorld(maze);
        } else if (arg.equalsIgnoreCase("turtle")) {
            return new TurtleWorld(maze);
        } else{
            throw new IllegalArgumentException("Unsupported argument");
        }
    }

    /**
     * Retrieves the current position of the robot
     */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /**
     * Gets the current direction the robot is facing in relation to a world edge.
     *
     * @return Direction.UP, RIGHT, DOWN, or LEFT
     */
    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    /**
     * Checks if the new position will be allowed, i.e. falls within the constraints of the world, and does not overlap an obstacle.
     *
     * @param position the position to check
     * @return true if it is allowed, else false
     */
    @Override
    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT,BOTTOM_RIGHT);
    }

    /**
     * Checks if the robot is at one of the edges of the world
     *
     * @return true if the robot's current is on one of the 4 edges of the world
     */
    @Override
    public boolean isAtEdge() {
        boolean TOP_EDGE = position.getY() == TOP_LEFT.getY();
        boolean BOTTOM_EDGE = position.getY() == BOTTOM_RIGHT.getY();
        boolean RIGHT_EDGE = position.getX() == BOTTOM_RIGHT.getX();
        boolean LEFT_EDGE = position.getX() == TOP_LEFT.getX();
        return TOP_EDGE || BOTTOM_EDGE || RIGHT_EDGE || LEFT_EDGE;
    }
}
