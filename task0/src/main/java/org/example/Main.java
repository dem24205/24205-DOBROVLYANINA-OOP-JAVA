package org.example;

public class Main {
    public static void main(String[] args) {
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