package com.kata.tennis;

import com.kata.tennis.adapters.in.TennisGameScannerInput;
import com.kata.tennis.adapters.out.ConsoleScoreDisplayerAdapter;
import com.kata.tennis.domain.application.TennisGame;
import com.kata.tennis.domain.exception.TennisException;
import com.kata.tennis.domain.model.Player;
import com.kata.tennis.ports.in.TennisGameInputPort;
import com.kata.tennis.ports.out.ScoreDisplayerOutputPort;

public class Main {

    public static void main(String[] args) {

        String banner = """
                  _____              _      _  __     _       \s
                 |_   _|__ _ _  _ _ (_)___ | |/ /__ _| |_ __ _\s
                   | |/ -_) ' \\| ' \\| (_-< | ' </ _` |  _/ _` |
                   |_|\\___|_||_|_||_|_/__/ |_|\\_\\__,_|\\__\\__,_|
                                 
                                 
                """;

        System.out.println(banner);

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
