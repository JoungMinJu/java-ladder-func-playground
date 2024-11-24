package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Height;
import domain.Ladder;
import domain.RungsBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Errors;

class LaddersServiceTest {

    final static TestRungsBuilder testRungsBuilder = new TestRungsBuilder();
    static LadderService laddersService = new LadderService(testRungsBuilder);


    static class TestRungsBuilder implements RungsBuilder {

        @Override
        public List<Boolean> buildAndGetRungsStatus(List<Boolean> prevRungsStatus) {
            return Arrays.asList(false, false, false, false, false);
        }

        @Override
        public List<Boolean> buildTemporaryRungsStatus(int height) {
            return Arrays.asList(true, false, true, false, true);
        }
    }

    @Test
    @DisplayName("createLadders()에선 RungsBuilder로 각 줄의 오른쪽 rungs 유무를 받고 이를 활용해 Ladders 객체를 만든다.")
    void test() {
        // given
        Height height = new Height(5);
        List<String> names = Arrays.asList("일번", "이번", "삼번", "사번");
        List<String> outcomes = Arrays.asList("100", "200", "300", "400");
        // when
        Ladder ladder = laddersService.createLadder(height, names, outcomes);
        // then
        assertThat(ladder.getRightRungStatus())
            .isEqualTo(
                Arrays.asList(
                    Arrays.asList(false, false, false, false, false),
                    Arrays.asList(false, false, false, false, false),
                    Arrays.asList(false, false, false, false, false),
                    Arrays.asList(false, false, false, false, false)
                )
            );
    }

    @Test
    @DisplayName("이름과 결과의 사이즈가 다르면 예외가 발생한다.")
    void invalidCountOfLadderTest() {
        // given
        Height height = new Height(5);
        List<String> names = List.of("일", "이");
        List<String> outcomes = List.of("1000", "2000", "3000");
        // when
        // then
        assertThatThrownBy(() -> laddersService.createLadder(height, names, outcomes))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.NAMES_AND_OUTCOMES_SIZE_IS_NOT_SAME);
    }

    @Test
    @DisplayName("원하는 참가자의 실행 결과를 조회할 수 있다.")
    void selectTargetNameTest() {
        // given
        Map<String, String> result = Map.of("일번", "2000",
                                            "이번", "꽝",
                                            "삼번", "1000");
        String targetName = "이번";
        // when
        Map<String, String> resultToPrint = laddersService.getResultToPrint(result, targetName);
        // then
        assertThat(resultToPrint)
            .isEqualTo(Map.of("이번", "꽝"));
    }

    @Test
    @DisplayName("결과를 보고 싶은 사람에 all을 입력하면 전체 결과를 조회할 수 있다.")
    void selectAllTest() {
        // given
        Map<String, String> result = Map.of("일번", "2000",
                                            "이번", "꽝",
                                            "삼번", "1000");
        String targetName = "all";
        // when
        Map<String, String> resultToPrint = laddersService.getResultToPrint(result, targetName);
        // then
        assertThat(resultToPrint)
            .isEqualTo(result);
    }

    @Test
    @DisplayName("결과를 보고싶은 사람이 참가자 명단에 없으면 예외가 발생한다.")
    void invalidSelectTest() {
        // given
        Map<String, String> result = Map.of("일번", "2000",
                                            "이번", "꽝",
                                            "삼번", "1000");
        String targetName = "오번";
        // when
        // then
        assertThatThrownBy(() -> laddersService.getResultToPrint(result, targetName))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.TARGET_NAME_MUST_BE_IN_NAMES);
    }
}
