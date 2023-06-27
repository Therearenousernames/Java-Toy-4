package za.co.wethinkcode.toyrobot;

import java.util.*;


public class Play {
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Robot robot;

        String name = getInput("What do you want to name your robot?");

        if (args.length == 2) {
            robot = new Robot(name,args[0]+" "+ args[1]);
        } else {
            robot = new Robot(name);
        }

        System.out.println("Hello Kiddo!");
        System.out.println("Loaded "+args[1]);
        robot.getWorld().showObstacles();

        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction,robot);
                shouldContinue = robot.handleCommand(command);
                robot.addToHistory(command);

            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
                System.out.println(robot);
            }
        } while (shouldContinue);
        robot.getWorld().reset();
    }

    /**
     * It takes a string as an argument, prints it to the console, then waits for the user to enter a string. If the user
     * enters a blank string, it will print the prompt again and wait for the user to enter a string
     *
     * @param prompt The message that will be displayed to the user.
     * @return The input from the user.
     */
    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

}
