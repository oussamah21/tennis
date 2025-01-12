package com.kata.tennis.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Player{

    private final Character name;

    private int score = 0;

    private static final String[] SCOREDISPLAY = {"0", "15", "30", "40"};


    public void incrementScore(){
        this.score ++ ;
    }

    public boolean isWinner (Player otherPlayer){
       return this.score >= 4 && this.score  >= otherPlayer.getScore() + 2;
    }

    public boolean isDeuce (Player otherPlayer){
        return this.score >= 3 && this.score == otherPlayer.getScore();
    }

   public boolean hasAdvantage(Player otherPlayer) {
       return this.score >= 4 && this.score == otherPlayer.getScore() + 1;
   }

   public String translateScore () {
        return SCOREDISPLAY[this.getScore()];
   }

}
