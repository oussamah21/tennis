package com.kata.domain;

import com.kata.exception.TennisException;
import com.kata.model.Player;
import com.kata.ports.out.ScoreDisplayerOutputPort;

import java.util.Optional;

public class TennisGame {
    private Player playerOne;
    private Player playerTwo;

    private Optional<Player> gameWinner = Optional.empty();
    private Optional<Player> gameAdvantage = Optional.empty();

    private static final String[] SCORE = {"0", "15", "30", "40"};

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

     void updateScore(char point) {
        if (point == playerOne.getName()) {
            playerOne.incrementScore();
        } else {
            playerTwo.incrementScore();
        }
    }

    void checkForWinner() {
        if (playerOne.isWinner(playerTwo)) {
            gameWinner = Optional.of(playerOne);
        } else if (playerTwo.isWinner(playerOne)) {
            gameWinner = Optional.of(playerTwo);
        }

    }

    void checkForAdvantage() {
        if (playerOne.hasAdvantage(playerTwo)) {
            gameAdvantage = Optional.of(playerOne);
        } else if (playerTwo.hasAdvantage(playerOne)) {
            gameAdvantage = Optional.of(playerTwo);
        }
    }

    void checkForDeuce() {
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
            scoreDisplayerPort.displayGameScore(SCORE[this.playerOne.getScore()],SCORE[this.playerTwo.getScore()]);
        }
    }

    public Optional<Player> getGameWinner() {
        return gameWinner;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Optional<Player> getGameAdvantage() {
        return gameAdvantage;
    }

    public boolean isDeuce() {
        return isDeuce;
    }
}
