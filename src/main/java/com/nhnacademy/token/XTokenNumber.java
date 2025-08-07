package com.nhnacademy.token;

public class XTokenNumber implements XToken {

    /**
     * 생성자.
     *
     * @param value 토큰의 문자열 값
     */
    public XTokenNumber(String value) {
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
     * @return 우선순위 (숫자 토큰은 0으로 설정)
     */
    @Override
    public int getPrecedence() {
        throw new UnsupportedOperationException();
    }

}
