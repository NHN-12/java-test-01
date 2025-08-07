package com.nhnacademy.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.nhnacademy.token.XToken;
import com.nhnacademy.token.XTokenHighOrderArithmeticOperator;
import com.nhnacademy.token.XTokenLogicOperator;
import com.nhnacademy.token.XTokenLowOrderArithmeticOperator;
import com.nhnacademy.token.XTokenNumber;
import com.nhnacademy.token.XTokenParenthesis;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.stream.Stream;

public class XTokenParserTest {

    // ===== 기본 파싱 테스트 =====

    @Test
    @DisplayName("단순 산술 연산 파싱")
    void testSimpleArithmeticParsing() {
        String[] input = { "5", "+", "3" };
        List<XToken> tokens = XTokenParser.parse(input);

        assertEquals(3, tokens.size());

        // 첫 번째 토큰: 숫자 5
        assertTrue(tokens.get(0) instanceof XTokenNumber);
        assertEquals("5", tokens.get(0).getValue());
        assertEquals(0, tokens.get(0).getPrecedence());

        // 두 번째 토큰: 연산자 +
        assertTrue(tokens.get(1) instanceof XTokenLowOrderArithmeticOperator);
        assertEquals("+", tokens.get(1).getValue());
        assertEquals(2, tokens.get(1).getPrecedence());

        // 세 번째 토큰: 숫자 3
        assertTrue(tokens.get(2) instanceof XTokenNumber);
        assertEquals("3", tokens.get(2).getValue());
        assertEquals(0, tokens.get(2).getPrecedence());
    }

    @Test
    @DisplayName("복합 연산 파싱")
    void testComplexExpressionParsing() {
        String[] input = { "10", "*", "5", "+", "3", "-", "2", "/", "1" };
        List<XToken> tokens = XTokenParser.parse(input);

        assertEquals(9, tokens.size());

        // 타입 검증
        assertTrue(tokens.get(0) instanceof XTokenNumber);
        assertTrue(tokens.get(1) instanceof XTokenHighOrderArithmeticOperator);
        assertTrue(tokens.get(2) instanceof XTokenNumber);
        assertTrue(tokens.get(3) instanceof XTokenLowOrderArithmeticOperator);
        assertTrue(tokens.get(4) instanceof XTokenNumber);
        assertTrue(tokens.get(5) instanceof XTokenLowOrderArithmeticOperator);
        assertTrue(tokens.get(6) instanceof XTokenNumber);
        assertTrue(tokens.get(7) instanceof XTokenHighOrderArithmeticOperator);
        assertTrue(tokens.get(8) instanceof XTokenNumber);

        // 값 검증
        assertEquals("10", tokens.get(0).getValue());
        assertEquals("*", tokens.get(1).getValue());
        assertEquals("5", tokens.get(2).getValue());
        assertEquals("+", tokens.get(3).getValue());
    }

    // ===== 연산자별 파싱 테스트 =====

    @ParameterizedTest
    @DisplayName("비교 연산자 파싱")
    @ValueSource(strings = { "<", ">", "==", "<=", ">=" })
    void testXTokenLowPrecedenceOperatorParsing(String operator) {
        String[] input = { "10", operator, "5" };
        List<XToken> tokens = XTokenParser.parse(input);

        assertEquals(3, tokens.size());
        assertTrue(tokens.get(1) instanceof XTokenLogicOperator);
        assertEquals(operator, tokens.get(1).getValue());
        assertEquals(1, tokens.get(1).getPrecedence());
    }

    @ParameterizedTest
    @DisplayName("모든 산술 연산자 파싱")
    @MethodSource("provideArithmeticOperators")
    void testAllArithmeticOperators(String operator, Class<?> expectedClass, int precedence) {
        String[] input = { "100", operator, "20" };
        List<XToken> tokens = XTokenParser.parse(input);

        assertEquals(3, tokens.size());
        assertTrue(expectedClass.isInstance(tokens.get(1)));
        assertEquals(operator, tokens.get(1).getValue());
        assertEquals(precedence, tokens.get(1).getPrecedence());
    }

    private static Stream<Arguments> provideArithmeticOperators() {
        return Stream.of(
                Arguments.of("+", XTokenLowOrderArithmeticOperator.class, 2),
                Arguments.of("-", XTokenLowOrderArithmeticOperator.class, 2),
                Arguments.of("*", XTokenHighOrderArithmeticOperator.class, 3),
                Arguments.of("/", XTokenHighOrderArithmeticOperator.class, 3),
                Arguments.of("%", XTokenHighOrderArithmeticOperator.class, 3));
    }

    // ===== 특수 케이스 테스트 =====

