package org.example.entity.model;

public enum SortAction {
    TITLE(0),
    COST(1),
    DATE(2);

    private final int value;

    SortAction(int value) {
        this.value = value;
    }
}
