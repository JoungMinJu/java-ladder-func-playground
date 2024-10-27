package service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.CountOfLine;
import domain.Height;
import domain.Ladder;
import domain.RungsBuilder;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LaddersServiceTest {

    static class TestRungsBuilder implements RungsBuilder {

        @Override
        public List<Boolean> buildAndGetRungsStatus(List<Boolean> prevRungsStatus) {
            return Arrays.asList(false, false, false, false, false);
        }

        @Override
        public List<Boolean> buildTemporaryRungsStatus(int height) {
            return Arrays.asList(true,false, true, false, true);
        }
    }

    @Test
    @DisplayName("createLadders()에선 RungsBuilder로 각 줄의 오른쪽 rungs 유무를 받고 이를 활용해 Ladders 객체를 만든다.")
    void test() {
        // given
        final Height height = new Height(5);
        final CountOfLine countOfLine = new CountOfLine(3);
        final TestRungsBuilder testRungsBuilder = new TestRungsBuilder();
        // when
        final LadderService laddersService = new LadderService(testRungsBuilder);
        final Ladder ladder = laddersService.createLadder(countOfLine, height);
        // then
        assertThat(ladder.getRightRungStatus())
            .isEqualTo(
                Arrays.asList(
                    Arrays.asList(false, false, false, false, false),
                    Arrays.asList(false, false, false, false, false),
                    Arrays.asList(false, false, false, false, false)
                )
            );
    }
}
