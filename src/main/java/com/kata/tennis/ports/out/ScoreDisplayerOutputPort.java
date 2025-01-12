package com.kata.tennis.ports.out;

import com.kata.tennis.domain.model.Player;

public interface ScoreDisplayerOutputPort {
     void displayGameScore(String playerOneScore, String playerTwoScore);
     void displayDeuce();
     void displayAdvantage(Player player);
     void displayWinner(Player player);

}
