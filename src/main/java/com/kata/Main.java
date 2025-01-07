package com.kata;

import com.kata.domain.TennisGame;
import com.kata.exception.TennisException;
import com.kata.model.Player;
import com.kata.util.ScoreDisplayer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        ScoreDisplayer scoreDisplayer = new ScoreDisplayer();
        scoreDisplayer.displayOpeningBanner();
        Player player1 = new Player('A');
        Player player2 = new Player('B');

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the points sequence (e.g., AABAB): ");
        String points = scanner.nextLine();

        TennisGame game = new TennisGame(player1, player2, new ScoreDisplayer());
        try {
            game.playTennisGame(points);
        } catch (TennisException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
