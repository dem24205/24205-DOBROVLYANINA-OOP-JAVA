package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
