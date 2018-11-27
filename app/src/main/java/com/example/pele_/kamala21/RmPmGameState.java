package com.example.pele_.kamala21;

import java.util.*;

public class RmPmGameState {
    //arraylist of cards already played
    private ArrayList<RmPmCard> playedCards;
    // arraylist of players
    private ArrayList<RmPmPlayerInfo> players;
    //the cards of the current set
    private ArrayList<RmPmCard> currentSet;
    //a set used by the player to check if their move is valid
    private ArrayList<RmPmCard> playerSet;
    //an int that tells whose move it is
    private int currentPlayer;
    //an int to remember who the last person to play a card was
    private int lastPlayed;

    public RmPmGameState() {
        // initialize the state to be a brand new game

        RmPmDeck deck = new RmPmDeck(4);
        Collections.shuffle(deck.getDeck());
        //make an empty list of cards for starting playedCards
        playedCards = new ArrayList<RmPmCard>();
        //make an empty list of player info
        players = new ArrayList<RmPmPlayerInfo>();
        for (int i = 0; i < 4; i++) {
            RmPmPlayerInfo temp = new RmPmPlayerInfo();
            players.add(temp); //adds 4 temp players to the players array list
        }
        //add the cards from the deck into the players list
        int j = 0;
        int size = deck.size();
        for (int i = 0; i < size; i++) {
            players.get(j).addCard(deck.remove(0));
            if (j < players.size() - 1) {
                j++;
            } else {
                j = 0;
            }
        }
        //make the current set an empty arraylist of cards
        currentSet = new ArrayList<RmPmCard>();
        //make the player set an empty arraylist of cards
        playerSet = new ArrayList<RmPmCard>();
        // make it player 0's move
        currentPlayer = 0;
        // make lastPlayed to be something
        lastPlayed = -1;
    }// constructor

    /**
     * Constructor for objects of class RmPmGameState
     */
    public RmPmGameState(int numPlayers) {
        // initialize the state to be a brand new game

        RmPmDeck deck = new RmPmDeck(numPlayers);
        Collections.shuffle(deck.getDeck());
        //make an empty list of cards for starting playedCards
        playedCards = new ArrayList<RmPmCard>();
        //make an empty list of player info
        players = new ArrayList<RmPmPlayerInfo>();
        for (int i = 0; i < numPlayers; i++) {
            RmPmPlayerInfo temp = new RmPmPlayerInfo();
            players.add(temp); //adds 4 temp players to the players array list
        }
        //add the cards from the deck into the players list
        int j = 0;
        int size = deck.size();
        for (int i = 0; i < size; i++) {
            players.get(j).addCard(deck.remove(0));
            if (j < players.size() - 1) {
                j++;
            } else {
                j = 0;
            }
        }
        //make the current set an empty arraylist of cards
        currentSet = new ArrayList<RmPmCard>();
        //make the player set an empty arraylist of cards
        playerSet = new ArrayList<RmPmCard>();
        // make it player 0's move
        currentPlayer = 0;
        // make lastPlayed to be something
        lastPlayed = -1;
    }// constructor

    /**
     * Copy constructor for class RmPmState
     *
     * @param original the RmPmState object that we want to clone
     */
    public RmPmGameState(RmPmGameState original) {
        //copy the instance variables of the original
        playedCards = new ArrayList<RmPmCard>();
        for (int i = 0; i < original.getPlayedCards().size(); i++) {
            playedCards.add(original.getPlayedCards().get(i));
        }
        players = new ArrayList<RmPmPlayerInfo>();
        for (int i = 0; i < original.getPlayers().size(); i++) {
            players.add(original.getPlayers().get(i));
        }
        currentSet = new ArrayList<RmPmCard>();
        for (int i = 0; i < original.getCurrentSet().size(); i++) {
            currentSet.add(original.getCurrentSet().get(i));
        }
        playerSet = new ArrayList<RmPmCard>();
        currentPlayer = original.getCurrentPlayer();
        lastPlayed = original.getLastPlayed();
    }

    public ArrayList<RmPmCard> getPlayedCards() {
        return playedCards;
    }

    public ArrayList<RmPmPlayerInfo> getPlayers() {
        return players;
    }

    public ArrayList<RmPmCard> getCurrentSet() {
        return currentSet;
    }

