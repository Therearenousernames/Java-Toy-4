package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.ForwardCommand;
import za.co.wethinkcode.toyrobot.LeftCommand;
import za.co.wethinkcode.toyrobot.RightCommand;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;
import java.util.*;

public class DesignedMazeRunner extends BaseMazeRunner{
    private final IWorld.Direction edgeDirection;
    private Robot robot;
    private int count = 0;

    public DesignedMazeRunner(Robot robot, IWorld.Direction edgeDirection, String name) {
        super(name);
        this.edgeDirection = edgeDirection;
        this.robot = robot;
    }

    @Override
    public boolean execute(Robot target) {
        return mazeRun(target, edgeDirection);
    }

    /**
     * The function takes in a robot and a direction, and then it will find the shortest path to the edge of the maze in
     * that direction
     *
     * @param target the robot that is running the maze
     * @param edgeDirection The direction of the edge you want to reach.
     * @return A boolean value.
     */
    @Override
    public boolean mazeRun(Robot target, IWorld.Direction edgeDirection) {
        System.out.println("Starting to mazerun...");
        int x = target.getWorld().getPosition().getX();
        int y = target.getWorld().getPosition().getY();


        List<List<Integer>> paths = target.getMaze().getPaths();

        List<Integer> initial = convertingPositionToCell(x,y, paths);

        List<Integer> end = new ArrayList<>();

        int dy = (initial.get(1)-2- target.getWorld().getPosition().getY());

        target.handleCommand(new ForwardCommand(String.valueOf(dy)));

        if (edgeDirection.equals(IWorld.Direction.UP)) {
            end = paths.get(0);
            target.setStatus("I am at the top edge.");
        } else if (edgeDirection.equals(IWorld.Direction.DOWN)) {
            end = paths.get(paths.size()-1);
            target.setStatus("I am at the bottom edge.");
        } else if (edgeDirection.equals(IWorld.Direction.RIGHT)) {
            end = List.of(95,-35);

            target.setStatus("I am at the right edge.");
        } else if (edgeDirection.equals(IWorld.Direction.LEFT)) {
            end = List.of(-100,-35);
            target.setStatus("I am at the left edge.");
        }

        HashMap<List<Integer>, List<Integer>> solution = search(initial, paths);

        List<List<Integer>> road = backTracking(initial, end, solution);


        while (count < road.size()-1) {
            IWorld.Direction currentDirection = target.getWorld().getCurrentDirection();

            dy = 5;
            int dx = 5;

            List<Integer> topN = List.of(road.get(count).get(0),(road.get(count).get(1)+5));
            List<Integer> bottomN = List.of(road.get(count).get(0), (road.get(count).get(1)-5));
            List<Integer> leftN = List.of(road.get(count).get(0)-5, road.get(count).get(1));
            List<Integer> rightN = List.of(road.get(count).get(0)+5, road.get(count).get(1));

            if (currentDirection.equals(IWorld.Direction.UP)) {
                if (road.get(count+1).equals(topN)) {
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dy))));
                } else if (road.get(count+1).equals(bottomN)) {
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dy))));
                } else if (road.get(count+1).equals(leftN)) {
                    target.handleCommand(new LeftCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dx))));
                } else if (road.get(count+1).equals(rightN)) {
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dx))));
                }
            } else if (currentDirection.equals(IWorld.Direction.RIGHT)) {
                if (road.get(count+1).equals(topN)) {
                    target.handleCommand(new LeftCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dx))));
                } else if (road.get(count+1).equals(bottomN)) {
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dx))));
                } else if (road.get(count+1).equals(leftN)) {
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dy))));
                } else if (road.get(count+1).equals(rightN)) {
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dy))));
                }
            } else if (currentDirection.equals(IWorld.Direction.DOWN)) {
                if (road.get(count + 1).equals(topN)) {
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dy))));
                } else if (road.get(count + 1).equals(bottomN)) {
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dy))));
                } else if (road.get(count + 1).equals(leftN)) {
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dx))));
                } else if (road.get(count + 1).equals(rightN)) {
                    target.handleCommand(new LeftCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dx))));
                }
            } else if (currentDirection.equals(IWorld.Direction.LEFT)) {
                if (road.get(count + 1).equals(topN)) {
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dx))));
                } else if (road.get(count + 1).equals(bottomN)) {
                    target.handleCommand(new LeftCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dx))));
                } else if (road.get(count + 1).equals(leftN)) {
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dy))));
                } else if (road.get(count + 1).equals(rightN)) {
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new RightCommand());
                    target.handleCommand(new ForwardCommand(String.valueOf(Math.abs(dy))));
                }
            }
            count+= 1;

        }

        if (edgeDirection.equals(IWorld.Direction.UP)) {
            target.setStatus("I am at the top edge. (Cost: "+count+" steps)");
            return true;
        } else if (edgeDirection.equals(IWorld.Direction.DOWN)) {
            target.setStatus("I am at the bottom edge. (Cost: "+count+" steps)");
            return true;
        } else if (edgeDirection.equals(IWorld.Direction.RIGHT)) {
            target.setStatus("I am at the right edge. (Cost: "+count+" steps)");
            return true;
        } else if (edgeDirection.equals(IWorld.Direction.LEFT)) {
            target.setStatus("I am at the left edge. (Cost: "+count+" steps)");
            return true;
        } else {
            target.setStatus("I am lost. (Cost: "+count+" steps)");
            return true;
        }

    }

    @Override
    public int getMazeRunCost() {
        return count;
    }


  /**
   * The function takes in a start point and a list of paths, and returns a hashmap of the path from the start point to
   * every other point in the list of paths
   *
   * @param start the starting point of the search
   * @param paths a list of all the possible paths in the maze
   * @return A HashMap of the path from the start to the end.
   */
  public static HashMap<List<Integer>, List<Integer>> search(List<Integer> start, List<List<Integer>> paths)  {
        HashMap<List<Integer>, List<Integer>> solution = new HashMap<>();
        List<List<Integer>> frontier = new ArrayList<>();
        List<List<Integer>> visited = new ArrayList<>();
        int x, y;
        solution.put(start, start);
        frontier.add(start);
        visited.add(start);

        while (!frontier.isEmpty()) {
            List<Integer> value = frontier.remove(0);
            x = value.get(0);
            y = value.get(1);

            List<Integer> topN = List.of(x,y+5);
            List<Integer> bottomN = List.of(x,y-5);
            List<Integer> leftN = List.of(x-5,y);
            List<Integer> rightN = List.of(x+5,y);

            if (paths.contains(leftN) && (!visited.contains(leftN))) {
                solution.put(leftN, value);
                frontier.add(leftN);
                visited.add(leftN);
            }
            if (paths.contains(rightN) && (!visited.contains(rightN))) {
                solution.put(rightN, value);
                frontier.add(rightN);
                visited.add(rightN);
            }
            if (paths.contains(topN) && (!visited.contains(topN))) {
                solution.put(topN,value);
                frontier.add(topN);
                visited.add(topN);
            }
            if (paths.contains(bottomN) && (!visited.contains(bottomN))) {
                solution.put(bottomN, value);
                frontier.add(bottomN);
                visited.add(bottomN);
            }
        }
        return solution;
        }


    /**
     * "Create a list of all the numbers in the range, then check if the number to check is in the list."
     *
     *
     * @param inclusive the lowest number in the range
     * @param exclusive the number that is not included in the range
     * @param numberToCheck The number to check if it's in the range.
     * @return A boolean value.
     */
    public boolean inRangeDesigned(int inclusive, int exclusive, int numberToCheck) {
        List<Integer> range = new ArrayList<>();
        for (int i = inclusive; i < exclusive; i++) {
            range.add(i);
        }
        return range.contains(numberToCheck);
    }


    /**
     * This function takes in the starting x and y coordinates of the user and the list of all the coordinates of the cells
     * in the maze and returns the coordinates of the cell in which the user is currently in
     *
     * @param start_x the x coordinate of the starting position
     * @param start_y the y coordinate of the starting position
     * @param paths a list of lists of integers, where each list of integers is a path.
     * @return The initial cell of the path.
     */
    public List<Integer> convertingPositionToCell(int start_x, int start_y, List<List<Integer>> paths) {
        List<Integer> initialCell = new ArrayList<>();
        for (List<Integer> cord: paths) {
            if (inRangeDesigned(cord.get(0),cord.get(0)+6, start_x) && inRangeDesigned(cord.get(1)-6,cord.get(1),start_y)) {
                initialCell = List.of(cord.get(0),cord.get(1));
            }
        }
        return initialCell;
    }


    /**
     * We start from the end point and keep adding the previous point to the path until we reach the start point
     *
     * @param start the starting point of the path
     * @param end the end point of the path
     * @param solution A HashMap that stores the solution to the maze.
     * @return A list of lists of integers.
     */
    public static List<List<Integer>> backTracking(List<Integer> start, List<Integer> end, HashMap<List<Integer>,List<Integer>> solution) {
        List<List<Integer>> path = new ArrayList<>();
        int endX = end.get(0), endY = end.get(1);

        while (!start.equals(List.of(endX,endY))) {
            List<Integer> endValue = solution.get(List.of(endX,endY));
            path.add(List.of(endX,endY));
            endX = endValue.get(0);
            endY = endValue.get(1);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

}
