package service;

import domain.CountOfLine;
import domain.Height;
import domain.Ladder;
import domain.Line;
import domain.RungsBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LadderService {

    private final RungsBuilder rungsBuilder;

    public LadderService(RungsBuilder rungsBuilder) {
        this.rungsBuilder = rungsBuilder;
    }

    public Ladder createLadder(CountOfLine countOfLine, Height height) {
        final List<Line> lineCollection = createLineCollection(countOfLine, height);
        return new Ladder(lineCollection);
    }

    private List<Line> createLineCollection(CountOfLine countOfLine, Height height) {
        final List<Line> lineCollection = new ArrayList<>();

        for (int index = 0; index < countOfLine.value(); index++) {
            final List<Boolean> prevLineRightStatus = getPrevLineRightStatus(lineCollection, index, height);
            final Line nowLine = createNowLine(index, height, countOfLine, prevLineRightStatus);
            lineCollection.add(nowLine);
        }
        return lineCollection;
    }

    private List<Boolean> getPrevLineRightStatus(List<Line> lineCollection, int index, Height height) {
        if (index == 0) {
            return rungsBuilder.buildTemporaryRungsStatus(height.value());
        }
        final Line prevLine = lineCollection.get(index - 1);
        return prevLine.getRightStatus();
    }

    private Line createNowLine(int index, Height height, CountOfLine countOfLine,
                               List<Boolean> nowLineLeftStatus) {
        final List<Boolean> nowLineRightStatus = createNowLineRightStatus(index, countOfLine, height,
                                                                          nowLineLeftStatus);
        if (index == 0) {
            nowLineLeftStatus = createEmptyStatus(height);
        }
        return Line.of(nowLineLeftStatus, nowLineRightStatus);
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
}
