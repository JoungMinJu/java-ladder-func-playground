package domain;

import java.util.function.Predicate;
import util.Errors;

public record Height(int value) {

    private static final Predicate<Integer> isNegative = value -> value <= 0;

    public Height {
        validate(value);
    }

    private void validate(int value) {
        validatePositive(value);
    }

    private void validatePositive(int value) {
        if (isNegative.test(value)) {
            throw new IllegalArgumentException(Errors.LADDER_HEIGHT_MUST_BE_POSITIVE);
        }
    }
}
