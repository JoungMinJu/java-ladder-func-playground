package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.Errors;

public class Line {

    private final Player player;
    private final String outcome;

    private final List<Point> points;

    private Line(Player player, String outcome, List<Point> points) {
        this.player = player;
        this.outcome = outcome;
        this.points = points;
    }

    public static Line of(Player player, String outcome, List<Boolean> leftRungsStatus,
                          List<Boolean> rightRungsStatus) {
        validateHeight(leftRungsStatus, rightRungsStatus);

        List<Point> points = IntStream.range(0, leftRungsStatus.size())
            .mapToObj(position -> new Point(leftRungsStatus.get(position), rightRungsStatus.get(position)))
            .collect(Collectors.toList());
        return new Line(player, outcome, points);
    }

    private static void validateHeight(List<Boolean> leftRungsStatus, List<Boolean> rightRungsStatus) {
        if (leftRungsStatus.size() != rightRungsStatus.size()) {
            throw new IllegalArgumentException(Errors.RUNG_STATUS_LENGTH_MUST_MATCH);
        }
    }

    public List<Boolean> getRightStatus() {
        return points.stream()
            .map(Point::isConnectedToRightLadder)
            .collect(Collectors.toList());
    }

    public int getHeight() {
        return this.points.size();
    }

    public String getName() {
        return player.getName();
    }

    public String getOutcome() {
        return outcome;
    }

    public boolean isConnectedToLeftLineAt(int position) {
        Point nowPoint = this.points.get(position);
        return nowPoint.isConnectedToLeftLadder();
    }

    public boolean isConnectedToRightLineAt(int position) {
        Point nowPoint = this.points.get(position);
        return nowPoint.isConnectedToRightLadder();
    }

}
