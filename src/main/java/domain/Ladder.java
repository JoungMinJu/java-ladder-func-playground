package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import util.Errors;

public class Ladder {

    private final List<Line> lines;

    public Ladder(List<Line> lines) {
        validate(lines);
        this.lines = new ArrayList<>(lines);
    }

    private void validate(List<Line> lines) {
        validateLaddersHeight(lines);
        validatePointStatus(lines);
    }

    private void validateLaddersHeight(List<Line> lines) {
        boolean allSameHeight = lines.stream()
            .map(Line::getHeight)
            .distinct()
            .count() == 1;
        if (!allSameHeight) {
            throw new IllegalArgumentException(Errors.ALL_LINE_MUST_HAVE_SAME_HEIGHT);
        }
    }

    private void validatePointStatus(List<Line> lines) {
        for (int index = 0; index < lines.size() - 1; index++) {
            Line nowLine = lines.get(index);
            Line nextLine = lines.get(index + 1);
            validateRungInSamePosition(nowLine, nextLine);
        }
    }

    private void validateRungInSamePosition(Line nowLine, Line nextLine) {
        if (!isRungInSamePosition(nowLine, nextLine)) {
            throw new IllegalArgumentException(Errors.ADJACENT_POINTER_STATUS_MATCH);
        }
    }

    private boolean isRungInSamePosition(Line nowLine, Line nextLine) {
         int maxPosition = nowLine.getHeight();
        return IntStream.range(0, maxPosition)
            .allMatch(
                position -> nowLine.isConnectedToRightLineAt(position) == nextLine.isConnectedToLeftLineAt(position));
    }

    public List<List<Boolean>> getRightRungStatus() {
        return lines.stream()
            .map(Line::getRightStatus)
            .toList();
    }

    public int getHeight() {
        return this.lines.get(0).getHeight();
    }

    public Map<String, String> getResult() {
        Map<String, String> result = new LinkedHashMap<>();
        int height = this.getHeight();

        for (int nowIndex = 0; nowIndex < lines.size(); nowIndex++) {
            int finalIndex = calculateTargetIndex(nowIndex, height);
             String name = getNameOfLine(nowIndex);
             String outcome = getOutcomeOfLine(finalIndex);
            result.put(name, outcome);
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

    private String getNameOfLine(int index) {
        return lines.get(index).getName();
    }

    private String getOutcomeOfLine(int index) {
        return lines.get(index).getOutcome();
    }
}
