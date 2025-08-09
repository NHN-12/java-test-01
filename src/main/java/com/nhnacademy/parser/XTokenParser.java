package com.nhnacademy.parser;

import com.nhnacademy.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class XTokenParser{
    /**
     * 문자열 배열을 Token 객체 리스트로 변환합니다.
     *
     * @param tokens 문자열로 된 토큰 배열
     * @return Token 객체 리스트
     * @throws IllegalArgumentException 잘못된 입력이나 인식할 수 없는 토큰
     */

    public static List<XToken> parse(String[] tokens) {
        if(tokens == null || tokens.length == 0) throw new IllegalArgumentException();
        ArrayList<XToken> result = new ArrayList<>();
        for(String token: tokens){
                if (token.isBlank() || token.equals("\t") || token.equals("\n")
                        || (isDouble(token) && !isInteger(token))
                        || Pattern.matches("^[a-zA-Z]*$", token)
                        || (!isDouble(token) && !isInteger(token) && !isCorrectOperator(token))
                )  {
                    throw new IllegalArgumentException(token);
                }
            if(isInteger(token)) {
                result.add(new XTokenNumber(token));
            } else if(isXTokenHighOrderArithmeticOperator(token)){
                result.add(new XTokenHighOrderArithmeticOperator(token));
            } else if(isXTokenLogicOperator(token)){
                result.add(new XTokenLogicOperator(token));
            } else if(isXTokenLowOrderArithmeticOperator(token)) {
                result.add(new XTokenLowOrderArithmeticOperator(token));
            } else if(isXTokenParenthesis(token)) {
                result.add(new XTokenParenthesis(token));
            }
        }
        return result;
    }

    private static boolean isInteger(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException ignored){
            return false;
        }
    }

    private static boolean isDouble(String value){ // 실수는 처리 X
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException ignored){
            return false;
        }
    }

    private static boolean isXTokenHighOrderArithmeticOperator(String value) {
        return "*".equals(value) || "/".equals(value) || "%".equals(value);
    }
    private static boolean isXTokenLogicOperator(String value){
        return "<".equals(value) || ">".equals(value) || "==".equals(value) || "!=".equals(value) || "<=".equals(value) || ">=".equals(value);
    }
    private static boolean isXTokenLowOrderArithmeticOperator(String value) {
        return "+".equals(value) || "-".equals(value);
    }
    private static boolean isXTokenParenthesis(String value){
        return "(".equals(value) || ")".equals(value);
    }

    private static boolean isCorrectOperator(String value){
        return "*".equals(value) || "/".equals(value) || "%".equals(value) || "<".equals(value) || ">".equals(value) ||
                "==".equals(value) || "!=".equals(value) || "<=".equals(value) || ">=".equals(value) || "+".equals(value) ||
                "-".equals(value) || "(".equals(value) || ")".equals(value);
    }
}