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
        if(denominator == 0) throw new ArithmeticException();
        if(numerator > 0 && denominator > 0){
            int g = gcd(numerator, denominator);
            this.numerator = numerator / g;
            this.denominator = denominator / g;
        } else if(numerator < 0 && denominator > 0){
            this.numerator =numerator;
            this.denominator = denominator;
        } else if(numerator > 0){
            this.numerator = numerator * -1;
            this.denominator = denominator * -1;
        } else if(numerator < 0){
            int g = gcd(numerator * -1, denominator * -1);
            this.numerator = numerator * -1 / g;
            this.denominator = denominator * -1 / g;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    /**
     * 정수를 유리수로 변환하는 생성자
     *
     * @param numerator 정수값
     */
    public XRational(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public int getNumerator() {
        return Math.toIntExact(this.numerator);
    }

    public int getDenominator() {
        return Math.toIntExact(denominator);
    }

    @Override
    public XNumber add(XNumber other) {
        if(other == null) throw new IllegalArgumentException();
        XRational xRational = new XRational();
        if(other instanceof XRational) {
            xRational = (XRational) other;
        }
        return new XRational(this.getNumerator() * xRational.getDenominator() + this.getDenominator() * xRational.getNumerator(), this.getDenominator() * xRational.getDenominator());
    }

    @Override
    public XNumber subtract(XNumber other) {
        if(other == null) throw new IllegalArgumentException();
        XRational xRational = new XRational();
        if(other instanceof XRational) {
            xRational = (XRational) other;
        }
        return new XRational(this.getNumerator() * xRational.getDenominator() - this.getDenominator() * xRational.getNumerator(), this.getDenominator() * xRational.getDenominator());
    }

    @Override
    public XNumber multiply(XNumber other) {
        if(other == null) throw new IllegalArgumentException();
        XRational xRational = new XRational();
        if(other instanceof XRational) {
            xRational = (XRational) other;
        }
        if(this.getDenominator() == Integer.MAX_VALUE && xRational.getNumerator() == Integer.MAX_VALUE) {
            return new XRational(this.getNumerator(), xRational.getDenominator());
        }
        if(this.getDenominator() == Integer.MAX_VALUE || this.getDenominator() == Integer.MIN_VALUE
                ||this.getNumerator() == Integer.MAX_VALUE || this.getNumerator() == Integer.MIN_VALUE
                || xRational.getDenominator() == Integer.MAX_VALUE || xRational.getDenominator() == Integer.MIN_VALUE
                ||xRational.getNumerator() == Integer.MAX_VALUE || xRational.getNumerator() == Integer.MIN_VALUE){
            throw new ArithmeticException();
        }
        return new XRational(this.getNumerator() * xRational.getNumerator(), this.getDenominator() * xRational.getDenominator());
    }

    @Override
    public XNumber divide(XNumber other) {
        if(other == null) throw new IllegalArgumentException();
        XRational xRational = new XRational();
        if(other instanceof XRational) {
            xRational = (XRational) other;
        }
        if(xRational.getDenominator() == 0) throw new ArithmeticException();
        return this.multiply(new XRational(xRational.getDenominator(), xRational.getNumerator()));
    }

    @Override
    public int compareTo(XNumber other) {
        return 0;
    }

    @Override
    public String toString() {
        if(denominator != 1) return "[" + numerator + "," + denominator + "]";
        return "" + numerator;
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