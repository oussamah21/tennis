package com.kata.domain;

import com.kata.exception.TennisException;
import com.kata.model.Player;
import com.kata.util.ScoreDisplayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameTest {

    private TennisGame tennisGame;
    private ScoreDisplayer scoreDisplayer;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    void setUp() {
        scoreDisplayer = Mockito.mock(ScoreDisplayer.class);

        playerOne = new Player('A');
        playerTwo = new Player('B');

        tennisGame = new TennisGame(playerOne, playerTwo, scoreDisplayer);
    }

    @Test
    void testGameWinner() throws TennisException {
        tennisGame.playTennisGame("AAAA");
        assertTrue(tennisGame.getGameWinner().isPresent(), "The game should have a winner.");
    }


    @Test
    void testInvalidPointThrowsException() {
        assertThrows(TennisException.class, () -> tennisGame.playTennisGame("XYZ"), "Invalid point should throw an exception.");
    }

    @Test
    void testIncompleteGameThrowsException() {
        assertThrows(TennisException.class, () -> tennisGame.playTennisGame("AAA"), "Incomplete game should throw an exception due to insufficient points.");
    }

    @Test
    void testWinnerScoreDisplayed() throws TennisException {
        tennisGame.playTennisGame("AAAA");
        Mockito.verify(scoreDisplayer).displayWinner(playerOne);
    }

    @Test
    void testDeuceDisplayed() throws TennisException {
        tennisGame.playTennisGame("ABABABAA");
        Mockito.verify(scoreDisplayer).displayDeuce();
    }

    @Test
    void testAdvantageDisplayedPlayerOne() throws TennisException {
        tennisGame.playTennisGame("ABABABAA");
        Mockito.verify(scoreDisplayer).displayAdvantage(playerOne);
    }

    @Test
    void testAdvantageDisplayedPlayerTwo() throws TennisException {
        tennisGame.playTennisGame("BABABABB");
        Mockito.verify(scoreDisplayer).displayAdvantage(playerTwo);
    }

    @Test
    void testScoreDisplayedDuringGame() throws TennisException {
        tennisGame.playTennisGame("ABABBB");
        Mockito.verify(scoreDisplayer, Mockito.times(0)).displayGameScore("0", "15");
        Mockito.verify(scoreDisplayer, Mockito.times(1)).displayGameScore("15", "0");
        Mockito.verify(scoreDisplayer, Mockito.times(1)).displayGameScore("15", "15");
        Mockito.verify(scoreDisplayer, Mockito.times(1)).displayGameScore("30", "15");

    }
}

