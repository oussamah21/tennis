package com.kata.ports.out;

import com.kata.model.Player;

public interface ScoreDisplayerOutputPort {
     void displayGameScore(String playerOneScore, String playerTwoScore);
     void displayDeuce();
     void displayAdvantage(Player player);
     void displayWinner(Player player);
     void displayOpeningBanner();
}
