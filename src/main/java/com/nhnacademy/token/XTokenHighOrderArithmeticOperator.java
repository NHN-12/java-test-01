package com.nhnacademy.token;

public class XTokenHighOrderArithmeticOperator extends XTokenOperator {
    private String value;
    /**
     * 생성자.
     *
     * @param value 토큰의 문자열 값
     */
    public XTokenHighOrderArithmeticOperator(String value) {
        if(value == null) throw new IllegalArgumentException();
        this.value = value;
        if(!isCorrectOperator()) throw new IllegalArgumentException();
    }

    /**
     * 토큰의 값을 반환합니다.
     *
     * @return 토큰의 문자열 값
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * 토큰의 우선순위를 반환합니다.
     *
     * @return 우선순위 (높은 우선순위로 설정)
     */
    @Override
    public int getPrecedence() {
        if(isCorrectOperator()) return 3;
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isCorrectOperator() {
        return "*".equals(value) || "/".equals(value) || "%".equals(value);
    }
}
