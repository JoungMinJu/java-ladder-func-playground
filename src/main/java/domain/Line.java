package domain;

import java.util.ArrayList;
import java.util.List;
import util.Errors;

public class Line {

    private final List<Point> points;

    private Line(List<Point> points) {
        this.points = points;
    }

    public static Line of(List<Boolean> leftRungsStatus, List<Boolean> rightRungsStatus) {
        validateHeight(leftRungsStatus, rightRungsStatus);

        int maxPosition = leftRungsStatus.size();
        List<Point> points = new ArrayList<>();
        for (int position = 0; position < maxPosition; position++) {
            points.add(new Point(leftRungsStatus.get(position), rightRungsStatus.get(position)));
        }
        return new Line(points);
    }

    public static void validateHeight(List<Boolean> leftRungsStatus, List<Boolean> rightRungsStatus) {
        if (leftRungsStatus.size() != rightRungsStatus.size()) {
            throw new IllegalArgumentException(Errors.RUNG_STATUS_LENGTH_MUST_MATCH);
        }
    }
    public List<Boolean> getRightStatus() {
        List<Boolean> rightStatus = new ArrayList<>();
        for (Point point : points) {
            rightStatus.add(point.isConnectedToRightLadder());
        }
        return rightStatus;
    }

    public int getHeight() {
        return this.points.size();
    }

}
