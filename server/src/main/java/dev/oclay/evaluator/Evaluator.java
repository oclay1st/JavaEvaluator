package dev.oclay.evaluator;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class Evaluator {

    private final String expression;

    private static final Pattern validationPattern = Pattern.compile("^\\d+((\\+|-|\\*|//)\\d+)*$");

    Evaluator(String expression) {
        this.expression = expression;
    }

    public boolean isValidExpression() throws IllegalArgumentException {
        Objects.requireNonNull(expression);
        return validationPattern.matcher(expression).matches();
    }

    private String [] parse()  {
        StringBuilder currentNumber = new StringBuilder();
        char [] data = expression.toCharArray();
        String [] expressionValues = new String[data.length];
        int index = 0;
        for (char datum : data) {
            if (Character.isDigit(datum)) {
                currentNumber.append(datum);
            } else {
                expressionValues[index] = currentNumber.toString();
                currentNumber.setLength(0);
                expressionValues[index+1] = Character.toString(datum);
                index+=2;
            }
        }
        expressionValues[index] = currentNumber.toString();
        return Arrays.copyOf(expressionValues, index+1);
    }

    public int evaluate() {
        var expressionValues = parse();
        Integer currentValue = Integer.parseInt(expressionValues[0]);
        for (int i = 0; i < expressionValues.length - 1; i+=2) {
            String operation = expressionValues[i+1];
            Integer secondValue = Integer.parseInt(expressionValues[i+2]);
            currentValue = evalOperation(currentValue, operation, secondValue);
        }
        return currentValue;
    }

    private Integer evalOperation(Integer first, String operation, Integer second) {
        return switch (operation) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }
}
