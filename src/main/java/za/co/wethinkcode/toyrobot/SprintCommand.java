package za.co.wethinkcode.toyrobot;

public class SprintCommand extends Command{

    public SprintCommand(String argument) { super("sprint", argument);}

    /**
     * The function executes the ForwardCommand for the number of steps specified in the argument.
     *
     * @param target The robot that will execute the command.
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());

        for (int i =nrSteps; i > 0; i--) {
            ForwardCommand command = new ForwardCommand(String.valueOf(i));
            target.handleCommand(command);
        }
        return true;
    }
}
