package domain;

import util.Errors;

public record CountOfLine(int value) {

    public CountOfLine {
        validate(value);
    }

    private void validate(int value) {
        validateIsNotEmpty(value);
    }

    private void validateIsNotEmpty(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(Errors.LADDERS_MUST_CONTAINS_LEAST_ONE_LADDER);
        }
    }
}
