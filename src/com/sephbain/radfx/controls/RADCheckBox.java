package com.sephbain.radfx.controls;

import com.sephbain.radfx.interfaces.RADControl;
import javafx.beans.property.*;
import javafx.scene.control.CheckBox;

public class RADCheckBox<T> extends CheckBox implements RADControl<T, Boolean> {
    private final StringProperty propertyName = new SimpleStringProperty();
    private final ObjectProperty<T> selectedItem = new SimpleObjectProperty<>(null);
    private final Property<Property<Boolean>> selectedProperty = new SimpleObjectProperty<>(null);

    public RADCheckBox() {
        this("");
    }

    public RADCheckBox(String label) {
        super(label);

        selectedItemProperty().addListener(SelectedItemHelper.getBooleanListener(this, selectedProperty()));
    }

    @Override
    public Property<Property<Boolean>> dataProperty() {return selectedProperty;}

    @Override
    public Property<Boolean> getDataProperty() {return selectedProperty.getValue();}

    @Override
    public void setDataProperty(Property<Boolean> value) {selectedProperty.setValue(value);}

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
