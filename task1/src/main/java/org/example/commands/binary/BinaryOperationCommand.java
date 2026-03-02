package org.example.commands.binary;

import org.example.Command;
import org.example.Context;
import java.util.NoSuchElementException;

/**
 * Абстрактный базовый класс для бинарных операций.
 * Извлекает два верхних элемента из стека и сохраняет их в поля elem1 и elem2.
 * При недостатке элементов выбрасывает исключение (с восстановлением стека).
 */
public class BinaryOperationCommand implements Command {
    double elem1;
    double elem2;
    @Override
    public void execute(Context context, String[] args) throws Exception {
        try {
            elem2 = context.popFromStack();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Not enough elements to perform the operation");
        }
        try {
            elem1 = context.popFromStack();
        } catch (NoSuchElementException e) {
            context.pushOnStack(elem2);
            throw new RuntimeException("Not enough elements to perform the operation");
        }
    }
}
