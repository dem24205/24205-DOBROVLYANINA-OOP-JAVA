package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Контекст выполнения команд калькулятора.
 * Хранит текущее состояние: стек чисел, переменные и историю команд.
 */
public class Context {
    private final CalculatorStack calculatorStack = new CalculatorStack();
    private final VariableTable variableTable = new VariableTable();
    private final List<String> history = new ArrayList<>();

    public boolean isStackEmpty() {
        return calculatorStack.empty();
    }

    public void pushOnStack(double value) {
        calculatorStack.push(value);
    }

    public double popFromStack() throws Exception {
        return calculatorStack.pop();
    }

    public double peekStack() {
        return calculatorStack.peek();
    }

    public void setVariable(String name, double value) {
        variableTable.put(name, value);
    }

    public double getVariable(String name) throws Exception {
        return variableTable.get(name);
    }

    public void addToHistory(String command) {
        history.add(command);
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    public List<Double> getStack() {
        return calculatorStack.getStack();
    }

    public Map<String, Double> getVariables() {
        return variableTable.getTable();
    }
}