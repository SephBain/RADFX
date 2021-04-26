package com.sephbain.radfx.interfaces;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;

public interface RADControl<T, R> {
    StringProperty propertyNameProperty();
    String  getPropertyName();
    void setPropertyName(String propertyName);

    ObjectProperty<T> selectedItemProperty();
    T getSelectedItem();
    void setSelectedItem(T item);

    Property<Property<R>> dataProperty();
    Property<R> getDataProperty();
    void setDataProperty(Property<R> value);
}
