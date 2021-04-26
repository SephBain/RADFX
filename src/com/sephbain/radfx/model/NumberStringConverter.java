package com.sephbain.radfx.model;

import javafx.util.StringConverter;

public class NumberStringConverter extends StringConverter<Number> {
    @Override
    public String toString(Number number) {
        return String.valueOf(number);
    }

    @Override
    public Number fromString(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return Double.valueOf(s);
        }
    }
}
