package com.kata.tennis.adapters.out;

import com.kata.tennis.domain.model.Player;
import com.kata.tennis.ports.out.ScoreDisplayerOutputPort;

public class ConsoleScoreDisplayerAdapter implements ScoreDisplayerOutputPort {

    @Override
    public void displayGameScore(String playerOneScore, String playerTwoScore) {
        System.out.println("Player A: " + playerOneScore + " / Player B: " + playerTwoScore);
    }

    @Override
    public void displayDeuce() {
        System.out.println("Deuce");
    }

    @Override
    public void displayAdvantage(Player player) {
        System.out.println("Advantage: " + player.getName());
    }

    @Override
    public void displayWinner(Player player) {
        System.out.println("Player " + player.getName() + " wins the game");
    }

}
