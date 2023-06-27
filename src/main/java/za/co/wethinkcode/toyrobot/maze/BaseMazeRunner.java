package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;
import java.util.List;

public abstract class BaseMazeRunner extends Command implements MazeRunner {

    public BaseMazeRunner(String name) {
        super(name);
    }


    /**
     * > This function creates a maze runner based on the maze name and the robot
     *
     * @param mazeRunner The name of the maze runner.
     * @param robot The robot that will be used to solve the maze.
     * @return A MazeRunner object.
     */
    public static BaseMazeRunner createMazeRunner(String mazeRunner,Robot robot) {
        IWorld.Direction direction = getDirection(mazeRunner);

        List<String> simpleMazerunnerList = List.of("RandomMaze","SimpleMaze","EmptyMaze");

        if (simpleMazerunnerList.contains(robot.getMaze().getName())) {
            return new SimpleMazeRunner(robot, direction, mazeRunner);
        } else if (robot.getMaze().getName().equals("DesignedMaze")) {
            return new DesignedMazeRunner(robot, direction, mazeRunner);
        }
        return new SimpleMazeRunner(robot, direction, mazeRunner);
    }


    /**
     * It takes a string and returns a direction
     *
     * @param args The direction you want to move in.
     * @return The direction of the player.
     */
    public static IWorld.Direction getDirection(String args) {
        switch (args) {
            case "bottom":
                return IWorld.Direction.DOWN;
            case "right":
                return IWorld.Direction.RIGHT;
            case "left":
                return IWorld.Direction.LEFT;
            case "top":
            default:
               return IWorld.Direction.UP;
        }

    }

}
