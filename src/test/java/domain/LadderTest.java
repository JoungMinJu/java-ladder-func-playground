package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Errors;

class LadderTest {

    private static final String name = "이름";
    private static final String outcome = "결과";

    @Test
    @DisplayName("Ladder를 통해 각 사다리의 우측 가로줄 유무를 모두 받아올 수 있다.")
    void constructorTest() {
        // given
        List<List<Boolean>> inputRungsStatus = Arrays.asList(
            Arrays.asList(false, false, false, false), // first Line의 임시 left rung status
            Arrays.asList(true, false, false, true),
            Arrays.asList(false, false, true, false),
            Arrays.asList(true, true, false, true),
            Arrays.asList(false, false, true, false)
        );
        // when
        List<Line> lineCollection = new ArrayList<>();
        for (int i = 1; i < inputRungsStatus.size(); i++) {
            lineCollection.add(Line.of(name, outcome, inputRungsStatus.get(i - 1), inputRungsStatus.get(i)));
        }
         Ladder ladder = new Ladder(lineCollection);
        // then
        inputRungsStatus = inputRungsStatus.subList(1, inputRungsStatus.size());
        assertThat(ladder.getRightRungStatus())
            .containsExactlyElementsOf(inputRungsStatus);
    }

    @Test
    @DisplayName("Ladder 내의 모든 Line의 길이가 같지 않다면 예외가 발생한다.")
    void invalidHeightTest() {
        // given
         Line line1 = Line.of(name, outcome, Arrays.asList(true, false, true), Arrays.asList(false, false, false));
         Line line2 = Line.of(name, outcome, Arrays.asList(false, false, false, false, false),
                                   Arrays.asList(true, false, false, true, true));
        // when
        List<Line> lineCollection = Arrays.asList(line1, line2);
        // then
        assertThatThrownBy(() -> new Ladder(lineCollection))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.ALL_LINE_MUST_HAVE_SAME_HEIGHT);
    }

    @Test
    @DisplayName("사다리를 탄 결과를 받을 수 있다.")
    void resultTest() {
        // given
        List<List<Boolean>> inputRungsStatus = Arrays.asList(
            Arrays.asList(false, false, false, false), // first Line의 임시 left rung status
            Arrays.asList(true, false, false, true),
            Arrays.asList(false, false, true, false),
            Arrays.asList(true, true, false, true),
            Arrays.asList(false, false, false, false)
        );
        List<String> names = Arrays.asList("일번", "이번", "삼번", "사번");
        List<String> outcomes = Arrays.asList("100", "200", "300", "400");
        List<Line> lineCollection = new ArrayList<>();
        for (int i = 1; i < inputRungsStatus.size(); i++) {
            String name = names.get(i-1);
            String outcome = outcomes.get(i-1);
            lineCollection.add(Line.of(name, outcome, inputRungsStatus.get(i - 1), inputRungsStatus.get(i)));
        }
         Ladder ladder = new Ladder(lineCollection);
        // when
         Map<String, String> result = ladder.getResult();
        // then
        assertThat(result).isEqualTo(Map.of("일번", "300",
                                            "이번", "200",
                                            "삼번", "400",
                                            "사번", "100"));

    }

    @Test
    @DisplayName("인접한 line의 경우 좌측 point의 right status 값과 우측 point의 left status 값이 일치하지 않음 예외가 발생한다.")
    void invalidRungTest() {
        // given
        Line line1 = Line.of(name, outcome, Arrays.asList(false, false, false), Arrays.asList(false, true, true));
        Line line2 = Line.of(name, outcome, Arrays.asList(false, false, true), Arrays.asList(false, false, false));
        List<Line> lineCollection = new ArrayList<>(List.of(line1, line2));
        // when
        // then
        assertThatThrownBy(() -> new Ladder(lineCollection))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.ADJACENT_POINTER_STATUS_MATCH);
    }

}
