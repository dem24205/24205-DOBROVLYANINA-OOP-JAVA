package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Хранилище определённых пользователем переменных.
 * Обеспечивает доступ к переменным по имени.
 */
public class VariableTable {
    private final HashMap<String, Double> table = new HashMap<>();

    /** Сохраняет переменную с указанным именем и значением. */
    public void put(String name, double value) {
        table.put(name, value);
    }

    /** Возвращает значение переменной по имени. */
    public double get(String name) throws Exception {
        if (!table.containsKey(name)) {
            throw new NoSuchElementException("Variable not found: " + name);
        }
        return table.get(name);
    }

    /** Возвращает копию таблицы переменных. */
    public Map<String, Double> getTable() {
        return new HashMap<>(table);
    }
}
