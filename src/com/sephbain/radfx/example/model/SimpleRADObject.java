package com.sephbain.radfx.example.model;

import javafx.beans.property.*;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class SimpleRADObject {
    private final StringProperty data = new SimpleStringProperty();
    private final IntegerProperty num = new SimpleIntegerProperty();
    private final BooleanProperty bool = new SimpleBooleanProperty();
    private final DoubleProperty floatnum = new SimpleDoubleProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    public SimpleRADObject() {
        //this();
        randomize();
    }

    public void randomize() {
        System.out.println("randomize");
        this.data.set(randomString());
        this.num.set(new Random().nextInt(100));
        this.bool.setValue(new Random().nextBoolean());
        this.date.set(LocalDate.now());
        this.floatnum.set(new Random().nextInt(100) / 100.0);
    }

    public String getData() {
        return data.get();
    }

    public StringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public void setNum(int num) {
        this.num.set(num);
    }

    public boolean isBool() {
        return bool.get();
    }

    public BooleanProperty boolProperty() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool.set(bool);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public double getFloatnum() {
        return floatnum.get();
    }

    public DoubleProperty floatnumProperty() {
        return floatnum;
    }

    public void setFloatnum(double floatnum) {
        this.floatnum.set(floatnum);
    }

    @Override
    public String toString() {
        return data.get();
    }

    public String randomString() {
        String retVal = "";
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            char c = (char) (random.nextInt(32) + 64);
            retVal += new String(new char[]{c});
        }
        return retVal;
    }
}
