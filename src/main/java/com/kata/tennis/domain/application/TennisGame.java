package com.kata.tennis.domain.application;

import com.kata.tennis.domain.exception.TennisException;
import com.kata.tennis.domain.model.Player;
import com.kata.tennis.ports.out.ScoreDisplayerOutputPort;

import java.util.Optional;

public class TennisGame {
    private Player playerOne;
    private Player playerTwo;

    private Optional<Player> gameWinner = Optional.empty();
    private Optional<Player> gameAdvantage = Optional.empty();
    private final ScoreDisplayerOutputPort scoreDisplayerPort;

    private boolean isDeuce;


    public TennisGame(Player playerOne, Player playerTwo, ScoreDisplayerOutputPort scoreDisplayerPort) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.scoreDisplayerPort = scoreDisplayerPort;
    }

    public void playTennisGame(String points) throws TennisException {

        for (char point : points.toCharArray()) {

            if (gameWinner.isPresent()) {
                break;
            }
            if (point != playerOne.getName() && point != playerTwo.getName()) {
                throw new TennisException("Invalid point: " + point);
            }

            updateScore(point);
            checkForAdvantage();
            checkForWinner();
            checkForDeuce();
            getScore();
        }

        if (gameWinner.isEmpty()) {
            throw new TennisException("The game sequence is incomplete. No winner was reached due to insufficient points.");
        }
    }

     private void updateScore(char point) {
        if (point == playerOne.getName()) {
            playerOne.incrementScore();
        } else {
            playerTwo.incrementScore();
        }
    }

    private void checkForWinner() {
        if (playerOne.isWinner(playerTwo)) {
            gameWinner = Optional.of(playerOne);
        } else if (playerTwo.isWinner(playerOne)) {
            gameWinner = Optional.of(playerTwo);
        }

    }

    private void checkForAdvantage() {
        if (playerOne.hasAdvantage(playerTwo)) {
            gameAdvantage = Optional.of(playerOne);
        } else if (playerTwo.hasAdvantage(playerOne)) {
            gameAdvantage = Optional.of(playerTwo);
        }
    }

    private void checkForDeuce() {
        isDeuce = playerOne.isDeuce(playerTwo);
    }

    void getScore(){
        if (this.gameWinner.isPresent()) {
            scoreDisplayerPort.displayWinner(this.gameWinner.get());
        } else if (isDeuce()) {
            scoreDisplayerPort.displayDeuce();
        } else if (this.gameAdvantage.isPresent()) {
            scoreDisplayerPort.displayAdvantage(this.gameAdvantage.get());
        } else {
            scoreDisplayerPort.displayGameScore(this.playerOne.translateScore(),this.playerTwo.translateScore());
        }
    }


    private boolean isDeuce() {
        return isDeuce;
    }
}
