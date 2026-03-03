package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Обёртка над стеком для хранения чисел калькулятора.
 * Предоставляет базовые операции со стеком.
 */
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

    public List<Double> getStack() {
        return new ArrayList<>(stack);
    }
}