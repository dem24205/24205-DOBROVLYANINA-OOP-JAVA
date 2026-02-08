package org.example;

//0 допустим на первом месте в коде
//не менее одной попытки
//создать репозиторий и склонировать его
//запушить код

public class Main {
    public static void main(String[] args) {
        int attempts = 10;
        int codeLength = 6;
        try {
            Game game = new Game(attempts, codeLength);
            game.run();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please restart the game with valid parameters.");
        }
    }
}