package com.kata;

import com.kata.adapters.in.TennisGameScannerInput;
import com.kata.adapters.out.ConsoleScoreDisplayerAdapter;
import com.kata.domain.TennisGame;
import com.kata.exception.TennisException;
import com.kata.model.Player;
import com.kata.ports.in.TennisGameInputPort;
import com.kata.ports.out.ScoreDisplayerOutputPort;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player('A');
        Player player2 = new Player('B');


        ScoreDisplayerOutputPort scoreDisplayerPort = new ConsoleScoreDisplayerAdapter();
        TennisGame game = new TennisGame(player1, player2, scoreDisplayerPort);
        TennisGameInputPort tennisGameInputPort = new TennisGameScannerInput(game);
        try {
            tennisGameInputPort.playGame();
        } catch (TennisException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
