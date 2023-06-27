package za.co.wethinkcode.toyrobot;

import java.util.Collections;
import java.util.List;

public class ReplayCommand extends Command{

    public boolean replay = false;
    public boolean reversed = false;

    public ReplayCommand(String argument) {super("replay", argument);}


    /**
     * It checks if the command is a replay command, and if it is, it gets the list of commands to replay, and then replays
     * them
     *
     * @param target the robot that will be executing the command
     * @return A boolean value.
     */
    @Override
    public boolean execute(Robot target) {
        List<Command> commands = checkReplayCommand(target);
        for (int i = 0; i < commands.size(); i ++) {
            target.handleCommand(commands.get(i));
        }
        target.setStatus("replayed " + commands.size() + " commands.");

        return true;
    }


    /**
     * If the argument is "reversed", then the commands are reversed. If the argument is "reversed" followed by a number,
     * then the last n commands are reversed. If the argument is "reversed" followed by a range of numbers, then the
     * commands between the two numbers are reversed. If the argument is a number, then the last n commands are replayed.
     * If the argument is a range of numbers, then the commands between the two numbers are replayed. If the argument is
     * empty, then all commands are replayed
     *
     * @param target the robot that the command is being executed on
     * @return A list of commands.
     */
    public List<Command> checkReplayCommand(Robot target) {
        String argument = getArgument();
        List<Command> commands = target.getHistoryCommands();

        if (argument.contains("reversed")) {
            reversed = true;
            if (argument.equals("reversed")) {
                Collections.reverse(commands);
            } else {
                String [] args = argument.split(" ");
                if (args[0].equals("reversed") && args[1].contains("-")) {
                    try {
                        String[] numbers = args[1].split("-");
                        int n = Integer.parseInt(numbers[0]);
                        int m = Integer.parseInt(numbers[1]);
                        if (n > m) {
                            commands = commands.subList(0, (n - m));
                            Collections.reverse(commands);
                        }
                    } catch (NumberFormatException e) {
                    }
                } else {
                    try {
                        int n = Integer.parseInt(args[1]);
                        commands = commands.subList(commands.size() - n, commands.size());
                        Collections.reverse(commands);

                    } catch (NumberFormatException e) {
                        }
                }
            }
        } else if (argument.equals("")) {
            replay = true;
        } else if (argument.contains("-")) {
            try {
                String [] args = argument.split("-");
                int n = Integer.parseInt(args[0]);
                int m = Integer.parseInt(args[1]);
                if (n > m) {
                    commands = commands.subList(0, (n-m));
                    replay = true;
                }
            } catch (NumberFormatException e){
            }
        } else {
            try {
                int arg = Integer.parseInt(argument);
                commands = commands.subList(commands.size()-arg,commands.size());
                replay = true;
            } catch (NumberFormatException e) {
            }
        }
        return commands;
    }

}
