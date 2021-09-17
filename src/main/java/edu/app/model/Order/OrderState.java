package edu.app.model.Order;


import java.util.ArrayList;
import java.util.List;

public enum OrderState {
    ACTIVE, CANCELED, DELIVERED, PREPARATORY, DELETED;

    public static List<OrderState> statesForAdmin() {
        List<OrderState> states = new ArrayList<>();
        states.add(ACTIVE);
        states.add(CANCELED);
        states.add(DELIVERED);
        states.add(DELETED);
        return states;
    }
}