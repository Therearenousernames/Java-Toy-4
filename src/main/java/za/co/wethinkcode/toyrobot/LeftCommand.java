package za.co.wethinkcode.toyrobot;

public class LeftCommand extends Command{

    public LeftCommand() {super("left");}


    /**
     * Turns the robot left.
     *
     * @param target The robot that is executing the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        target.setStatus("Turned left.");
        target.getWorld().updateDirection(false);
        return true;
    }
}
