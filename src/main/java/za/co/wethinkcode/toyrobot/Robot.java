package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.maze.BaseMaze;
import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import java.util.ArrayList;
import java.util.List;

public class Robot {
    private List<Command> historyCommands = new ArrayList<>();

    private String status;
    private String name;
    private AbstractWorld world;
    private Maze maze;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.maze = BaseMaze.create("RandomMaze");
        this.world = AbstractWorld.checkArg("text", maze);

    }

    public Robot(String name, String argument) {
        String[] args = argument.split(" ");
        this.name = name;
        this.status = "Ready";
        this.maze = BaseMaze.create(args[1]);
        this.world = AbstractWorld.checkArg(args[0], maze);
    }

    public String getStatus() {
        return this.status;
    }


    /**
     * If the command is not a sprint command, print the player's status
     *
     * @param command The command to be executed
     * @return A boolean value.
     */
    public boolean handleCommand(Command command) {
        boolean commandHandled = command.execute(this);
        if(!command.getName().equalsIgnoreCase("sprint")){
            System.out.println(this);
        }
        return commandHandled;

    }

    /**
     * `toString()` is a function that returns a string representation of the object
     *
     * @return The position of the world, the name of the world, and the status of the world.
     */
    @Override
    public String toString() {
       return "[" + this.world.getPosition().getX() + "," + this.world.getPosition().getY() + "] "
               + this.name + "> " + this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Maze getMaze() {return this.maze;}

    public List<Command> getHistoryCommands(){
        return this.historyCommands ;
    }

    /**
     * If the command is valid, add it to the history
     *
     * @param command The command to add to the history.
     */
    public void addToHistory(Command command){
        List<String> validCommands = List.of("forward", "back", "sprint","left","right");
        if (validCommands.contains(command.getName())) {
            this.historyCommands.add(command);}
    }

    public AbstractWorld getWorld() {return this.world;}
}