package com.sephbain.radfx.model;

import javafx.util.StringConverter;

public class IntegerStringConverter extends StringConverter<Integer> {
    @Override
    public String toString(Integer integer) {
        return String.valueOf(integer);
    }

    @Override
    public Integer fromString(String s) {
        return Integer.valueOf(s);
    }
}
