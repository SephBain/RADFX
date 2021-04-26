package com.sephbain.radfx.controls;

import com.sephbain.radfx.interfaces.RADControl;
import javafx.beans.property.*;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class RADDatePicker<T, R> extends DatePicker implements RADControl<T, R> {
    private final StringProperty propertyName = new SimpleStringProperty();
    private final ObjectProperty<T> selectedItem = new SimpleObjectProperty<>(null);
    private final Property<Property<R>> selectedProperty = new SimpleObjectProperty<>(null);

    public RADDatePicker() {
        this(null);
    }

    public RADDatePicker(LocalDate date) {
        super(date);

        selectedItemProperty().addListener(SelectedItemHelper.getDateListener(this, valueProperty()));
    }

    @Override
    public Property<Property<R>> dataProperty() {return selectedProperty;}

    @Override
    public Property<R> getDataProperty() {return selectedProperty.getValue();}

    @Override
    public void setDataProperty(Property<R> value) {selectedProperty.setValue(value);}

    @Override
    public StringProperty propertyNameProperty() {return propertyName;}

    @Override
    public String getPropertyName() {return propertyName.get();}

    @Override
    public void setPropertyName(String propertyName) {this.propertyName.set(propertyName);}

    @Override
    public T getSelectedItem() {return selectedItem.get();}

    @Override
    public ObjectProperty<T> selectedItemProperty() {return selectedItem;}

    @Override
    public void setSelectedItem(T item) {selectedItem.set(item);}
}
