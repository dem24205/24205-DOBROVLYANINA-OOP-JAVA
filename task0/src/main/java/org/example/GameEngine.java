package org.example;

public class GameEngine {
    public static Guess calculateBullsAndCows(String answer, String guess) {
        int bulls = 0;
        int cows = 0;
        boolean[] guessSeen = new boolean[answer.length()];
        boolean[] answerSeen = new boolean[answer.length()];
        for (int i = 0; i < answer.length(); ++i) {
            if (guess.charAt(i) == answer.charAt(i)) {
                bulls++;
                guessSeen[i] = true;
                answerSeen[i] = true;
            }
        }
        for (int i = 0; i < answer.length(); ++i) {
            if (guessSeen[i]) continue;
            char curGuessChar = guess.charAt(i);
            for (int j = 0; j < answer.length(); ++j) {
                if (answerSeen[j]) continue;
                if (curGuessChar == answer.charAt(j)) {
                    cows++;
                    answerSeen[j] = true;
                    break;
                }
            }
        }
        return new Guess(bulls, cows);
    }
}
