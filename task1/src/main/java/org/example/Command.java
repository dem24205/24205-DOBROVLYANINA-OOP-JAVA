package org.example;

public interface Command {
    void execute(Context context, String[] args) throws Exception;
}
