package SaarAviviOfekKanariRonAfriat;

import java.util.InputMismatchException;

public class Exception_market extends Exception {
    public void validateUserName(String input) throws Exception_market {
        if (input == null || input.isBlank()) {
            throw new Exception_market("Input shouldn't be empty!");
        }
        if (!input.matches("[a-zA-Z0-9]+")) {
            throw new Exception_market("Illegal character detected!");
        }
    }

    public Exception_market(String message) {
        super(message);
    }

    public int validateInt(String input) throws Exception_market {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new Exception_market("Invalid input. Please enter a number.");
        }
    }

    public int checkRangeAction(String input) throws Exception_market {
        int choice = Integer.parseInt(input);
        if ((choice >= 0) && (choice <= 9)) {
            return choice;
        } else {
            return -1;
        }
    }

    public void validatePassword(String input) throws InputMismatchException {
        if (input == null || input.trim().isEmpty() || input.contains(" ")) {
            throw new InputMismatchException("Input shouldn't be empty!");
        }
    }

    public void validateOnlyLetters(String input) throws InputMismatchException {
        if (input == null || input.trim().isEmpty() || input.contains(" ")) {
            throw new InputMismatchException("Input shouldn't be empty!");
        }
        if (!input.matches("[a-zA-Z]+")) {
            throw new InputMismatchException("Illegal character detected![allowed (a-z, A-z) only]");
        }
    }

    public int positiveNum(int input) throws Exception_market {
        if (input > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void validateFloat(String input) throws InputMismatchException {
        Float.parseFloat(input);
    }

    public int checkRangePrice(String input) throws InputMismatchException {
        float choice = Float.parseFloat(input);
        if (choice > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void validateYN(String input) throws InputMismatchException {
        if (input == null || input.trim().isEmpty() || input.contains(" ")) {
            throw new InputMismatchException("Input shouldn't be empty!");
        }
        if (!input.matches("[YNyn]+")) {
            throw new InputMismatchException("Illegal character detected!");
        }
    }

    public int checkRangeCategory(int input) {
        if ((input > 0) && (input < 5)) {
            return 1;
        } else {
            return 0;
        }
    }
}
