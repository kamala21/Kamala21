package com.example.pele_.kamala21;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class gameStateTests { //Pelenalako Kamala
    @Test
    public void gameStateNumPlayersBase() {
        RmPmGameState instance = new RmPmGameState();
        assertEquals(4, instance.getPlayers().size());
    }
    @Test
    public void gameStateNumPlayersUpperBound() {
        RmPmGameState instance = new RmPmGameState(2);
        assertEquals(2, instance.getPlayers().size());
    }
    @Test
    public void gameStateNumPlayersLowBound() {
        RmPmGameState instance = new RmPmGameState(6);
        assertEquals(6, instance.getPlayers().size());
    }
    @Test
    public void gameStateNumPlayersUnder() {
        RmPmGameState instance = new RmPmGameState(1);
        assertEquals(2, instance.getPlayers().size());
    }
    @Test
    public void gameStateNumPlayersOver() {
        RmPmGameState instance = new RmPmGameState(8);
        assertEquals(6, instance.getPlayers().size());
    }
    @Test
    public void gameStateCopyBase() {
        RmPmGameState instance = new RmPmGameState();
        RmPmGameState instance2 = new RmPmGameState(instance);
        assertEquals(4, instance2.getPlayers().size());
    }
    @Test
    public void gameStateCopyUpperBound() {
        RmPmGameState instance = new RmPmGameState(2);
        RmPmGameState instance2 = new RmPmGameState(instance);
        assertEquals(2, instance2.getPlayers().size());
    }
    @Test
    public void gameStateCopyLowBound() {
        RmPmGameState instance = new RmPmGameState(6);
        RmPmGameState instance2 = new RmPmGameState(instance);
        assertEquals(6, instance2.getPlayers().size());
    }
    @Test
    public void gameStateCopyUnder() {
        RmPmGameState instance = new RmPmGameState(1);
        RmPmGameState instance2 = new RmPmGameState(instance);
        assertEquals(2, instance2.getPlayers().size());
    }
    @Test
    public void gameStateCopyOver() {
        RmPmGameState instance = new RmPmGameState(8);
        RmPmGameState instance2 = new RmPmGameState(instance);
        assertEquals(6, instance2.getPlayers().size());
    }
    @Test
    public void gameStateDone() {
        RmPmGameState instance = new RmPmGameState(3);
        assertEquals(false, instance.gameDone());
    }
    @Test
    public void gameStatePlayersWithCards() {
        RmPmGameState instance = new RmPmGameState(3);
        assertEquals(3, instance.playersWithCards());
    }
    @Test
    public void gameStatePlayersWithCardsClear() {
        RmPmGameState instance = new RmPmGameState(3);
        instance.getPlayers().get(0).getHand().clear();
        assertEquals(2, instance.playersWithCards());
    }
    @Test
    public void gameStateCurrentPlayer() {
        RmPmGameState instance = new RmPmGameState(3);
        assertEquals(0, instance.getCurrentPlayer());
    }
    @Test
    public void gameStateCurrentPlayerNext() {
        RmPmGameState instance = new RmPmGameState(3);
        instance.nextPlayer();
        assertEquals(1, instance.getCurrentPlayer());
    }
    @Test
    public void gameStateLastPlayed() {
        RmPmGameState instance = new RmPmGameState(3);
        assertEquals(-1, instance.getLastPlayed());
    }
}