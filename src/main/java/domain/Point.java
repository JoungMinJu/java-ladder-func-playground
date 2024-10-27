package domain;

import util.Errors;

public class Point {

    private final boolean left;
    private final boolean right;

    public Point(boolean left, boolean right) {
        validateIsConnected(left, right);
        this.left = left;
        this.right = right;
    }

    public void validateIsConnected(boolean left, boolean right) {
        if(left && right) {
            throw new IllegalArgumentException(Errors.ADJACENT_LADDERS_CANNOT_HAVE_RUNG_AT_SAME_POSITION);
        }
    }

    public boolean isConnectedToRightLadder() {
        return this.right;
    }

    public boolean isConnectedToLeftLadder() {
        return this.left;
    }

}
