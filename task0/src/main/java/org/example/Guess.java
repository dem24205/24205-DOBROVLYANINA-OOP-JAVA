package org.example;

public class Guess {
    private int bulls;
    private int cows;

    public Guess(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    public void setBulls(int bulls) { this.bulls = bulls; }

    public void setCows(int cows) {
        this.cows = cows;
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }
}
