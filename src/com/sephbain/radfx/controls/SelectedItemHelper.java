package com.sephbain.radfx.controls;

import com.sephbain.radfx.interfaces.RADControl;
import com.sephbain.radfx.model.NumberStringConverter;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.converter.DateStringConverter;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Objects;

public class SelectedItemHelper {
    public static <T, R> ChangeListener<? super T> getStringListener(RADControl<T, R> control, StringProperty textProperty) {
        return getGenericPropertyListener(control, textProperty,
                (valueProperty, displayProperty) -> {
                    if (valueProperty instanceof StringProperty) {
                        displayProperty.bindBidirectional(valueProperty);
                        //Bindings.bindBidirectional(displayProperty, (StringProperty) valueProperty);
                    } else if (valueProperty instanceof IntegerProperty) {
                        ((StringProperty) displayProperty).bindBidirectional((IntegerProperty) valueProperty, new NumberStringConverter());
                        //Bindings.bindBidirectional(displayProperty, (IntegerProperty) valueProperty, new NumberStringConverter());
                    }
                },
                (valueProperty, displayProperty) -> {
                    displayProperty.unbindBidirectional(valueProperty);
                });
    }

    public static <T> ChangeListener<? super T> getBooleanListener(RADControl<T, Boolean> control, BooleanProperty booleanProperty) {
        return getGenericPropertyListener(control, booleanProperty,
                (valueProperty, displayProperty) -> {
                    if (valueProperty instanceof BooleanProperty) {
                        displayProperty.bindBidirectional(valueProperty);
                    } else {
                        throw new RuntimeException(control.getPropertyName() + " must be a BooleanProperty");
                    }
                },
                (valueProperty, displayProperty) -> {
                    displayProperty.unbindBidirectional(valueProperty);
                });
    }

    public static <T, R> ChangeListener<? super T> getDateListener(RADControl<T, R> control, ObjectProperty<LocalDate> dateProperty) {
        return getGenericPropertyListener(control, dateProperty,
                (valueProperty, displayProperty) -> {
                    if (valueProperty instanceof StringProperty) {
                        ((StringProperty)valueProperty).bindBidirectional(displayProperty, new DateStringConverter());
                    } else if (valueProperty instanceof ObjectProperty) {
                        displayProperty.bindBidirectional(valueProperty);
                    }
                },
                (valueProperty, displayProperty) -> {
                    displayProperty.unbindBidirectional(valueProperty);
                });
    }

    public static <T, R> ChangeListener<? super T> getDoubleListener(RADControl<T, R> control, DoubleProperty doubleProperty) {
        return getGenericPropertyListener(control, doubleProperty,
                (valueProperty, displayProperty) -> {
                    if (valueProperty instanceof DoubleProperty) {
                        displayProperty.bindBidirectional(valueProperty);
                    }
                },
                (valueProperty, displayProperty) -> {
                    displayProperty.unbindBidirectional(valueProperty);
                });
    }

    private static <T, R> ChangeListener<? super T> getGenericPropertyListener(RADControl<T, R> control,
                                                    Property displayProperty, Binder binder, Binder unbinder) {
        return ((observableValue, oldVal, newVal) -> {
            if (Objects.nonNull(oldVal) && Objects.nonNull(control.getDataProperty())) {
                unbinder.bind(control.getDataProperty(), displayProperty);
                control.setDataProperty(null);
            }
            if (Objects.nonNull(newVal)) {
                try {
                    if (Objects.isNull(control.getDataProperty())) {
                        Property<R> obj = getValueProperty(control, newVal);
                        control.setDataProperty(obj);
                    }
                    binder.bind(control.getDataProperty(), displayProperty);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    public static <T, R> ChangeListener<? super T> getActionListener(RADControl<T, R> control, ObjectProperty<EventHandler<ActionEvent>> actionProperty) {
        return ((observableValue, oldVal, newVal) -> {
            actionProperty.set(actionEvent -> {
                try {
                    newVal.getClass().getMethod(control.getPropertyName()).invoke(newVal);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }


    private static Method getProperty(String propertyName, Object obj) {
        try {
           return obj.getClass().getMethod(propertyName + "Property");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, R> Property<R> getValueProperty(RADControl<T, R> control, Object obj) {
        try {
            Object retVal = getProperty(control.getPropertyName(), obj).invoke(obj);
            if (retVal instanceof Property) {
                return (Property<R>) retVal;
            }
            throw  new RuntimeException(control.getPropertyName() + " must be a property");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private interface Binder {
        void bind(Property valueProperty, Property displayProperty);
    }
}
