package com.sephbain.radfx.model;

import javafx.collections.ModifiableObservableListBase;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class SelectableList<T> extends ModifiableObservableListBase<T> {
    public class SingleSelectionModel<T> extends javafx.scene.control.SingleSelectionModel<T> {
        private final SelectableList<T> list;
        private SingleSelectionModel(SelectableList<T> list){
            this.list = list;
        }
        @Override
        protected T getModelItem(int i) {
            return list.get(i);
        }

        @Override
        protected int getItemCount() {
            return list.size();
        }
    }

    private final List<T> list;
    @FXML
    private final SingleSelectionModel<T> selectionModel;

    public SelectableList() {
        this(new ArrayList<>());
    }

    public SelectableList(List<T> list) {
        super();
        this.list = list;
        this.selectionModel = new SingleSelectionModel<>(this);
    }

    public SingleSelectionModel<T> getSelectionModel() {
        return selectionModel;
    }

    @Override
    public T get(int i) {
        return list.get(i);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected void doAdd(int i, T t) {
        list.add(i, t);
    }

    @Override
    protected T doSet(int i, T t) {
        return list.set(i, t);
    }

    @Override
    protected T doRemove(int i) {
        return list.remove(i);
    }
}
