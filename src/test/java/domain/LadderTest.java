package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Errors;

class LadderTest {

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
            lineCollection.add(Line.of(inputRungsStatus.get(i - 1), inputRungsStatus.get(i)));
        }
        final Ladder ladder = new Ladder(lineCollection);
        // then
        inputRungsStatus = inputRungsStatus.subList(1, inputRungsStatus.size());
        assertThat(ladder.getRightRungStatus())
            .containsExactlyElementsOf(inputRungsStatus);
    }

    @Test
    @DisplayName("Ladder 내의 모든 Line의 길이가 같지 않다면 예외가 발생한다.")
    void invalidHeightTest() {
        // given
        final Line line1 = Line.of(Arrays.asList(true, false, true), Arrays.asList(false, false, false));
        final Line line2 = Line.of(Arrays.asList(false, false, false, false, false),
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
        List<Line> lineCollection = new ArrayList<>();
        for (int i = 1; i < inputRungsStatus.size(); i++) {
            lineCollection.add(Line.of(inputRungsStatus.get(i - 1), inputRungsStatus.get(i)));
        }
        final Ladder ladder = new Ladder(lineCollection);
        // when
        final List<Integer> result = ladder.getResult();
        // then
        assertThat(result).isEqualTo(List.of(2, 1, 3, 0));
    }

    @Test
    @DisplayName("인접한 line의 경우 좌측 point의 right status 값과 우측 point의 left status 값이 일치하지 않음 예외가 발생한다.")
    void invalidRungTest() {
        // given
        Line line1 = Line.of(Arrays.asList(false, false, false), Arrays.asList(false, true, true));
        Line line2 = Line.of(Arrays.asList(false, false, true), Arrays.asList(false, false, false));
        List<Line> lineCollection = new ArrayList<>(List.of(line1, line2));
        // when
        // then
        assertThatThrownBy(() -> new Ladder(lineCollection))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.ADJACENT_POINTER_STATUS_MATCH);
    }

}
