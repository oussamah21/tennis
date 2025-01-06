package com.kata.domain;

import com.kata.exception.TennisException;
import com.kata.model.Player;
import com.kata.util.ScoreDisplayer;
import java.util.Optional;

public class TennisGame {
    // Players in the game
    private Player playerOne;
    private Player playerTwo;

    // Scores for each player
    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    // Track game winner and player with advantage
    private Optional<Player> gameWinner = Optional.empty();
    private Optional<Player> hasAdvantage = Optional.empty();

    private final ScoreDisplayer scoreDisplayer;

    // Initialize the score array for tennis notation (0 -> "0", 1 -> "15", 2 -> "30", 3 -> "40")
    private static final String[] SCORE = {"0", "15", "30", "40"};

    // Constructor to initialize players
    public TennisGame(Player playerOne, Player playerTwo, ScoreDisplayer scoreDisplayer) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.scoreDisplayer = scoreDisplayer;
    }

    // Main method to play the tennis game with the given sequence of points
    public void playTennisGame(String points) throws TennisException {
        // Iterate through each point in the string of points
        for (char point : points.toCharArray()) {
            // If the game already has a winner, break the loop
            if (gameWinner.isPresent()) {
                break;
            }

            // Validate the point: it must be from either playerOne or playerTwo
            if (point != playerOne.name() && point != playerTwo.name()) {
                throw new TennisException("Invalid point: " + point);  // Throw exception if point is invalid
            }

            // Increment the score for the respective player based on the point
            if (point == playerOne.name()) {
                incrementPlayerOneScore();
            } else {
                incrementPlayerTwoScore();
            }

            // Display the current score after each point
            getGameScore();
        }
        // After all points have been processed, if there is no winner, the game is incomplete
        if (gameWinner.isEmpty()) {
            throw new TennisException("The game sequence is incomplete. No winner was reached due to insufficient points.");
        }
    }

    // Increment the score of player one
    private void incrementPlayerOneScore() {
        playerOneScore++;
    }

    // Increment the score of player two
    private void incrementPlayerTwoScore() {
        playerTwoScore++;
    }

    // Generate and return the game score message based on the current score
    private void getGameScore() {
        if (hasGameWinner()) {
           scoreDisplayer.displayWinner(gameWinner.get());
        } else if (isDeuce()) {
           scoreDisplayer.displayDeuce();
        } else if (hasAdvantage()) {
           scoreDisplayer.displayAdvantage(hasAdvantage.get());
        } else {
            scoreDisplayer.displayGameScore(SCORE[playerOneScore],SCORE[playerTwoScore]);
        }
    }

    // Check if there is a winner in the game (4 or more points with a 2-point lead)
    boolean hasGameWinner() {
        // Player one wins if they have 4 or more points and lead by 2
        if (playerOneScore >= 4 && playerOneScore >= playerTwoScore + 2) {
            gameWinner = Optional.of(playerOne);
        }
        // Player two wins if they have 4 or more points and lead by 2
        if (playerTwoScore >= 4 && playerTwoScore >= playerOneScore + 2) {
            gameWinner = Optional.of(playerTwo);
        }
        return gameWinner.isPresent();
    }

    // Check if the game is in a Deuce state (both players have at least 3 points and have the same score)
    boolean isDeuce() {
        return playerOneScore >= 3 && playerTwoScore == playerOneScore;
    }

    // Check if the game has an Advantage state (one player has 4 or more points and leads by 1)
    boolean hasAdvantage() {
        // Check for advantage for playerOne
        if (playerOneScore >= 4 && playerOneScore == playerTwoScore + 1) {
            hasAdvantage = Optional.of(playerOne);
        }
        // Check for advantage for playerTwo
        else if (playerTwoScore >= 4 && playerTwoScore == playerOneScore + 1) {
            hasAdvantage = Optional.of(playerTwo);
        } else {
            hasAdvantage = Optional.empty();  // No advantage
        }

        // Return true if there is an advantage (hasAdvantage is not empty)
        return hasAdvantage.isPresent();
    }
}
