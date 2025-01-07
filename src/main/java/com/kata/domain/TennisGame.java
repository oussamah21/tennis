package com.kata.domain;

import com.kata.exception.TennisException;
import com.kata.model.Player;
import com.kata.util.ScoreDisplayer;
import java.util.Optional;

public class TennisGame {
    private Player playerOne;
    private Player playerTwo;

    private Optional<Player> gameWinner = Optional.empty();
    private Optional<Player> gameAdvantage = Optional.empty();

    private final ScoreDisplayer scoreDisplayer;

    private static final String[] SCORE = {"0", "15", "30", "40"};

    public TennisGame(Player playerOne, Player playerTwo, ScoreDisplayer scoreDisplayer) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.scoreDisplayer = scoreDisplayer;
    }

    public void playTennisGame(String points) throws TennisException {

        for (char point : points.toCharArray()) {
            if (gameWinner.isPresent()) {
                break;
            }

            if (point != playerOne.getName() && point != playerTwo.getName()) {
                throw new TennisException("Invalid point: " + point);  // Throw exception if point is invalid
            }

            if (point == playerOne.getName()) {
                playerOne.incrementScore();
            } else {
                playerTwo.incrementScore();
            }
            hasAdvantage();
            hasGameWinner();
            getGameScore();
        }
        if (gameWinner.isEmpty()) {
            throw new TennisException("The game sequence is incomplete. No winner was reached due to insufficient points.");
        }
    }

    // Generate and return the game score message based on the current score
    private void getGameScore() {

        if (gameWinner.isPresent()) {
           scoreDisplayer.displayWinner(gameWinner.get());
        } else if (playerOne.isDeuce(playerTwo)) {
           scoreDisplayer.displayDeuce();
        } else if (gameAdvantage.isPresent()) {
           scoreDisplayer.displayAdvantage(gameAdvantage.get());
        } else {
            scoreDisplayer.displayGameScore(SCORE[playerOne.getScore()],SCORE[playerTwo.getScore()]);
        }
    }

    void hasGameWinner() {
        if(playerOne.isWinner(playerTwo)){
            gameWinner = Optional.of(playerOne);
        } else if(playerTwo.isWinner(playerOne)){
            gameWinner = Optional.of(playerTwo);
        }

    }

    void hasAdvantage() {
        if(playerOne.hasAdvantage(playerTwo)){
            gameAdvantage = Optional.of(playerOne);
        } else if(playerTwo.hasAdvantage(playerOne)){
            gameAdvantage = Optional.of(playerTwo);
        }
    }

    public Optional<Player> getGameWinner() {
        return gameWinner;
    }



}
