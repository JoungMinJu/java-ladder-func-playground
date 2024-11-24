package view;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import util.Errors;

public class InputView {

    private Scanner scanner;

    public int getUserIntegerInput() {
        try {
            scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(Errors.INPUT_IS_NOT_INTEGER);
        }
    }

    public List<String> getStringList() {
         String input = getString();
        return Arrays.asList(input.split(","));
    }

    public String getString() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
