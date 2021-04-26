package com.sephbain.radfx.controls;

import com.sephbain.radfx.interfaces.RADControl;
import javafx.beans.property.*;
import javafx.scene.control.Slider;

public class RADSlider<T> extends Slider implements RADControl<T, Double> {
    private final StringProperty propertyName = new SimpleStringProperty();
    private final ObjectProperty<T> selectedItem = new SimpleObjectProperty<>(null);
    private final Property<Property<Double>> selectedProperty = new SimpleObjectProperty<>(null);

    public RADSlider() {
        this(0, 100, 0);
    }

    public RADSlider(double min, double max, double value) {
        super(min, max, value);
        selectedItemProperty().addListener(SelectedItemHelper.getDoubleListener(this, valueProperty()));
    }

    @Override
    public Property<Property<Double>> dataProperty() {return selectedProperty;}

    @Override
    public Property<Double> getDataProperty() {return selectedProperty.getValue();}

    @Override
    public void setDataProperty(Property<Double> value) {selectedProperty.setValue(value);}

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
