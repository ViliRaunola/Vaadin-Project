package entity;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int totalScore = 0;
    private int currentTurn = 0;
    private List<Turn> playerTurns = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }


    public int getTotalScore() {
        return totalScore;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void newUserTurn(int turnScore) {
        ++currentTurn;
        playerTurns.add(new Turn(turnScore, currentTurn));
        calculateTotalScore();
    }

    private void calculateTotalScore() {
        this.totalScore = 0;
        for(Turn turn : playerTurns) {
            this.totalScore += turn.getScore();
        }
    }

    public List<Turn> getUserTurns() {
        return this.playerTurns;
    }

    public String getName() {
        return name;
    }
}
