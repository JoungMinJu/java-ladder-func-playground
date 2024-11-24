package domain;

import util.Errors;

public class Player {

    private final int MAX_LENGTH = 5;

    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validate(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(Errors.NAME_IS_TOO_LONG);
        }
    }
}
