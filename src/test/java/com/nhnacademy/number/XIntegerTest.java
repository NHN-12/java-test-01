package com.nhnacademy.number;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class XIntegerTest {

    @Test
    @DisplayName("정수 생성 테스트")
    void testConstructor() {
        XInteger integer = new XInteger(42);
        assertEquals(42, integer.getValue());
        assertEquals("42", integer.toString());
    }

    @Test
    @DisplayName("음수 생성 테스트")
    void testNegativeInteger() {
        XInteger integer = new XInteger(-15);
        assertEquals(-15, integer.getValue());
        assertEquals("-15", integer.toString());
    }

    @Test
    @DisplayName("정수 + 정수 = 정수")
    void testAddInteger() {
        XInteger a = new XInteger(10);
        XInteger b = new XInteger(20);
        XNumber result = a.add(b);

        assertTrue(result instanceof XInteger);
        assertEquals("30", result.toString());
    }

    @Test
    @DisplayName("정수 + 유리수 = 유리수")
    void testAddRational() {
        XInteger a = new XInteger(5);
        XRational b = new XRational(3, 4); // 3/4
        XNumber result = a.add(b);

        assertTrue(result instanceof XRational);
        assertEquals("[23,4]", result.toString());
    }

    @Test
    @DisplayName("정수 - 정수")
    void testSubtractInteger() {
        XInteger a = new XInteger(30);
        XInteger b = new XInteger(12);
        XNumber result = a.subtract(b);

        assertTrue(result instanceof XInteger);
        assertEquals("18", result.toString());
    }

    @Test
    @DisplayName("정수 * 정수")
    void testMultiplyInteger() {
        XInteger a = new XInteger(6);
        XInteger b = new XInteger(7);
        XNumber result = a.multiply(b);

        assertTrue(result instanceof XInteger);
        assertEquals("42", result.toString());
    }

    @Test
    @DisplayName("정수 / 정수")
    void testDivideExact() {
        XInteger a = new XInteger(20);
        XInteger b = new XInteger(4);
        XNumber result = a.divide(b);

        assertTrue(result instanceof XInteger);
        assertEquals("5", result.toString());
    }

    @Test
    @DisplayName("정수 / 정수")
    void testDivideInexact() {
        XInteger a = new XInteger(7);
        XInteger b = new XInteger(3);
        XNumber result = a.divide(b);

        assertTrue(result instanceof XInteger);
        assertEquals("2", result.toString());
    }

    @Test
    @DisplayName("정수 / 유리수")
    void testDivideRational() {
        XInteger a = new XInteger(7);
        XRational b = new XRational(3, 1);
        XNumber result = a.divide(b);

        assertTrue(result instanceof XRational);
        assertEquals("[7,3]", result.toString());
    }

    @Test
    @DisplayName("나머지 연산 테스트")
    void testModulo() {
        XInteger a = new XInteger(17);
        XInteger b = new XInteger(5);
        XInteger result = a.modulo(b);

        assertEquals(2, result.getValue());
    }

    @Test
    @DisplayName("음수 나머지 연산")
    void testModuloNegative() {
        XInteger a = new XInteger(-17);
        XInteger b = new XInteger(5);
        XInteger result = a.modulo(b);

        assertEquals(-2, result.getValue());
    }

    @Test
    @DisplayName("0으로 나머지 연산 시 예외")
    void testModuloByZero() {
        XInteger a = new XInteger(10);
        XInteger b = new XInteger(0);

        assertThrows(ArithmeticException.class, () -> a.modulo(b));
    }

    @Test
    @DisplayName("null로 나머지 연산 시 예외")
    void testModuloNull() {
        XInteger a = new XInteger(10);

        assertThrows(IllegalArgumentException.class, () -> a.modulo(null));
    }

    @Test
    @DisplayName("유리수로 나머지 연산 시 예외")
    void testModuloWithRational() {
        XInteger a = new XInteger(10);
        XRational b = new XRational(3, 2);

        assertThrows(UnsupportedOperationException.class, () -> a.modulo(b));
    }

    @Test
    @DisplayName("null 파라메터로 add 호출 시 예외")
    void testAddNull() {
        XInteger a = new XInteger(10);

        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    @DisplayName("equals 테스트")
    void testEquals() {
        XInteger a = new XInteger(42);
        XInteger b = new XInteger(42);
        XInteger c = new XInteger(24);

        assertEquals(a, b);
        assertNotEquals(a, c);
    }
}