package org.example;

import java.util.NoSuchElementException;
import java.util.Stack;

public class CalculatorStack {
    private final Stack<Double> stack = new Stack<>();

    public void push(double value) {
        stack.push(value);
    }

    public double pop() throws Exception {
        if (stack.empty()) {
            throw new NoSuchElementException("Nothing to pop: stack is empty");
        }
        return stack.pop();
    }

    public double peek() {
        if (stack.empty()) {
            return Double.POSITIVE_INFINITY;
        }
        return stack.peek();
    }

    public boolean empty() {
        return stack.empty();
    }
}
