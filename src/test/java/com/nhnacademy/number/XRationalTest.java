package com.nhnacademy.number;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class XRationalTest {

    @Test
    @DisplayName("유리수 생성 및 자동 약분")
    void testConstructorWithReduction() {
        XRational rational = new XRational(6, 8);
        assertEquals("[3,4]", rational.toString());
    }

    @Test
    @DisplayName("음수 분자 유리수")
    void testNegativeNumerator() {
        XRational rational = new XRational(-3, 5);
        assertEquals("[-3,5]", rational.toString());
    }

    @Test
    @DisplayName("음수 분모 유리수 (분자로 이동)")
    void testNegativeDenominator() {
        XRational rational = new XRational(3, -5);
        assertEquals("[-3,5]", rational.toString());
    }

    @Test
    @DisplayName("양쪽 모두 음수인 경우")
    void testBothNegative() {
        XRational rational = new XRational(-3, -5);
        assertEquals("[3,5]", rational.toString());
    }

    @Test
    @DisplayName("분모가 0인 경우 예외")
    void testZeroDenominator() {
        assertThrows(ArithmeticException.class, () -> new XRational(1, 0));
    }

    @Test
    @DisplayName("분모가 1인 경우 정수로 표시")
    void testIntegerDisplay() {
        XRational rational = new XRational(5, 1);
        assertEquals("5", rational.toString());
    }

    @Test
    @DisplayName("유리수 + 유리수")
    void testAddRational() {
        XRational a = new XRational(1, 2); // 1/2
        XRational b = new XRational(1, 3); // 1/3
        XNumber result = a.add(b);

        assertEquals("[5,6]", result.toString());
    }

    @Test
    @DisplayName("유리수 + 정수")
    void testAddInteger() {
        XRational a = new XRational(3, 4); // 3/4
        XInteger b = new XInteger(2);
        XNumber result = a.add(b);

        assertEquals("[11,4]", result.toString());
    }

    @Test
    @DisplayName("유리수 - 유리수")
    void testSubtractRational() {
        XRational a = new XRational(3, 4); // 3/4
        XRational b = new XRational(1, 2); // 1/2
        XNumber result = a.subtract(b);

        assertEquals("[1,4]", result.toString());
    }

    @Test
    @DisplayName("유리수 * 유리수")
    void testMultiplyRational() {
        XRational a = new XRational(2, 3);
        XRational b = new XRational(3, 5);
        XNumber result = a.multiply(b);

        assertEquals("[2,5]", result.toString());
    }

    @Test
    @DisplayName("최소 양의 유리수 * 최대 유리수")
    void testAddSpecialRational() {
        XRational a = new XRational(2, Integer.MAX_VALUE); // 1/2
        XRational b = new XRational(Integer.MAX_VALUE, 3); // 1/3
        XNumber result = a.multiply(b);

        assertEquals("[2,3]", result.toString());
    }

    @Test
    @DisplayName("유리수 / 유리수")
    void testDivideRational() {
        XRational a = new XRational(2, 3);
        XRational b = new XRational(4, 5);
        XNumber result = a.divide(b);

        assertEquals("[5,6]", result.toString());
    }

    @Test
    @DisplayName("0으로 나누기 예외")
    void testDivideByZero() {
        XRational a = new XRational(1, 2);
        XRational b = new XRational(0, 1);

        assertThrows(ArithmeticException.class, () -> a.divide(b));
    }

    @Test
    @DisplayName("null 파라메터 예외 - add")
    void testAddNull() {
        XRational a = new XRational(1, 2);

        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    @DisplayName("null 파라메터 예외 - subtract")
    void testSubtractNull() {
        XRational a = new XRational(1, 2);

        assertThrows(IllegalArgumentException.class, () -> a.subtract(null));
    }

    @Test
    @DisplayName("null 파라메터 예외 - multiply")
    void testMultiplyNull() {
        XRational a = new XRational(1, 2);

        assertThrows(IllegalArgumentException.class, () -> a.multiply(null));
    }

    @Test
    @DisplayName("null 파라메터 예외 - divide")
    void testDivideNull() {
        XRational a = new XRational(1, 2);

        assertThrows(IllegalArgumentException.class, () -> a.divide(null));
    }

    @Test
    @DisplayName("분자 오버플로우 예외")
    void testNumeratorOverflow() {
        assertThrows(ArithmeticException.class, () -> {
            new XRational(Integer.MAX_VALUE, 1).multiply(new XRational(2, 1));
        });
    }

    @Test
    @DisplayName("분모 오버플로우 예외")
    void testDenominatorOverflow() {
        assertThrows(ArithmeticException.class, () -> {
            new XRational(1, Integer.MAX_VALUE).multiply(new XRational(1, 2));
        });
    }

    @Test
    @DisplayName("분자 언더플로우 예외")
    void testNumeratorUnderflow() {
        assertThrows(ArithmeticException.class, () -> {
            new XRational(Integer.MIN_VALUE, 1).multiply(new XRational(2, 1));
        });
    }

    @Test
    @DisplayName("equals 테스트")
    void testEquals() {
        XRational a = new XRational(6, 8); // 3/4
        XRational b = new XRational(3, 4); // 3/4
        XRational c = new XRational(1, 2); // 1/2

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    @DisplayName("큰 수의 GCD 계산")
    void testLargeGCD() {
        XRational rational = new XRational(1000000, 2000000);
        assertEquals("[1,2]", rational.toString());
    }

    @Test
    @DisplayName("복잡한 연산 체인")
    void testChainedOperations() {
        XRational a = new XRational(1, 2);
        XRational b = new XRational(1, 3);
        XRational c = new XRational(1, 6);

        // (1/2 + 1/3) * 1/6 = 5/6 * 1/6 = 5/36
        XNumber result = a.add(b).multiply(c);
        assertEquals("[5,36]", result.toString());
    }
}