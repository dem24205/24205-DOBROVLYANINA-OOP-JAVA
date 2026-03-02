package org.example;

/**
 * Интерфейс всех команд калькулятора.
 * Каждая конкретная команда должна реализовывать этот интерфейс
 * и быть помечена аннотацией {@link CommandInfo} с указанием имени команды.
 */
public interface Command {
    void execute(Context context, String[] args) throws Exception;
}
