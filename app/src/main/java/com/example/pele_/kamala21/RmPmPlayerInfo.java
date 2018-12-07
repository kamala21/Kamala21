package com.example.pele_.kamala21;

import java.util.ArrayList;

public class RmPmPlayerInfo {
    private ArrayList<RmPmCard> hand;
    private int wins;
    private int standing;

    public RmPmPlayerInfo(){
        //Player info constructor
        hand = new ArrayList<RmPmCard>(13);
        wins = 0;
        standing = -1;
    }
    //Info stored for each player
    public ArrayList<RmPmCard> getHand() {
        return hand;
    }
    public int getWins(){ return wins; }
    public int getStanding(){ return standing; }

    //Actions for player
    public void setHand(ArrayList<RmPmCard> in){ hand = in; }
    public void addCard(RmPmCard in){
        hand.add(in);
    }
    public boolean removeCard(RmPmCard in){
        if(hand.contains(in)){
            hand.remove(in);
            return true;
        }
        return false;
    }

    public void addWin(){
        wins++;
    }
    public void setWin(int amountWins) {wins = amountWins; }

    public void setStanding(int standingResult){
        standing = standingResult;
    }
}
