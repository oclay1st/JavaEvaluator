package dev.oclay.evaluator;

import java.util.ArrayList;
import java.util.List;
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

    private List<String> parse()  {
        StringBuilder currentNumber = new StringBuilder();
        List<String> expressionValues = new ArrayList<>();
        char [] data = expression.toCharArray();
        for (char datum : data) {
            if (Character.isDigit(datum)) {
                currentNumber.append(datum);
            } else {
                expressionValues.add(currentNumber.toString());
                currentNumber.setLength(0);
                expressionValues.add(String.valueOf(datum));
            }
        }
        expressionValues.add(currentNumber.toString());
        return expressionValues;
    }

    public int evaluate() {
        var expressionValues = parse();
        Integer currentValue = Integer.parseInt(expressionValues.get(0));
        for (int i = 0; i < expressionValues.size() - 1; i+=2) {
            String operation = expressionValues.get(i+1);
            Integer secondValue = Integer.parseInt(expressionValues.get(i+2));
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
