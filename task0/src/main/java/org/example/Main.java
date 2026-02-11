package org.example;

import java.io.IOException;
import java.util.logging.*;
//+javadoc
//+game log
//readme file

public class Main {
    private static void setLogParams() {
        try {
            FileHandler fileHandler = new FileHandler("game.log", false);
            fileHandler.setFormatter(new SimpleFormatter());
            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fileHandler);
            rootLogger.setUseParentHandlers(false);
        }
        catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        //setLogParams();

        try {
            GameConfig config = GameConfig.fromArgs(args);
            Game session = new Game(config);
            session.run();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please restart the game with valid parameters.");
        }
    }
}