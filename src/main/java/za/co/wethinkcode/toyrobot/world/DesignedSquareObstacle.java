package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class DesignedSquareObstacle implements Obstacle{

    private int x;
    private int y;

    public DesignedSquareObstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * `getBottomLeftX` returns the x coordinate of the bottom left corner of the rectangle
     *
     * @return The x coordinate of the bottom left corner of the rectangle.
     */
    @Override
    public int getBottomLeftX() {
        return x;
    }

    /**
     * Returns the y coordinate of the bottom left corner of the rectangle.
     *
     * @return The y coordinate of the bottom left corner of the rectangle.
     */
    @Override
    public int getBottomLeftY() {
        return y;
    }

    /**
     * This function returns the number 5.
     *
     * @return The size of the array.
     */
    @Override
    public int getSize() {
        return 5;
    }

    /**
     * If the position is within the bounds of the building, return true
     *
     * @param position The position to check if the block is at.
     * @return A boolean value.
     */
    @Override
    public boolean blocksPosition(Position position) {
        int x = position.getX(), y = position.getY();
        for (int i = this.x; i < this.x+5; i++) {
            for (int p = this.y-5; p < this.y; p++) {
                if (x == i && y == p) {
                    return true;
                }
            }
        }
        return false;
    }


   /**
     * If the path is vertical, check every position between the two points. If the path is horizontal, check every
     * position between the two points
     *
     * @param a The starting position
     * @param b The position of the target
     * @return A boolean value.
     */
     @Override
    public boolean blocksPath(Position a, Position b) {;
        int x1 = a.getX(), y1 = a.getY(), x2 = b.getX(), y2 = b.getY();
        int difference_y = (y2-y1), difference_x = (x2-x1), increment = 0;

        if (difference_y > 0 || difference_x > 0) {
            increment = 1;
        } else {
            increment = -1;
        }
        if  (x1==x2) {
            for (int i = 0; i < Math.abs(difference_y); i++) {
                y1 += increment;
                if (blocksPosition(new Position(x1, y1))) {
                    return true;
                }
            }
        }else if  (y1==y2) {
            for (int i = 0; i < Math.abs(difference_x); i++) {
                x1 += increment;
                if (blocksPosition(new Position(x1, y1))) {
                    return true;
                }
            }
        }
        return false;

    }
}
