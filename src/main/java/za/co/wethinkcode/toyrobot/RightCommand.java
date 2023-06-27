package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command{

    public RightCommand() {super("right");}

    /**
     * Turns the robot right.
     *
     * @param target The robot that is executing the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        target.setStatus("Turned right.");
        target.getWorld().updateDirection(true);
        return true;
    }


}
