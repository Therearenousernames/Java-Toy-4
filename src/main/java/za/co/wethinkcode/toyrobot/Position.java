package za.co.wethinkcode.toyrobot;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * This function returns the value of the x variable.
     *
     * @return The value of the x variable.
     */
    public int getX() {
        return x;
    }


    /**
     * This function returns the value of the y variable.
     *
     * @return The y value of the point.
     */
    public int getY() {
        return y;
    }


    /**
     * If the two objects are the same object, or if they are both null, then they are equal. Otherwise, if the two objects
     * are of the same class, and if their x and y values are equal, then they are equal
     *
     * @param o The object to compare to.
     * @return The hashcode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }


    /**
     * If the position is within the top left and bottom right, then return true.
     *
     * @param topLeft The top left corner of the rectangle
     * @param bottomRight The bottom right corner of the rectangle
     * @return A boolean value.
     */
    public boolean isIn(Position topLeft, Position bottomRight) {
        boolean withinTop = this.y <= topLeft.getY();
        boolean withinBottom = this.y >= bottomRight.getY();
        boolean withinLeft = this.x >= topLeft.getX();
        boolean withinRight = this.x <= bottomRight.getX();
        return withinTop && withinBottom && withinLeft && withinRight;
    }
}
