package dev.oclay.evaluator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EvaluatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1+2+6", "5-8", "5*86*1", "8/6", "10+5-9*4/6"})
    @DisplayName("Should validate all the expression as true")
    public void shouldBeValidExpressions(String expression) {
        assertTrue(Evaluator.isValid(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1+", "-8", "*86*1*", "--", "8**9", "", "  "})
    @DisplayName("Should validate all the expression as false")
    public void shouldBeInvalidExpressions(String expression) {
        assertFalse(Evaluator.isValid(expression));
    }

}
