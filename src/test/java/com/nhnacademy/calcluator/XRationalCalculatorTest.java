package com.nhnacademy.calcluator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import com.nhnacademy.number.XNumber;

class XRationalCalculatorTest {

    private XRationalCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new XRationalCalculator();
    }

    @ParameterizedTest
    @DisplayName("단순 정수 덧셈")
    @CsvSource({
            "'2 + 3', '5'",
            "'2 + -1', '1'",
            "'-2 + 3', '1'",
            "'-2 + -1', '-3'"
    })
    void testSimpleIntegerAddition(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("단순 정수 뺄셈")
    @CsvSource({
            "'10 - 3', '7'",
            "'10 - -3', '13'",
            "'-10 - 3', '-13'",
            "'-10 - -3', '-7'"
    })
    void testSimpleIntegerSubtraction(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("단순 정수 곱셈")
    @CsvSource({
            "'4 * 5', '20'",
            "'4 * -5', '-20'",
            "'-4 * 5', '-20'",
            "'-4 * -5', '20'"
    })
    void testSimpleIntegerMultiplication(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("단순 정수 나눗셈 (나누어떨어지는 경우)")
    @CsvSource({
            "'20 / 4', '5'",
            "'20 / -4', '-5'",
            "'-20 / 4', '-5'",
            "'-20 / -4', '5'"
    })
    void testSimpleIntegerDivisionExact(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("단순 정수 나눗셈 (나누어떨어지지 않는 경우)")
    @CsvSource({
            "'7 / 3', '2'",
            "'7 / -3', '-2'",
            "'-7 / 3', '-2'",
            "'-7 / -3', '2'"
    })
    void testSimpleIntegerDivisionInexact(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("유리수 덧셈")
    @CsvSource({
            "'[1,2] + [1,3]', '[5,6]'",
            "'[1,2] + [-1,1]', '[-1,2]'",
            "'[-1,1] + [1,2]', '[-1,2]'"
    })
    void testRationalAddition(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("유리수와 정수의 혼합 연산")
    @CsvSource({
            "'2 + [1,2]', '[5,2]'",
            "'2 + [-1,1]', '1'"
    })
    void testMixedOperation(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("연산자 우선순위 테스트")
    @CsvSource({
            "'2 + 3 * 4', '14'",
            "'10 - 2 * 3', '4'",
            "'10 - 2 / 3', '10'",
            "'10 - 2 % 3', '8'",
            "'10 - 2 / [3,1]', '[28,3]'"
    })
    void testOperatorPrecedence(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("괄호가 있는 수식")
    @CsvSource({
            "'2  * ( 3 + 4 )', '14'",
            "'2  * ( 3 + [1,2] )', '7'"
    })
    void testParentheses(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("중첩된 괄호")
    @CsvSource({
            "'( ( 2 + 3 ) * 4 ) / 5', '4'",
            "'( ( ( 2 ) + 3 ) * 4 ) / 5', '4'",
            "'( ( 2 + 3 ) * ( 4 ) ) / 5', '4'",
            "'( ( 2 + 3 ) * 4 ) / ( 5 )', '4'",
            "'( ( ( 2 ) + ( 3 ) ) * ( 4 ) ) / ( 5 )', '4'"
    })
    void testNestedParentheses(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("복잡한 수식")
    @CsvSource({
            "'( 2 + [1,2] ) * 3 - 1', '[13,2]'",
            "'( ( 2 + 3 ) * 4 ) / 5', '4'",
            "'( ( [1,2] + [1,3] ) * [6,5] )', '1'"
    })
    void testComplexExpression(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("공백이 포함된 수식")
    @CsvSource({
            "'  2   +   3  *  4  ', '14'",
            "'  2                      +   3  *           4  ', '14'"
    })
    void testExpressionWithSpaces(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("음수 처리")
    @CsvSource({
            "'-5 + 3', '-2'",
            "'-5 - 3', '-8'",
            "'-5 * 3', '-15'",
            "'-5 / 3', '-1'",
            "'-5 + -3', '-8'",
            "'-5 - -3', '-2'",
            "'-5 * -3', '15'",
            "'-5 / -3', '1'"
    })
    void testNegativeNumbers(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @Test
    @DisplayName("0으로 나누기 예외")
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class,
                () -> calculator.calculate("5 / 0"));
    }

    @ParameterizedTest
    @DisplayName("빈 수식 예외")
    @ValueSource(strings = { "", "   ", " \t", "\t\n", "\n\t" })
    void testEmptyExpression(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(input));
    }

    @Test
    @DisplayName("null 수식 예외")
    void testNullExpression() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(null));
    }

    @ParameterizedTest
    @DisplayName("잘못된 유리수 형식")
    @ValueSource(strings = { "[1,2,3]", "[1]", "[1 2]", "[1", "1,2]" })
    void testInvalidRationalFormat(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(input));
    }

    @ParameterizedTest
    @DisplayName("연산자가 연속으로 오는 경우")
    @ValueSource(strings = { "2 + + 3", "2 - + 3" })
    void testConsecutiveOperators(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(input));
    }

    @ParameterizedTest
    @DisplayName("수식이 연산자로 시작하는 경우")
    @ValueSource(strings = { "+ 2 + 3", "+ 2 + 3 + " })
    void testStartsWithOperator(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(input));
    }

    @ParameterizedTest
    @DisplayName("수식이 연산자로 끝나는 경우")
    @ValueSource(strings = { "2 + 3 +", "2 + 3 + + " })
    void testEndsWithOperator(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(input));
    }

    @ParameterizedTest
    @DisplayName("괄호가 맞지 않는 경우")
    @ValueSource(strings = { "( 2 + 3", "2 + 3 )", "( ( 2 + 3 )" })
    void testMismatchedParentheses(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(input));
    }

    @ParameterizedTest
    @DisplayName("유리수 분모가 0인 경우")
    @CsvSource({
            "'[1,0] + 2', '1'",
            "'[1,0] - 2', '1'",
            "'[1,0] * 2', '1'",
            "'[1,0] / 2', '1'",
            "'[1,0] % 2', '1'"
    })
    void testRationalWithZeroDenominator(String input, String expected) {
        assertThrows(ArithmeticException.class,
                () -> calculator.calculate(input));
    }

    @ParameterizedTest
    @DisplayName("복잡한 유리수 연산")
    @CsvSource({
            "'( [1,2] + [1,3] ) * [6,5]', '1'"
    })
    void testComplexRationalCalculation(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("연속된 연산")
    @CsvSource({
            "'1 + 2 + 3 + 4', '10'",
            "'100 - 50 - 20 - 10', '20'"
    })
    void testChainedOperations(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }

    @ParameterizedTest
    @DisplayName("모든 연산자 조합")
    @CsvSource({
            "'2 * 3 + 4 / 2 - 1', '7'",
            "'2 * 3 + 4 / 2 - 1', '7'"
    })
    void testAllOperatorsCombined(String input, String expected) {
        XNumber result = calculator.calculate(input);
        assertEquals(expected, result.toString());
    }
}