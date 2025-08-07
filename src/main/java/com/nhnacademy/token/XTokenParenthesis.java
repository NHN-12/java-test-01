package com.nhnacademy.token;

public class XTokenParenthesis implements XToken {
    /**
     * 생성자.
     *
     * @param value 토큰의 문자열 값
     */
    public XTokenParenthesis(String value) {
    }

    /**
     * 토큰의 값을 반환합니다.
     *
     * @return 토큰의 문자열 값
     */
    @Override
    public String getValue() {
        return "";
    }

    /**
     * 토큰의 우선순위를 반환합니다.
     *
     * @return 우선순위 (괄호는 특별한 우선순위로 설정)
     */
    @Override
    public int getPrecedence() {
        throw new UnsupportedOperationException();
    }

}
