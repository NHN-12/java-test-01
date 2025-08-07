package com.nhnacademy.token;

public interface XToken {
    /**
     * 토큰의 값을 반환합니다.
     *
     * @return 토큰의 문자열 값
     */
    String getValue();

    /**
     * 토큰의 우선순위를 반환합니다.
     *
     * @return 우선순위 (높을수록 먼저 처리)
     */
    int getPrecedence();
}