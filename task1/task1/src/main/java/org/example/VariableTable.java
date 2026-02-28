package org.example;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class VariableTable {
    private final HashMap<String, Double> table = new HashMap<>();

    public void put(String name, double value) {
        table.put(name, value);
    }

    public double get(String name) throws Exception {
        if (!table.containsKey(name)) {
            throw new NoSuchElementException("Variable not found: " + name);
        }
        return table.get(name);
    }
}
