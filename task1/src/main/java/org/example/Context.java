package org.example;

public class Context {
    private final CalculatorStack calculatorStack = new CalculatorStack();
    private final VariableTable variableTable = new VariableTable();

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
}
