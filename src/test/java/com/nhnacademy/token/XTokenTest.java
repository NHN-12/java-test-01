package com.nhnacademy.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class XTokenTest {
    @ParameterizedTest
    @DisplayName("XTokenNumber 정상 생성")
    @CsvSource({
            "'123', '123', 0",
            "'-456', '-456', 0",
            "'[1,2]', '[1,2]', 0",
            "'[-1,2]', '[-1,2]', 0",
            "'[-1,-2]', '[-1,-2]', 0"
    })
    void testXTokenNumberCreation(String value, String expectedValue, int expectedPrecedence) {
        XToken token = new XTokenNumber(value);
        assertEquals(expectedValue, token.getValue());
        assertEquals(expectedPrecedence, token.getPrecedence());
    }

    @ParameterizedTest
    @DisplayName("XTokenNumber 잘못된 입력 처리")
    @ValueSource(strings = { "abc", "12.5", "1e10", "" })
    void testInvalidXTokenNumber(String value) {
        assertThrows(IllegalArgumentException.class,
                () -> new XTokenNumber(value));
    }

    @Test
    @DisplayName("XTokenNumber null 처리")
    void testXTokenNumberNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new XTokenNumber(null));
    }

    // ===== XTokenLowPrecedenceOperator 테스트 =====

    @ParameterizedTest
    @DisplayName("XTokenLowPrecedenceOperator 정상 생성")
    @ValueSource(strings = { "<", ">", "==", "!=", "<=", ">=" })
    void testXTokenLowPrecedenceOperatorCreation(String operator) {
        XToken token = new XTokenLogicOperator(operator);
        assertEquals(operator, token.getValue());
        assertEquals(1, token.getPrecedence());
    }

    @ParameterizedTest
    @DisplayName("XTokenLowPrecedenceOperator 잘못된 연산자")
    @ValueSource(strings = { "=", "=>", "=<", "&&", "||" })
    void testInvalidXTokenLowPrecedenceOperator(String operator) {
        assertThrows(IllegalArgumentException.class,
                () -> new XTokenLogicOperator(operator));
    }

    // ===== XTokenMiddlePrecedenceOperator 테스트 (20점) =====

    @ParameterizedTest
    @DisplayName("XTokenMiddlePrecedenceOperator 정상 생성")
    @ValueSource(strings = { "+", "-" })
    void testXTokenMiddlePrecedenceOperator(String operator) {
        XToken token = new XTokenLowOrderArithmeticOperator(operator);
        assertEquals(operator, token.getValue());
        assertEquals(2, token.getPrecedence());
    }

    @ParameterizedTest
    @DisplayName("XTokenMiddlePrecedenceOperator 잘못된 연산자")
    @ValueSource(strings = { "*", "/", "%", "++", "--" })
    void testInvalidLowOrderOperator(String operator) {
        assertThrows(IllegalArgumentException.class,
                () -> new XTokenLowOrderArithmeticOperator(operator));
    }

    // ===== XTokenHighPrecedenceOperator 테스트 =====

    @ParameterizedTest
    @DisplayName("XTokenHighPrecedenceOperator 정상 생성")
    @ValueSource(strings = { "*", "/", "%" })
    void testXTokenHighPrecedenceOperator(String operator) {
        XToken token = new XTokenHighOrderArithmeticOperator(operator);
        assertEquals(operator, token.getValue());
        assertEquals(3, token.getPrecedence());
    }

    @ParameterizedTest
    @DisplayName("XTokenHighPrecedenceOperator 잘못된 연산자")
    @ValueSource(strings = { "+", "-", "**", "//", "%%" })
    void testInvalidHighOrderOperator(String operator) {
        assertThrows(IllegalArgumentException.class,
                () -> new XTokenHighOrderArithmeticOperator(operator));
    }

    // ===== 다형성 테스트 =====

    @Test
    @DisplayName("Token 인터페이스 다형성")
    void testTokenPolymorphism() {
        XToken[] tokens = {
                new XTokenNumber("100"),
                new XTokenLogicOperator(">"),
                new XTokenLowOrderArithmeticOperator("+"),
                new XTokenHighOrderArithmeticOperator("*")
        };

        int[] expectedPrecedence = { 0, 1, 2, 3 };
        String[] expectedValues = { "100", ">", "+", "*" };

        for (int i = 0; i < tokens.length; i++) {
            assertEquals(expectedValues[i], tokens[i].getValue());
            assertEquals(expectedPrecedence[i], tokens[i].getPrecedence());
        }
    }

    // ===== 통합 테스트 =====

    @ParameterizedTest
    @DisplayName("우선순위 비교 테스트")
    @CsvSource({
            "*, +, true", // 곱셈이 덧셈보다 우선
            "/, -, true", // 나눗셈이 뺄셈보다 우선
            "+, >, true", // 덧셈이 비교연산자보다 우선
            "*, /, false", // 같은 우선순위
            "+, -, false" // 같은 우선순위
    })
    void testPrecedenceComparison(String op1, String op2, boolean op1Higher) {
        XToken token1 = createXTokenOperator(op1);
        XToken token2 = createXTokenOperator(op2);

        if (op1Higher) {
            assertTrue(token1.getPrecedence() > token2.getPrecedence());
        } else {
            assertEquals(token1.getPrecedence(), token2.getPrecedence());
        }
    }

    // Helper method
    private XToken createXTokenOperator(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return new XTokenLowOrderArithmeticOperator(operator);
            case "*":
            case "/":
            case "%":
                return new XTokenHighOrderArithmeticOperator(operator);
            case "<":
            case ">":
            case "==":
            case "<=":
            case ">=":
                return new XTokenLogicOperator(operator);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
