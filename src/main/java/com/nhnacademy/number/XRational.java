package com.nhnacademy.number;

public class XRational implements XNumber {
    protected final long numerator;
    protected final long denominator;

    public XRational() {
        numerator = 0;
        denominator = 1;
    }

    /**
     * 유리수 생성자
     *
     * @param numerator   분자
     * @param denominator 분모 (0이 아니어야 함)
     * @throws ArithmeticException 분모가 0인 경우
     */
    public XRational(int numerator, int denominator) {
        this();
    }

    /**
     * 정수를 유리수로 변환하는 생성자
     *
     * @param numerator 정수값
     */
    public XRational(int numerator) {
        this();
    }

    public int getNumerator() {
        return 0;
    }

    public int getDenominator() {
        return 0;
    }

    @Override
    public XNumber add(XNumber other) {
        return new XRational();
    }

    @Override
    public XNumber subtract(XNumber other) {
        return new XRational();
    }

    @Override
    public XNumber multiply(XNumber other) {
        return new XRational();
    }

    @Override
    public XNumber divide(XNumber other) {
        return new XRational();
    }

    @Override
    public int compareTo(XNumber other) {
        return 0;
    }

    @Override
    public String toString() {
        return "[" + numerator + "," + denominator + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof XRational)) {
            return false;
        }

        XRational other = (XRational) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * (int) numerator + (int) denominator;
    }
}