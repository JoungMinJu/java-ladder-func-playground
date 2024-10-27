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

    public int getHeight() {
        return this.lines.get(0).getHeight();
    }

    public List<Integer> getResult() {
        List<Integer> result = new ArrayList<>();
        int height = this.getHeight();

        for (int nowIndex = 0; nowIndex < lines.size(); nowIndex++) {
            int finalIndex = calculateTargetIndex(nowIndex, height);
            result.add(finalIndex);
        }
        return result;
    }

    private int calculateTargetIndex(int startIndex, int height) {
        int targetIndex = startIndex;
        for (int nowPosition = height - 1; nowPosition >= 0; nowPosition--) {
            targetIndex = getNextIndex(targetIndex, nowPosition);
        }
        return targetIndex;
    }

    private int getNextIndex(int currentIndex, int position) {
        Line nowLine = lines.get(currentIndex);

        if (nowLine.isConnectedToLeftLineAt(position)) {
            return currentIndex - 1;
        } else if (nowLine.isConnectedToRightLineAt(position)) {
            return currentIndex + 1;
        }
        return currentIndex;
    }
}
