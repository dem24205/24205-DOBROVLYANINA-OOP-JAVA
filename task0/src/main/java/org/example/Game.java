package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {
    private final String answer;
    private final int attemptsNum;
    private boolean isGameWon;
    private final InputHandler inputHandler;
    private Guess guess;
    private static final Logger log = LoggerFactory.getLogger(Game.class);

    public Game(GameConfig config) {
        Generator gen = new Generator(config.getCodeLength());
        this.answer = gen.generate();
        this.attemptsNum = config.getAttemptsNum();
        this.isGameWon = false;
        this.inputHandler = new InputHandler(answer.length());
        this.guess = new Guess(0, 0);
        log.info("New game is started");
        log.debug("Generated code: {}", answer);
        log.debug("Code length: {}", answer.length());
        log.debug("Attempts: {}", attemptsNum);
    }

    /**
     * Запускает основной игровой цикл
     * Игрок вводит числа, получает подсказки, пока не угадает код или не кончатся попытки
     */
    public void run() {
        log.debug("Session start");
        printGreeting();
        for (int i = 0; i < attemptsNum; ++i) {
            int attempt = i + 1;
            printAttempt(attempt);
            String inputCode = inputHandler.readPlayerInput();
            if (answer.equals(inputCode)) {
                isGameWon = true;
                log.debug("Victory");
                log.debug("Number of attempts was needed: {}", attempt);
                printWinMessage();
                return;
            }
            else {
                this.guess = GameEngine.calculateBullsAndCows(answer, inputCode);
                printHint();
            }
        }
        if (!isGameWon) {
            log.debug("Defeat");
            printLoseMessage();
        }
        log.debug("Session end");
    }

    private void printGreeting() {
        log.info("Welcome to Cows and Bulls game!");
        log.info("Try to guess {}-digit code.", answer.length());
        log.info("You have {} attempts.", attemptsNum);
        log.info("Good luck!");
    }

    private void printHint() {
        log.info("Number of bulls: {}\nNumber of cows: {}\n", this.guess.getBulls(), this.guess.getCows());
    }

    private void printWinMessage() {
        log.info("Congratulations!");
        log.info("You guessed the code: {}", answer);
    }

    private void printLoseMessage() {
        log.info("Game over!");
        log.info("You've used all attempts!");
        log.info("The secret code was: {}", answer);
    }

    private void printAttempt(int attempt) {
        log.info("Attempt: {}", attempt);
    }
}