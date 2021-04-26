package com.sephbain.radfx.model;

import javafx.beans.property.SimpleObjectProperty;

public class SelectableListProperty<T> extends SimpleObjectProperty<SelectableList<T>> {
    private SelectableList<T> list = new SelectableList<>();

    @Override
    public SelectableList<T> get() {
        return list;
    }
}
 