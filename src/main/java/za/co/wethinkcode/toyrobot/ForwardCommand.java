package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

public class ForwardCommand extends Command {

    public ForwardCommand(String argument) {super("forward", argument);}


    /**
     * "The robot moves forward by a number of steps, and if it fails, it sets the status to a message explaining why."
     *
     * The first line of the function is a call to the superclass's execute function. This is a good habit to get into, as
     * it ensures that the superclass's execute function is called
     *
     * @param target The robot that is executing the command.
     * @return The return value is a boolean.
     */
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        IWorld.UpdateResponse response = target.getWorld().updatePosition(nrSteps);
        switch (response) {
            case SUCCESS:
                target.setStatus("Moved forward by "+nrSteps+" steps.");
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

