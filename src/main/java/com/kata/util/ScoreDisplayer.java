package com.kata.util;

import com.kata.model.Player;

public class ScoreDisplayer {

    public void displayGameScore(String playerOneScore, String playerTwoScore) {
        System.out.println("Player A: " + playerOneScore + " / Player B: " + playerTwoScore );
    }

    public void displayDeuce() {
        System.out.println("Deuce");
    }

    public void displayAdvantage(Player player) {
        System.out.println("Advantage: " + player.name());
    }

    public void displayWinner(Player player) {
        System.out.println("Player " + player.name() + " wins the game");
    }
}
