package domain;

import java.util.function.Predicate;
import util.Errors;

public record CountOfLine(int value) {

    private static final Predicate<Integer> ismpty = value -> value <= 0;

    public CountOfLine {
        validate(value);
    }

    private void validate(int value) {
        validateIsNotEmpty(value);
    }

    private void validateIsNotEmpty(int value) {
        if (ismpty.test(value)) {
            throw new IllegalArgumentException(Errors.LADDERS_MUST_CONTAINS_LEAST_ONE_LADDER);
        }
    }
}
