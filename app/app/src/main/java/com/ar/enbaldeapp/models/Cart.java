package com.ar.enbaldeapp.models;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {
    private final List<Selection> selections;
    private final long id;

    public Cart(long id, List<Selection> selections) {
        this.id = id;
        this.selections = selections;
    }

    public long getId() {
        return id;
    }

    public List<Selection> getSelections() {
        return this.selections;
    }
}
