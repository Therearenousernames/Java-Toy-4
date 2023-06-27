package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class BackCommand extends Command{

    public BackCommand(String argument) {super("back", argument);}



    /**
     * "If the robot can move back by the number of steps specified in the argument, then it will do so and return true.
     * Otherwise, it will return false."
     *
     * The first thing we do is to parse the argument to an integer. We then call the updatePosition() function of the
     * robot's world, which returns an UpdateResponse. This is an enum that can have three values: SUCCESS,
     * FAILED_OBSTRUCTED, and FAILED_OUTSIDE_WORLD. If the robot can move back by the number of steps specified in the
     * argument, then the updatePosition() function will return SUCCESS. Otherwise, it will return FAILED_OBSTRUCTED or
     * FAILED_OUTSIDE_WORLD
     *
     * @param target The robot that is executing the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        int nrSteps = -(Integer.parseInt((getArgument())));
        IWorld.UpdateResponse response = target.getWorld().updatePosition(nrSteps);
        switch (response) {
            case SUCCESS:
                target.setStatus("Moved back by " + (nrSteps * -1) + " steps.");
                break;
            case FAILED_OBSTRUCTED:
                target.setStatus("Sorry, there's an obstacle in the way.");
                break;
            case FAILED_OUTSIDE_WORLD:
                target.setStatus("Sorry, I cannot go outside my safe zone.");
                break;
        }
        return true;
    }
}
