package service;

import domain.CountOfLine;
import domain.Height;
import domain.Ladder;
import domain.Line;
import domain.RungsBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.Errors;

public class LadderService {

    private final RungsBuilder rungsBuilder;

    public LadderService(RungsBuilder rungsBuilder) {
        this.rungsBuilder = rungsBuilder;
    }

    public Ladder createLadder(Height height, List<String> names, List<String> outcomes) {
        CountOfLine countOfLine = getcountOfLine(names, outcomes);
        List<Line> lineCollection = createLineCollection(countOfLine, height, names, outcomes);
        return new Ladder(lineCollection);
    }

    private List<Line> createLineCollection(CountOfLine countOfLine, Height height, List<String> names, List<String> outcomes) {
         List<Line> lineCollection = new ArrayList<>();

        for (int index = 0; index < countOfLine.value(); index++) {
             List<Boolean> prevLineRightStatus = getPrevLineRightStatus(lineCollection, index, height);
             String name = names.get(index);
             String outcome = outcomes.get(index);
             Line nowLine = createNowLine(index, height, countOfLine, prevLineRightStatus, name, outcome);
            lineCollection.add(nowLine);
        }
        return lineCollection;
    }

    private List<Boolean> getPrevLineRightStatus(List<Line> lineCollection, int index, Height height) {
        if (index == 0) {
            return rungsBuilder.buildTemporaryRungsStatus(height.value());
        }
         Line prevLine = lineCollection.get(index - 1);
        return prevLine.getRightStatus();
    }

    private Line createNowLine(int index, Height height, CountOfLine countOfLine,
                               List<Boolean> nowLineLeftStatus, String name, String outcome) {
         List<Boolean> nowLineRightStatus = createNowLineRightStatus(index, countOfLine, height,
                                                                          nowLineLeftStatus);
        if (index == 0) {
            nowLineLeftStatus = createEmptyStatus(height);
        }
        return Line.of(name, outcome, nowLineLeftStatus, nowLineRightStatus);
    }

    private List<Boolean> createNowLineRightStatus(int index, CountOfLine countOfLine, Height height,
                                                   List<Boolean> prevLineRightStatus) {
        if (index == countOfLine.value() - 1) {
            return createEmptyStatus(height);
        }
        return rungsBuilder.buildAndGetRungsStatus(prevLineRightStatus);
    }

    private List<Boolean> createEmptyStatus(Height height) {
        return IntStream.range(0, height.value())
            .mapToObj(i -> false)
            .collect(Collectors.toList());
    }

    public Map<String, String> getResultToPrint(Map<String, String> result, String targetName) {
        if (isAllMode(targetName)) {
            return Collections.unmodifiableMap(result);
        }
        validateTargetName(result, targetName);
        return Map.of(targetName, result.get(targetName));
    }

    private boolean isAllMode(String targetName) {
        return "all".equals(targetName);
    }

    private void validateTargetName(Map<String, String> result, String targetName) {
        if (!result.containsKey(targetName)) {
            throw new IllegalArgumentException(Errors.TARGET_NAME_MUST_BE_IN_NAMES);
        }
    }

    private CountOfLine getcountOfLine(List<String> names, List<String> outcomes) {
        validateCountOfLine(names, outcomes);
         int valueOfCountOfLine = names.size();
        return new CountOfLine(valueOfCountOfLine);
    }

    private void validateCountOfLine(List<String> names, List<String> outcomes) {
        if (names.size() != outcomes.size()) {
            throw new IllegalArgumentException(Errors.NAMES_AND_OUTCOMES_SIZE_IS_NOT_SAME);
        }
    }
}
