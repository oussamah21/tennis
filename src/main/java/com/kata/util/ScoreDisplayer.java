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
        System.out.println("Advantage: " + player.getName());
    }

    public void displayWinner(Player player) {
        System.out.println("Player " + player.getName() + " wins the game");
    }

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
