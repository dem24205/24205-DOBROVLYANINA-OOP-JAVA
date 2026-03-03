package org.example;

import java.lang.annotation.*;

/**
 * Аннотация для маркировки классов команд калькулятора.
 * Указывает, под каким именем команда будет доступна в калькуляторе.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
        String name();
}
