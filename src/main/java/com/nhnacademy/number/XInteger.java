package com.nhnacademy.number;

public class XInteger extends XRational {

    public XInteger() {
    }

    /**
     * 정수 생성자
     *
     * @param value 정수값
     */
    public XInteger(int value) {
    }

    /**
     * 정수값 반환
     *
     * @return 정수값
     */
    public int getValue() {
        return 0;
    }

    @Override
    public XNumber add(XNumber other) {
        return new XInteger();
    }

    @Override
    public XNumber subtract(XNumber other) {
        return new XInteger();
    }

    @Override
    public XNumber multiply(XNumber other) {
        return new XInteger();
    }

    @Override
    public XNumber divide(XNumber other) {
        return new XInteger();
    }

    /**
     * 나머지 연산
     *
     * @param other 나누는 수 (정수여야 하며 0이 아니어야 함)
     * @return 나머지
     * @throws IllegalArgumentException      other가 null인 경우
     * @throws UnsupportedOperationException other가 정수가 아닌 경우
     * @throws ArithmeticException           other가 0인 경우
     */
    public XInteger modulo(XNumber other) {
        return new XInteger();
    }
}