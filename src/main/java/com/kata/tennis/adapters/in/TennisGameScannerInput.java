package com.kata.tennis.adapters.in;

import com.kata.tennis.domain.application.TennisGame;
import com.kata.tennis.domain.exception.TennisException;
import com.kata.tennis.ports.in.TennisGameInputPort;

import java.util.Scanner;

public class TennisGameScannerInput implements TennisGameInputPort {

    private final TennisGame tennisGame;

    public TennisGameScannerInput(TennisGame tennisGame) {
        this.tennisGame = tennisGame;
    }

    @Override
    public void playGame() throws TennisException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the points sequence (e.g., AABAB): ");
        String points = scanner.nextLine();
        tennisGame.playTennisGame(points);
    }


}
