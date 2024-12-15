package domain;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomRungsBuilder implements RungsBuilder {

    private final Random random;

    public RandomRungsBuilder() {
        this.random = new Random();
    }

    @Override
    public List<Boolean> buildAndGetRungsStatus(List<Boolean> prevRungsStatus) {
        return prevRungsStatus.stream()
            .map(this::generateRungIfAbsent)
            .collect(Collectors.toList());
    }

    private boolean generateRungIfAbsent(Boolean doesPrevLadderHaveRung) {
        if (!doesPrevLadderHaveRung) {
            return random.nextBoolean();
        }
        return false;
    }

    @Override
    public List<Boolean> buildTemporaryRungsStatus(int height) {
        return IntStream.range(0, height)
            .mapToObj(i -> random.nextBoolean())
            .collect(Collectors.toList());
    }
}
