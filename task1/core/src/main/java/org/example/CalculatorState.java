package org.example;

import java.util.List;
import java.util.Map;

/**
 * DTO для сохранения и загрузки состояния калькулятора.
 * Содержит стек чисел, переменные и историю команд.
 */
public class CalculatorState {
    private List<Double> stack;
    private Map<String, Double> variables;
    private List<String> history;

    public List<Double> getStack() {
        return stack;
    }

    public Map<String, Double> getVariables() {
        return variables;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setStack(List<Double> stack) {
        this.stack = stack;
    }

    public void setVariables(Map<String, Double> table) {
        this.variables = table;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
}
