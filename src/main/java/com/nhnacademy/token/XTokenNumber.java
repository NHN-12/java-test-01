package com.nhnacademy.token;

import java.util.regex.Pattern;

public class XTokenNumber implements XToken {
    private String value;
    /**
     * 생성자.
     *
     * @param value 토큰의 문자열 값
     */
    public XTokenNumber(String value) {
        if(value == null || value.isEmpty() || (isDouble(value) && !isInteger(value)) || Pattern.matches("^[a-zA-Z]*$", value)) throw new IllegalArgumentException();
        this.value = value;
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
     * @return 우선순위 (숫자 토큰은 0으로 설정)
     */
    @Override
    public int getPrecedence() {
        if(isInteger(value)) {
            return 0;
        }
        else if(isArray(value)) {
            return 0;
        }
        throw new IllegalArgumentException();
    }

    private boolean isArray(String value){
        if('[' != value.charAt(0) || ']' != value.charAt(value.length() - 1)) return false;
        StringBuilder s = new StringBuilder();
        for(int i=0; i<value.length(); i++){
            s.append(value.charAt(i));
        }
        this.value = s.toString();
        return true;
    }

    private boolean isInteger(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException ignored){
            return false;
        }
    }

    private boolean isDouble(String value){ // 실수는 처리 X
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException ignored){
            return false;
        }
    }
}
