package com.nhnacademy.calcluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.nhnacademy.number.XInteger;
import com.nhnacademy.number.XNumber;
import com.nhnacademy.number.XRational;
import com.nhnacademy.parser.XTokenParser;
import com.nhnacademy.token.XToken;
import com.nhnacademy.token.XTokenNumber;
import com.nhnacademy.token.XTokenOperator;
import com.nhnacademy.token.XTokenParenthesis;

public class XRationalCalculator {

    /**
     * 수식을 계산합니다.
     *
     * @param expression 계산할 수식
     * @return 계산 결과
     * @throws IllegalArgumentException 잘못된 수식 형식
     * @throws ArithmeticException      계산 중 오류 (0으로 나누기 등)
     */
    public XNumber calculate(String expression) {
        return null;
    }

    /**
     * 토큰 검증
     */
    private void validateTokens(List<XToken> tokens) {
    }

    /**
     * 숫자 파싱
     */
    private XNumber parseNumber(String token) {
        return null;
    }

    /**
     * 수식 평가 (중위 표기법을 후위 표기법으로 변환 후 계산)
     */
    private XNumber evaluate(List<XToken> tokens) {
        return null;
    }

    /**
     * 중위 표기법을 후위 표기법으로 변환 (Shunting-yard 알고리즘)
     */
    private List<XToken> toPostfix(List<XToken> infix) {
        return infix;
    }
}