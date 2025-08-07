package com.nhnacademy.number;

public interface XNumber extends Comparable<XNumber> {
    XNumber add(XNumber other);

    XNumber subtract(XNumber other);

    XNumber multiply(XNumber other);

    XNumber divide(XNumber other);
}