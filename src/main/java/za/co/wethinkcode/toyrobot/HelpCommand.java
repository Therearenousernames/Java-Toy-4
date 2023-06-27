package za.co.wethinkcode.toyrobot;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }


    /**
     * The function prints out a list of commands that the robot can understand
     *
     * @param target The robot that the command is being executed on.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        target.setStatus("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n"+
                "SPRINT - sprint forward according to a formula\n"+
                "RIGHT - turn right by 90 degrees.\n"+
                "LEFT - turn left by 90 degrees.\n"+
                "REPLAY - replays all the movement commands from history [FORWARD, BACK, RIGHT, LEFT, SPRINT\n"+
                "MAZERUN TOP - runs the maze to the top.");
        return true;
    }
}
