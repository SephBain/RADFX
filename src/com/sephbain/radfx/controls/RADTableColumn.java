package com.sephbain.radfx.controls;

import com.sephbain.radfx.interfaces.RADControl;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class RADTableColumn<T, R> extends TableColumn implements RADControl<T, R> {
    private final StringProperty propertyName = new SimpleStringProperty();
    private final ObjectProperty<T> selectedItem = new SimpleObjectProperty<>(null);
    private final Property<Property<R>> selectedProperty = new SimpleObjectProperty<>(null);

    public RADTableColumn() {
        this("");
    }

    public RADTableColumn(String text) {
        super(text);
        propertyName.addListener((observableValue, oldVal, newVal) -> {
            setCellValueFactory((Callback<CellDataFeatures<T, R>, ObservableValue>) cellData ->
               SelectedItemHelper.getValueProperty(this, cellData.getValue()));
        });
    }

    @Override
    public Property<Property<R>> dataProperty() {
        return selectedProperty;
    }

    @Override
    public StringProperty propertyNameProperty() {return propertyName;}

    @Override
    public String getPropertyName() {
        return propertyName.get();
    }

    @Override
    public void setPropertyName(String propertyName) {
        this.propertyName.set(propertyName);
    }

    @Override
    public T getSelectedItem() {
        return selectedItem.get();
    }

    @Override
    public ObjectProperty<T> selectedItemProperty() {return selectedItem;}

    @Override
    public void setSelectedItem(T item) {
        selectedItem.set(item);
    }

    @Override
    public Property<R> getDataProperty() {
        return dataProperty().getValue();
    }

    @Override
    public void setDataProperty(Property<R> value) {
        dataProperty().setValue(value);
    }
}
