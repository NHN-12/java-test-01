package com.nhnacademy.token;

public class XTokenLogicOperator extends XTokenOperator {
    private final String value;
    /**
     * 생성자.
     *
     * @param value 토큰의 문자열 값
     */
    public XTokenLogicOperator(String value) {
        if(value == null || value.isEmpty()) throw new IllegalArgumentException();
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
     * @return 우선순위 (낮은 우선순위로 설정)
     */
    @Override
    public int getPrecedence() {
        if(isCorrectOperator()) return 1;
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isCorrectOperator() {
        return "<".equals(value) || ">".equals(value) || "==".equals(value) || "!=".equals(value) || "<=".equals(value) || ">=".equals(value);
    }
}
