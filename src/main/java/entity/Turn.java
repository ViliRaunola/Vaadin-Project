package entity;

public class Turn {
    private int score;
    private int turnNumber;

    public Turn(int score, int turnNumber) {
        this.score = score;
        this.turnNumber = turnNumber;
    }

    public int getScore() {
        return score;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
