package com.kata.domain;

import com.kata.exception.TennisException;
import com.kata.model.Player;
import com.kata.ports.out.ScoreDisplayerOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TennisGameTest {

    private TennisGame tennisGame;
    @Mock
    private ScoreDisplayerOutputPort scoreDisplayerPort;
    private Player playerOne;
    private Player playerTwo;


    @BeforeEach
    void setUp() {

        playerOne = new Player('A');
        playerTwo = new Player('B');

        tennisGame = new TennisGame(playerOne, playerTwo, scoreDisplayerPort);
       // Mockito.doNothing().when(scoreDisplayerPort).displayGameScore(any(),any());
        //Mockito.doNothing().when(scoreDisplayerPort).displayDeuce();
       // Mockito.doNothing().when(scoreDisplayerPort).displayAdvantage(any());
       // Mockito.doNothing().when(scoreDisplayerPort).displayWinner(any());
    }

    @Test
    void testGameWinner() throws TennisException {
        tennisGame.playTennisGame("AAAA");

        verify(scoreDisplayerPort, times(1)).displayWinner(playerOne);
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
        verify(scoreDisplayerPort).displayWinner(playerOne);
    }

    @Test
    void testDeuceDisplayed() throws TennisException {
        tennisGame.playTennisGame("ABABABAA");
        verify(scoreDisplayerPort).displayDeuce();
    }

    @Test
    void testAdvantageDisplayedPlayerOne() throws TennisException {
        tennisGame.playTennisGame("ABABABAA");
        verify(scoreDisplayerPort).displayAdvantage(playerOne);
    }

    @Test
    void testAdvantageDisplayedPlayerTwo() throws TennisException {
        tennisGame.playTennisGame("BABABABB");
        verify(scoreDisplayerPort).displayAdvantage(playerTwo);
    }

    @Test
    void testScoreDisplayedDuringGame() throws TennisException {
        tennisGame.playTennisGame("ABABBB");
        InOrder inOrder = inOrder(scoreDisplayerPort);

        inOrder.verify(scoreDisplayerPort).displayGameScore("15", "0");
        inOrder.verify(scoreDisplayerPort).displayGameScore("15", "15");
        inOrder.verify(scoreDisplayerPort).displayGameScore("30", "15");
        verify(scoreDisplayerPort, times(0)).displayGameScore("0", "15");
        verify(scoreDisplayerPort, times(1)).displayGameScore("15", "0");
        verify(scoreDisplayerPort, times(1)).displayGameScore("15", "15");
        verify(scoreDisplayerPort, times(1)).displayGameScore("30", "15");

    }
}