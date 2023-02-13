package dev.oclay.evaluator;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Evaluator {

    private static final Pattern validationPattern = Pattern.compile("^\\d+((\\+|-|\\*|//)\\d+)*$");

    private Evaluator() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValid(String expression)  {
        return Objects.nonNull(expression) && validationPattern.matcher(expression).matches();
    }

    public static String [] parse(String expression)  {
        StringBuilder currentNumber = new StringBuilder();
        char [] data = expression.toCharArray();
        String [] expressionValues = new String[data.length];
        int index = 0;
        for (char character : data) {
            if (Character.isDigit(character)) {
                currentNumber.append(character);
            } else {
                expressionValues[index] = currentNumber.toString();
                currentNumber.setLength(0);
                expressionValues[index+1] = Character.toString(character);
                index+=2;
            }
        }
        expressionValues[index] = currentNumber.toString();
        return Arrays.copyOf(expressionValues, index+1);
    }

    public static int evaluate(String expression) {
        var expressionValues = parse(expression);
        Integer currentValue = Integer.parseInt(expressionValues[0]);
        for (int i = 0; i < expressionValues.length - 1; i+=2) {
            String operation = expressionValues[i+1];
            Integer secondValue = Integer.parseInt(expressionValues[i+2]);
            currentValue = evalOperation(currentValue, operation, secondValue);
        }
        return currentValue;
    }

    public static Integer evalOperation(Integer first, String operation, Integer second) {
        return switch (operation) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }
}
