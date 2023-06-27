package za.co.wethinkcode.toyrobot;

public class ShutdownCommand extends Command {
    public ShutdownCommand() {
        super("off");
    }

    /**
     * If the robot is told to shut down, it will set its status to 'Shutting down...' and return false.
     *
     * @param target The robot that is executing the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        target.setStatus("Shutting down...");
        return false;
    }
}
