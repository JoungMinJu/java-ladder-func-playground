package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Errors;

class PointTest {

    @Test
    @DisplayName("좌측, 우측이 모두 연결된 point가 생성되면 예외가 발생한다.")
    void invalidPointTest() {
        // given
        boolean left = true;
        boolean right = true;
        // when
        // then
        assertThatThrownBy(() -> new Point(left, right))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.ADJACENT_LADDERS_CANNOT_HAVE_RUNG_AT_SAME_POSITION);
    }


}
