package za.co.wethinkcode.toyrobot.maze;


public abstract class BaseMaze implements Maze{

    private String name;


    public BaseMaze(String name){
        this.name = name;
    }


    /**
     * Given a string, return a new instance of the class that matches the string.
     *
     * @param maze The name of the maze you want to create.
     * @return A new instance of the class that is being called.
     */
    public static BaseMaze create(String maze) {
        switch (maze){
            case "EmptyMaze":
                return new EmptyMaze();
            case "RandomMaze":
                return new RandomMaze();
            case "SimpleMaze":
                return new SimpleMaze();
            case "DesignedMaze":
                return new DesignedMaze();
            default:
                throw new IllegalArgumentException("Unsupported command: "+ maze);
        }
    }

    @Override
    public String getName(){
        return this.name;
    }
}
