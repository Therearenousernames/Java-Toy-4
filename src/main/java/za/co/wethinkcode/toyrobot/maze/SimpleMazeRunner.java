package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.ForwardCommand;
import za.co.wethinkcode.toyrobot.LeftCommand;
import za.co.wethinkcode.toyrobot.RightCommand;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SimpleMazeRunner extends BaseMazeRunner{
    private Robot robot;
    private IWorld.Direction edgeDirection;
    private  int count = 0;

    public SimpleMazeRunner(Robot robot, IWorld.Direction edgeDirection, String name) {
        super(name);
        this.robot = robot;
        this.edgeDirection = edgeDirection;
    }


    @Override
    public boolean execute(Robot target) {
        return mazeRun(target, edgeDirection);
    }

    /**
     * The robot will move forward until it hits a wall, then it will turn left or right and move forward until it hits a
     * wall again, and so on
     *
     * @param target The robot that will be running the maze
     * @param edgeDirection The direction of the edge you want to reach.
     * @return A boolean value.
     */
    @Override
    public boolean mazeRun(Robot target, IWorld.Direction edgeDirection) {
        System.out.println("Starting to mazerun...");

        while (true) {
            if (edgeDirection.equals(IWorld.Direction.UP)) {
                if (inRange(-100, 100, target.getWorld().getPosition().getX()) && target.getWorld().getPosition().getY() == 200) {
                    target.setStatus("I am at the top edge. (Cost: "+count+" steps)");
                    break;
                } else if (target.getWorld().getCurrentDirection().equals(IWorld.Direction.UP)) {
                    int old_x = target.getWorld().getPosition().getX();
                    int old_y = target.getWorld().getPosition().getY();
                    target.handleCommand(new ForwardCommand("1"));
                    int new_x = target.getWorld().getPosition().getX();
                    int new_y = target.getWorld().getPosition().getY();
                    if (old_x == new_x && new_y == old_y) {
                        boolean handledCommand = new Random().nextBoolean() ?
                                target.handleCommand(new RightCommand()) : target.handleCommand(new LeftCommand());
                        target.handleCommand(new ForwardCommand("1"));
                    }
                } else {
                    boolean handleCommand = new Random().nextBoolean() ?
                            target.handleCommand(new RightCommand()) : target.handleCommand(new LeftCommand());
                }
            }
            if (edgeDirection.equals(IWorld.Direction.DOWN)) {
                if (inRange(-100, 100, target.getWorld().getPosition().getX()) && target.getWorld().getPosition().getY() == -200) {
                    target.setStatus("I am at the bottom edge. (Cost: "+count+"steps)");
                    break;
                } else if (target.getWorld().getCurrentDirection().equals(IWorld.Direction.DOWN)) {
                    int old_x = target.getWorld().getPosition().getX();
                    int old_y = target.getWorld().getPosition().getY();
                    target.handleCommand(new ForwardCommand("1"));
                    int new_x = target.getWorld().getPosition().getX();
                    int new_y = target.getWorld().getPosition().getY();
                    if (old_x == new_x && new_y == old_y) {
                        boolean handledCommand = new Random().nextBoolean() ?
                                target.handleCommand(new RightCommand()) : target.handleCommand(new LeftCommand());
                        target.handleCommand(new ForwardCommand("1"));
                    }
                } else {
                    boolean handleCommand = new Random().nextBoolean() ?
                            target.handleCommand(new RightCommand()) : target.handleCommand(new LeftCommand());
                }
            }
            if (edgeDirection.equals(IWorld.Direction.RIGHT)) {
                if (inRange(-200, 200, target.getWorld().getPosition().getY()) && target.getWorld().getPosition().getX() == 100) {
                    target.setStatus("I am at the right edge. (Cost: "+count+" steps)");
                    break;
                } else if (target.getWorld().getCurrentDirection().equals(IWorld.Direction.RIGHT)) {
                    int old_x = target.getWorld().getPosition().getX();
                    int old_y = target.getWorld().getPosition().getY();
                    target.handleCommand(new ForwardCommand("1"));
                    int new_x = target.getWorld().getPosition().getX();
                    int new_y = target.getWorld().getPosition().getY();
                    if (old_x == new_x && new_y == old_y) {
                        boolean handledCommand = new Random().nextBoolean() ?
                                target.handleCommand(new RightCommand()) : target.handleCommand(new LeftCommand());
                        target.handleCommand(new ForwardCommand("1"));
                    }
                } else {
                    boolean handleCommand = new Random().nextBoolean() ?
                            target.handleCommand(new RightCommand()) : target.handleCommand(new LeftCommand());
                }
            }

            if (edgeDirection.equals(IWorld.Direction.LEFT)) {
                if (inRange(-200, 201, target.getWorld().getPosition().getY()) && target.getWorld().getPosition().getX() == -100) {
                    target.setStatus("I am at the left edge. (Cost: "+count+" steps)");
                    break;
                } else if (target.getWorld().getCurrentDirection().equals(IWorld.Direction.LEFT)) {
                    int old_x = target.getWorld().getPosition().getX();
                    int old_y = target.getWorld().getPosition().getY();
                    target.handleCommand(new ForwardCommand("1"));
                    int new_x = target.getWorld().getPosition().getX();
                    int new_y = target.getWorld().getPosition().getY();
                    if (old_x == new_x && new_y == old_y) {
                        boolean handledCommand = new Random().nextBoolean() ?
                                target.handleCommand(new RightCommand()) : target.handleCommand(new LeftCommand());
                        target.handleCommand(new ForwardCommand("1"));
                    }
                } else {
                    boolean handleCommand = new Random().nextBoolean() ?
                            target.handleCommand(new RightCommand()) : target.handleCommand(new LeftCommand());
                }
            }
            count+=1;

        }
        return true;
    }

    @Override
    public int getMazeRunCost() {
        return count;
    }


   /**
     * > This function takes in three integers, inclusive, exclusive, and numberToCheck, and returns true if numberToCheck
     * is in the range of inclusive to exclusive
     *
     * @param inclusive the first number in the range
     * @param exclusive the number that is not included in the range
     * @param numberToCheck the number to check if it's in the range
     * @return A boolean value.
     */
     public boolean inRange(int inclusive, int exclusive , int numberToCheck){
        List<Integer> range  = new ArrayList<>();
        for (int i = inclusive; i < exclusive ; i++){
            range.add(i);
        }
        return range.contains(numberToCheck);
    }
}
