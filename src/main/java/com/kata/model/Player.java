package com.kata.model;

public class Player{

    private Character name;

    private int score = 0;


    public void setName(Character name) {
        this.name = name;
    }

    public Character getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(Character name) {
        this.name = name;
    }

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

}