    public ArrayList<RmPmCard> getPlayerSet() {
        return playerSet;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getLastPlayed() {
        return lastPlayed;
    }

    public void setPlayedCards(ArrayList<RmPmCard> in) {
        playedCards = in;
    }

    public void setPlayers(ArrayList<RmPmPlayerInfo> in) {
        players = in;
    }

    public void setCurrentSet(ArrayList<RmPmCard> in) {
        currentSet = in;
    }

    public void setPlayerSet(ArrayList<RmPmCard> in) {
        playerSet = in;
    }

    public void setCurrentPlayer(int in) {
        currentPlayer = in;
    }

    public void setLastPlayed(int in) {
        lastPlayed = in;
    }

    public boolean gameDone() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getHand().size() > 0) {
                return false;
            } //checking if each players has cards; if no one has cards then returns true
        }
        return true;
    }

    public boolean selectCard(int playerIndex, int cardIndex) {
        if (playerIndex != currentPlayer) {
            return false;
        }
        RmPmCard inCard = this.getPlayers().get(playerIndex).getHand().get(cardIndex);
        if (playerSet.contains(inCard)) {
            return false;
        } //if card is already in the player set return false
        if (currentSet.size() == 0) {
            for (int i = 0; i < playerSet.size(); i++) {
                if (inCard.getValue() == playerSet.get(i).getValue() || inCard.getFace() == "four" ||
                        inCard.getFace() == "five" || inCard.getFace() == "six" || playerSet.get(i).getFace() == "four" ||
                        playerSet.get(i).getFace() == "five" || playerSet.get(i).getFace() == "six") {
                    //continues if selected card either matches each card in the player set or either is a wild
                } else {
                    return false;
                }
            }
            playerSet.add(inCard);
            return true;
        } else {
            if (playerSet.size() < currentSet.size()) {
                if (playerSet.size() == 0) {
                    for (int i = 0; i < currentSet.size(); i++) {
                        if (inCard.getValue() >= currentSet.get(i).getValue() || inCard.getFace() == "four" ||
                                inCard.getFace() == "five" || inCard.getFace() == "six" || currentSet.get(i).getFace() == "four" ||
                                currentSet.get(i).getFace() == "five" || currentSet.get(i).getFace() == "six") {
                            //continues if selected card either matches each card in the current set; is less than; or is a wild
                        } else {
                            return false;
                        }
                    }
                    playerSet.add(inCard);
                    return true;
                } else {
                    for (int i = 0; i < currentSet.size(); i++) {
                        if (inCard.getValue() >= currentSet.get(i).getValue() || inCard.getFace() == "four" ||
                                inCard.getFace() == "five" || inCard.getFace() == "six" || currentSet.get(i).getFace() == "four" ||
                                currentSet.get(i).getFace() == "five" || currentSet.get(i).getFace() == "six") {
                            //continues if selected card either matches each card in the current set; is less than; or is a wild
                        } else {
                            return false;
                        }
                    }
                    for (int i = 0; i < playerSet.size(); i++) {
                        if (inCard.getValue() == playerSet.get(i).getValue() || inCard.getFace() == "four" ||
                                inCard.getFace() == "five" || inCard.getFace() == "six" || playerSet.get(i).getFace() == "four" ||
                                playerSet.get(i).getFace() == "five" || playerSet.get(i).getFace() == "six") {
                            //continues if selected card either matches each card in the player set or either is a wild
                        } else {
                            return false;
                        }
                    }
                    playerSet.add(inCard);
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public boolean deselectCard(int playerIndex, int cardIndex) {
        if (playerIndex != currentPlayer) {
            return false;
        }
        if (playerSet.contains(this.getPlayers().get(playerIndex).getHand().get(cardIndex))) {
            playerSet.remove(this.getPlayers().get(playerIndex).getHand().get(cardIndex));
            return true;
        } //if the player set contains the card that you are deselecting, then removes card from player set
        return false;
    }

    public boolean passTurn(int playerIndex) {
        if (playerIndex != currentPlayer) {
            return false;
        }
        playerSet.clear();
        nextPlayer();
        while (players.get(currentPlayer).getHand().size() < 1) {
            nextPlayer();
        } //skips players that don't have cards
        if (currentPlayer == lastPlayed) {
            ArrayList<RmPmCard> emptySet = new ArrayList<RmPmCard>();
            this.setCurrentSet(emptySet);
        } //sets the current set to empty if the last person to play cards is the current player
        return true;
    }

    public boolean playSet(int playerIndex) {
        if (playerIndex != currentPlayer) {
            return false;
        }
        ArrayList<RmPmCard> hand = this.getPlayers().get(playerIndex).getHand();
        if (playerSet.size() != currentSet.size() && currentSet.size() != 0) {
            return false;
        }
        Boolean skipTurn = false;
        playerLoop:
        for (int i = 0; i < playerSet.size(); i++) {
            for (int j = 0; j < currentSet.size(); j++) {
                if (playerSet.get(i).getValue() == currentSet.get(i).getValue() && currentSet.get(i).getFace() != "four" &&
                        currentSet.get(i).getFace() != "five" && currentSet.get(i).getFace() != "six") {
                    skipTurn = true;
                    break playerLoop; //so only skips one player
                }
            }
        } //if the current and player set has the same value does an extra next player
        for (int i = 0; i < playerSet.size(); i++) {
            if (playerSet.get(i).getFace() == "three") {
                for (int j = 0; j < playerSet.size(); j++) {
                    hand.remove(playerSet.get(j));
                } //copies the player set into the current set
                playerSet.clear();
                currentSet.clear();
                if (hand.size() == 0) {
                    if (playerIndex > 0) {
                        lastPlayed--;
                    } else {
                        lastPlayed = players.size() - 1;
                    }
                    while (players.get(lastPlayed).getHand().size() < 1) {
                        if (lastPlayed > 0) {
                            lastPlayed--;
                        } else {
                            lastPlayed = players.size() - 1;
                        }
                    }
                    nextPlayer();
                } else {
                    lastPlayed = currentPlayer;
                }
                while (this.getPlayers().get(currentPlayer).getHand().size() == 0) {
                    nextPlayer();
                } //continues around until a person who has cards is now the current player
                return true;
            }
        } //if the player set is a bomb clears the set and lets them play another card
        int numWilds = 0;
        for (int i = 0; i < playerSet.size(); i++) {
            if (playerSet.get(i).getFace() == "four" || playerSet.get(i).getFace() == "five" || playerSet.get(i).getFace() == "six") {
                numWilds++;
            }
        }
        if (numWilds == playerSet.size()) {
            for (int j = 0; j < playerSet.size(); j++) {
                hand.remove(playerSet.get(j));
            } //copies the player set into the current set
            playerSet.clear();
            currentSet.clear();
            if (hand.size() == 0) {
                if (playerIndex > 0) {
                    lastPlayed--;
                } else {
                    lastPlayed = players.size() - 1;
                }
                while (players.get(lastPlayed).getHand().size() < 1) {
                    if (lastPlayed > 0) {
                        lastPlayed--;
                    } else {
                        lastPlayed = players.size() - 1;
                    }
                }
                nextPlayer();
            } else {
                lastPlayed = currentPlayer;
            }
            while (this.getPlayers().get(currentPlayer).getHand().size() == 0) {
                nextPlayer();
            } //continues around until a person who has cards is now the current player
            return true;
        } //if the player set is all wilds clears the set
        currentSet.clear();
        for (int i = 0; i < playerSet.size(); i++) {
            currentSet.add(playerSet.get(i));
            hand.remove(playerSet.get(i));
        } //copies the player set into the current set
        this.getPlayers().get(playerIndex).setHand(hand); //updates the player's hand
        if (hand.size() == 0) {
            if (playerIndex > 0) {
                lastPlayed--;
            } else {
                lastPlayed = players.size() - 1;
            }
            while (players.get(lastPlayed).getHand().size() < 1) {
                if (lastPlayed > 0) {
                    lastPlayed--;
                } else {
                    lastPlayed = players.size() - 1;
                }
            }
            nextPlayer();
        } else {
            lastPlayed = currentPlayer;
        }
        if (skipTurn) {
            nextPlayer();
        }
        nextPlayer();
        while (this.getPlayers().get(currentPlayer).getHand().size() == 0) {
            nextPlayer();
        } //continues around until a person who has cards is now the current player
        if (currentPlayer == lastPlayed) {
            ArrayList<RmPmCard> emptySet = new ArrayList<RmPmCard>();
            this.setCurrentSet(emptySet);
        } //sets the current set to empty if the last person to play cards is the current player
        return true;
    }

    public void nextPlayer() {
        if (currentPlayer == players.size() - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        } //takes turn in a loop from 0 to (player set size -1)
    }

    public int playersWithCards() {
        int count = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getHand().size() > 0) {
                count++;
            } //if a player has a card they add to the count
        }
        return count;
    }

    public void reDeal() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).getHand().clear();
            players.get(i).setStanding(-1);
        }
        RmPmDeck deck = new RmPmDeck(players.size());
        int j = 0;
        int size = deck.size();
        for (int i = 0; i < size; i++) {
            players.get(j).addCard(deck.remove(0));
            if (j < players.size() - 1) {
                j++;
            } else {
                j = 0;
            }
        }
    }

    public void dumbAi(int playerIndex) {
        if (this.selectCard(playerIndex, 0)) {
            if (this.playSet(playerIndex)) {
                this.playerSet.clear();
            } else {
                this.passTurn(playerIndex);
            }
        } else {
            this.passTurn(playerIndex);
        }
    }
}