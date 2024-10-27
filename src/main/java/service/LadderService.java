package service;

import domain.CountOfLadders;
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

    public Ladder createLadder(CountOfLadders countOfLadders, Height height) {
        final List<Line> lineCollection = createLineCollection(countOfLadders, height);
        return new Ladder(lineCollection);
    }

    private List<Line> createLineCollection(CountOfLadders countOfLadders, Height height) {
        final List<Line> lineCollection = new ArrayList<>();

        for (int index = 0; index < countOfLadders.value(); index++) {
            final List<Boolean> prevLineRightStatus = getPrevLineRightStatus(lineCollection, index, height);
            final Line nowLine = createNowLine(index, height, countOfLadders, prevLineRightStatus);
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

    private Line createNowLine(int index, Height height, CountOfLadders countOfLadders,
                               List<Boolean> nowLineLeftStatus) {
        final List<Boolean> nowLineRightStatus = createNowLineRightStatus(index, countOfLadders, height,
                                                                          nowLineLeftStatus);
        return Line.of(nowLineLeftStatus, nowLineRightStatus);
    }

    private List<Boolean> createNowLineRightStatus(int index, CountOfLadders countOfLadders, Height height,
                                                   List<Boolean> prevLineRightStatus) {
        if (index == countOfLadders.value() - 1) {
            return createRightStatusOfLastLine(height);
        }
        return rungsBuilder.buildAndGetRungsStatus(prevLineRightStatus);
    }

    private List<Boolean> createRightStatusOfLastLine(Height height) {
        return IntStream.range(0, height.value())
            .mapToObj(i -> false)
            .collect(Collectors.toList());
    }
}
