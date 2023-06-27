package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.BaseMazeRunner;


public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }


    public String getName() {                                                                           //<2>
        return name;
    }


    public String getArgument() {
        return this.argument;
    }


    /**
     * It takes a string and a robot, and returns a command
     *
     * @param instruction The instruction that the user has entered.
     * @param robot The robot that will be executing the command.
     * @return A command object.
     */
    public static Command create(String instruction,Robot robot) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            case "right":
                return new RightCommand();
            case "left":
                return new LeftCommand();
            case "back":
                return new BackCommand(args[1]);
            case "sprint":
                return new SprintCommand(args[1]);
            case "replay":
                if (args.length > 1) {
                    String arg = instruction.substring(7);
                    return new ReplayCommand(arg);
                } else {
                    return new ReplayCommand("");
            }
            case "mazerun":
                    if (args.length == 1) {
                        return BaseMazeRunner.createMazeRunner("", robot);
                    }
                    return BaseMazeRunner.createMazeRunner(args[1], robot);

            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }

}