    @Test
    @DisplayName("음수 파싱")
    void testNegativeNumberParsing() {
        String[] input = { "-5", "+", "-10", "*", "-3" };
        List<XToken> tokens = XTokenParser.parse(input);

        assertEquals(5, tokens.size());
        assertEquals("-5", tokens.get(0).getValue());
        assertEquals("-10", tokens.get(2).getValue());
        assertEquals("-3", tokens.get(4).getValue());

        assertTrue(tokens.get(0) instanceof XTokenNumber);
        assertTrue(tokens.get(2) instanceof XTokenNumber);
        assertTrue(tokens.get(4) instanceof XTokenNumber);
    }

    @Test
    @DisplayName("괄호 처리")
    void testParenthesesHandling() {
        String[] input = { "(", "5", "+", "3", ")", "*", "2" };
        assertDoesNotThrow(() -> {
            List<XToken> tokens = XTokenParser.parse(input);

            assertEquals(7, tokens.size());
            for (int i = 0; i < input.length; i++) {
                assertEquals(input[i], tokens.get(i).getValue());
            }
        });
    }

    // ===== 예외 처리 테스트 =====

    @Test
    @DisplayName("null 및 빈 배열 처리")
    void testNullAndEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> XTokenParser.parse(null));
        assertThrows(IllegalArgumentException.class, () -> XTokenParser.parse(new String[] {}));
    }

    @ParameterizedTest
    @DisplayName("잘못된 토큰 처리")
    @ValueSource(strings = { "abc", "12.5", "1e10", "++", "--", "**", "&", "|" })
    void testInvalidTokens(String invalidToken) {
        String[] input = { "5", invalidToken, "3" };

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> XTokenParser.parse(input));

        // 예외 메시지에 잘못된 토큰이 포함되어야 함
        assertTrue(exception.getMessage().contains(invalidToken));
    }

    @ParameterizedTest
    @DisplayName("공백 처리")
    @ValueSource(strings = { " ", "  ", "\t", "\n" })
    void testEmptyStringTokens(String emptyToken) {
        String[] input = { "5", emptyToken, "3" };

        assertThrows(IllegalArgumentException.class, () -> XTokenParser.parse(input));
    }

    // ===== 통합 테스트 =====

    @Test
    @DisplayName("복잡한 수식 통합 파싱")
    void testCompleteExpressionParsing() {
        String[] input = { "(", "100", "+", "50", ")", "*", "2", ">=", "200", "-", "50" };
        List<XToken> tokens = XTokenParser.parse(input);

        // 괄호를 제외한 9개 토큰
        assertEquals(input.length, tokens.size());

        // 토큰 순서 및 타입 검증
        XToken[] expectedTypes = {
                new XTokenParenthesis("("),
                new XTokenNumber("100"),
                new XTokenLowOrderArithmeticOperator("+"),
                new XTokenNumber("50"),
                new XTokenParenthesis(")"),
                new XTokenHighOrderArithmeticOperator("*"),
                new XTokenNumber("2"),
                new XTokenLogicOperator(">="),
                new XTokenNumber("200"),
                new XTokenLowOrderArithmeticOperator("-"),
                new XTokenNumber("50")
        };

        for (int i = 0; i < tokens.size(); i++) {
            assertEquals(expectedTypes[i].getClass(), tokens.get(i).getClass());
            assertEquals(expectedTypes[i].getValue(), tokens.get(i).getValue());
            assertEquals(expectedTypes[i].getPrecedence(), tokens.get(i).getPrecedence());
        }
    }

    // ===== 추가 검증 테스트 =====

    @Test
    @DisplayName("연속된 숫자 처리")
    void testConsecutiveNumbers() {
        String[] input = { "5", "10", "15" };

        // 연속된 숫자는 수식으로 유효하지 않을 수 있지만,
        // 파서는 단순히 토큰으로 변환만 함
        List<XToken> tokens = XTokenParser.parse(input);
        assertEquals(3, tokens.size());
        assertTrue(tokens.stream().allMatch(t -> t instanceof XTokenNumber));
    }

    @Test
    @DisplayName("연속된 연산자 처리")
    void testConsecutiveOperators() {
        String[] input = { "5", "+", "-", "3" };

        // 파서는 단순히 토큰으로 변환만 함
        // 유효성 검증은 다른 단계에서 수행
        List<XToken> tokens = XTokenParser.parse(input);
        assertEquals(4, tokens.size());
    }

    @Test
    @DisplayName("대용량 입력 처리")
    void testLargeInput() {
        // 1001개의 토큰 생성
        // 0 + 2 + 4 + ... + 1000
        String[] input = new String[1001];
        for (int i = 0; i < input.length; i += 2) {
            input[i] = String.valueOf(i);
            if (i < 999) {
                input[i + 1] = "+";
            }
        }

        List<XToken> tokens = XTokenParser.parse(input);
        assertEquals(1001, tokens.size());
    }
}