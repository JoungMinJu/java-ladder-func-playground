package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Errors;

class LineTest {

    @Test
    @DisplayName("Line의 우측 사다리 유무를 전달받을 수 있다")
    void getRungsStatusTest() {
        // given
        final List<Boolean> inputLeftRungStatus = Arrays.asList(false, false, false, false, false, false);
        final List<Boolean> inputRightRungStatus = Arrays.asList(true, false, false, false, true, true);

        // when
        final Line line = Line.of(inputLeftRungStatus, inputRightRungStatus);
        // then
        assertThat(line.getRightStatus())
            .containsExactlyElementsOf(inputRightRungStatus);
    }

    @Test
    @DisplayName("Line을 생성할 때, rightStatus와 leftStatus의 길이가 일치하지 않으면 에외가 발생한다.")
    void invalidLineConstructorTest() {
        // given
        final List<Boolean> inputLeftRungStatus = Arrays.asList(false, false, false);
        final List<Boolean> inputRightRungStatus = Arrays.asList(true, false, false, false, true, true);
        // when
        // then
        assertThatThrownBy(() -> Line.of(inputLeftRungStatus, inputRightRungStatus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.RUNG_STATUS_LENGTH_MUST_MATCH);
    }

    @Test
    @DisplayName("Line을 생성할 때, 왼쪽 오른쪽이 모두 연결된 point를 생성하려 시도하면 예외가 발생한다.")
    void invalidPointTest() {
        // given
        final List<Boolean> inputLeftRungStatus = Arrays.asList(false, false, false, false, true, true);
        final List<Boolean> inputRightRungStatus = Arrays.asList(true, false, false, false, true, true);
        // when
        // then
        assertThatThrownBy(() -> Line.of(inputLeftRungStatus, inputRightRungStatus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.ADJACENT_LADDERS_CANNOT_HAVE_RUNG_AT_SAME_POSITION);
    }

}
