package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            GameConfig config = GameConfig.fromArgs(args);
            Game session = new Game(config);
            session.run();
        } catch (IllegalArgumentException e) {
            log.error("Error: {}", e.getMessage());
            log.info("Please restart the game with valid parameters.");
        }
    }
}