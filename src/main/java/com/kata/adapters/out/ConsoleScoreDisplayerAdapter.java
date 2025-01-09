package com.kata.adapters.out;

import com.kata.model.Player;
import com.kata.ports.out.ScoreDisplayerOutputPort;

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

    @Override
    public void displayOpeningBanner() {

        String banner = """
                  _____              _      _  __     _       \s
                 |_   _|__ _ _  _ _ (_)___ | |/ /__ _| |_ __ _\s
                   | |/ -_) ' \\| ' \\| (_-< | ' </ _` |  _/ _` |
                   |_|\\___|_||_|_||_|_/__/ |_|\\_\\__,_|\\__\\__,_|
                                 
                                 
                """;

        System.out.println(banner);


    }
}
