package domain;

import java.util.ArrayList;
import java.util.List;
import util.Errors;

public class Ladder {

    private final List<Line> lines;

    public Ladder(List<Line> lines) {
        validate(lines);
        this.lines = new ArrayList<>(lines);
    }

    private static void validate(List<Line> lines) {
        validateLaddersHeight(lines);
    }

    private static void validateLaddersHeight(List<Line> lines) {
        boolean allSameHeight = lines.stream()
            .map(Line::getHeight)
            .distinct()
            .count() == 1;
        if (!allSameHeight) {
            throw new IllegalArgumentException(Errors.ALL_LINE_MUST_HAVE_SAME_HEIGHT);
        }
    }

    public List<List<Boolean>> getRightRungStatus() {
        List<List<Boolean>> rightRungStatus = new ArrayList<>();
        for (Line line : lines) {
            rightRungStatus.add(line.getRightStatus());
        }
        return rightRungStatus;
    }

}
