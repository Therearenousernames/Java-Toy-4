package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.DesignedSquareObstacle;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public class DesignedMaze extends BaseMaze{

    List<Obstacle> Obstacles = new ArrayList<>();
    public static List<List<Integer>> Paths = new ArrayList<>();


    public DesignedMaze() {
        super("DesignedMaze");
        createObstacles();}

    /**
     * It takes a string of a maze, splits it into a list of strings, then splits each string into a list of characters,
     * then adds the coordinates of each character to a list of coordinates, then creates a list of obstacles from the list
     * of coordinates
     */
    public void createObstacles() {

        List<List<Integer>> Coordinates = new ArrayList<>();
        String maze = "################# ######################\n"+
                "# #   #     #     #   #   #       # #  #\n"+
                "# ### # # # # ### # # # ### # # # # # ##\n"+
                "# #   # # #   #   # #     # # # #     ##\n"+
                "# # # ####### # ####### ####### ##### ##\n"+
                "#   #             # #     #     #   #  #\n"+
                "# ##### ### ### # # ### # # ##### ### ##\n"+
                "#     # # # #   #   # # #     # # #    #\n"+
                "### ##### # ##### ### ### # ### # # ####\n"+
                "#       # #   #     #     # # #   # #  #\n"+
                "# # # ### # # #####     # # # # ##### ##\n"+
                "# # # #     # #         # #     # #    #\n"+
                "# ### # # ######### ### ####### # ######\n"+
                "# #   # # #   # #     #   # # # #   # ##\n"+
                "# ### ####### # ### # ##### # # ### # ##\n"+
                "#   #         # #   # # #     #       ##\n"+
                "##### # ### ### ### ### # # # # # # # ##\n"+
                "#     # #   #     #   #   # #   # # #  #\n"+
                "# # # # ### # ### ### ### # ### ### ####\n"+
                "# # # #   #   #   #   #   #   #   #   ##\n"+
                "# #   #     #     #   #   #       # #  #\n"+
                "# ### # # # # ### # # # ### # # # # # ##\n"+
                "# #   # # #   #   # #     # # # #     ##\n"+
                "# # # ####### # ####### ####### ##### ##\n"+
                "#   #             # #     #     #   #  #\n"+
                "# ##### ### ### # # ### # # ##### ### ##\n"+
                "#     # # # #   #   # # #     # # #    #\n"+
                "### ##### # ##### ### ### # ### # # ####\n"+
                "#       # #   #     #     # # #   # #  #\n"+
                "# # # ### # # #####     # # # # ##### ##\n"+
                "# # # #     # #         # #     # #    #\n"+
                "# ### # # ######### ### ####### # ######\n"+
                "# #   # # #   # #     #   # # # #   # ##\n"+
                "# ### ####### # ### # ##### # # ### # ##\n"+
                "#   #         # #   # # #     #       ##\n"+
                "##### # ### ### ### ### # # # # # # # ##\n"+
                "#     # #   #     #   #   # #   # # #  #\n"+
                "# # # # ### # ### ### ### # ### ### ####\n"+
                "# # # #   #   #   #   #   #   #   #   ##\n"+
                "# #   #     #     #   #   #       # #  #\n"+
                "# ### # # # # ###       ### # # # # # ##\n"+
                "# #   # # #   #           # # # #     ##\n"+
                "# # # ####### # ####### ####### ##### ##\n"+
                "#   #             # #     #     #   #  #\n"+
                "# ##### # # ### # # ### # # ##### ### ##\n"+
                "#     # # # #   #   # # #     # # #    #\n"+
                "### ##### # ##### ### ### # ### # # ####\n"+
                "*       # #   #     #     # # #        *\n"+
                "# # # ### # # #####     # # # # ##### ##\n"+
                "# # # #     # #         # #     # #    #\n"+
                "# ### # # ##### ### ### ####### # ######\n"+
                "# #   # # #   # #     #   # # # #   # ##\n"+
                "# ### ####### # ### # # ### # # ### # ##\n"+
                "#   #         # #   # # #     #       ##\n"+
                "##### # ### ### ### ### # # # # # # # ##\n"+
                "#     # #   #     #   #   # #   # # #  #\n"+
                "# # # # ### # ### ### ### # ### ### ####\n"+
                "# # # #   #   #   #   #   #   #   #   ##\n"+
                "# #   #     #     #   #   #       # #  #\n"+
                "# ### # # # # ### # # # ### # # # # # ##\n"+
                "# #   # # #   #   # #     # # # #     ##\n"+
                "# # # ####### # ####### ####### ##### ##\n"+
                "#   #             # #     #     #   #  #\n"+
                "# ##### ### ### # # ### # # ##### ### ##\n"+
                "#     # # # #   #   # # #     # # #    #\n"+
                "### ##### # ##### ### ### # ### # # ####\n"+
                "#       # #   #     #     # # #   # #  #\n"+
                "# # # ### # # #####     # # # # ##### ##\n"+
                "# # # #     # #         # #     # #    #\n"+
                "# ### # # ######### ### ####### # ######\n"+
                "# #   # # #   # #     #   # # # #   # ##\n"+
                "# ### ####### # ### # ##### # # ### # ##\n"+
                "#   #         # #   # # #     #       ##\n"+
                "##### # ### ### ### ### # # # # # # # ##\n"+
                "#     # #   #     #   #   # #   # # #  #\n"+
                "# # # # ### # ### ### ### # ### ### ####\n"+
                "# # # #   #   #   #   #   #   #   #   ##\n"+
                "# # # # ### # ### ### ### # ### ### ####\n"+
                "# # # #   #   #   #   #   #   #   #   ##\n"+
                "################### ####################\n";

        String [] new_maze = maze.trim().split("\n");

        int start_y = Math.round((5*new_maze.length)/2);
        int start_x = -(Math.round(5*(new_maze[0].length())/2));
        System.out.println(start_x+" :::: "+start_y);
        for (String string_cords: new_maze) {
            String[] cords = string_cords.split("");
            for (String cord: cords) {
               if (cord.equals("#")) {
                   Coordinates.add(List.of(start_x,start_y));
               } else if (cord.equals(" ")|| cord.equals("*")) {
                   if (cord.equals("*"))System.out.println(List.of(start_x,start_y));
                   Paths.add(List.of(start_x,start_y));
               }
               start_x += 5;
            }
            start_x = -(Math.round(5*(new_maze[0].length())/2));
            start_y -= 5;
        }

        for (List<Integer> coordinate : Coordinates) {
            int x = coordinate.get(0);
            int y = coordinate.get(1);
            Obstacle obstacle = new DesignedSquareObstacle(x, y);
            Obstacles.add(obstacle);
        }
    }


    @Override
    public List<Obstacle> getObstacles() {
        return this.Obstacles;
    }

    /**
     * If any obstacle blocks the path, then the path is blocked.
     *
     * @param a The starting position of the path
     * @param b The position of the end of the path
     * @return A boolean value.
     */
    @Override
    public boolean blocksPath(Position a, Position b) {
        for (Obstacle obstacle : Obstacles) {
            if (obstacle.blocksPath(a, b)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<List<Integer>> getPaths() {
        return Paths;
    }


}
