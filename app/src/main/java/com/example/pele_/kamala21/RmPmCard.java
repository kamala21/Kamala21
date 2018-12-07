package com.example.pele_.kamala21;

public class RmPmCard {
    private int value;
    private String suit;

    public RmPmCard(){
        //Sets default constructor and makes a 6 of hearts card
        value = 6;
        suit = "hearts";
    }

    public RmPmCard(int inValue, String inSuit){
        //Deep Copy
        value = inValue;
        suit = inSuit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getFace(){ //Returns face value of card based on its game value
        int temp = this.value;
        if(temp == 0){
            return "seven";
        } else if(temp == 1){
            return "eight";
        } else if(temp == 2){
            return "nine";
        } else if(temp == 3){
            return "ten";
        } else if(temp == 4){
            return "jack";
        } else if(temp == 5){
            return "queen";
        } else if(temp == 6){
            return "king";
        } else if(temp == 7){
            return "ace";
        } else if(temp == 8){
            return "two";
        } else if(temp == 9){
            return "three";
        } else if(temp == 10){
            return "four";
        } else if(temp == 11){
            return "five";
        } else if(temp == 12) {
            return "six";
        }
        return null;
    }

    public String getCardName(){
        return this.getFace() +"_" +this.getSuit();
    }

    public int getDrawable() {//Draws each card
        switch (this.getFace()){
            case "seven":
                if(this.getSuit() == "clubs"){
                    return R.drawable.seven_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.seven_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.seven_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.seven_spades;
                }
                break;
            case "eight":
                if(this.getSuit() == "clubs"){
                    return R.drawable.eight_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.eight_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.eight_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.eight_spades;
                }
                break;
            case "nine":
                if(this.getSuit() == "clubs"){
                    return R.drawable.nine_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.nine_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.nine_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.nine_spades;
                }
                break;
            case "ten":
                if(this.getSuit() == "clubs"){
                    return R.drawable.ten_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.ten_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.ten_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.ten_spades;
                }
                break;
            case "jack":
                if(this.getSuit() == "clubs"){
                    return R.drawable.jack_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.jack_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.jack_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.jack_spades;
                }
                break;
            case "queen":
                if(this.getSuit() == "clubs"){
                    return R.drawable.queen_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.queen_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.queen_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.queen_spades;
                }
                break;
            case "king":
                if(this.getSuit() == "clubs"){
                    return R.drawable.king_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.king_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.king_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.king_spades;
                }
                break;
            case "ace":
                if(this.getSuit() == "clubs"){
                    return R.drawable.ace_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.ace_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.ace_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.ace_spades;
                }
                break;
            case "two":
                if(this.getSuit() == "clubs"){
                    return R.drawable.two_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.two_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.two_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.two_spades;
                }
                break;
            case "three":
                if(this.getSuit() == "clubs"){
                    return R.drawable.three_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.three_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.three_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.three_spades;
                }
                break;
            case "four":
                if(this.getSuit() == "clubs"){
                    return R.drawable.four_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.four_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.four_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.four_spades;
                }
                break;
            case "five":
                if(this.getSuit() == "clubs"){
                    return R.drawable.five_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.five_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.five_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.five_spades;
                }
                break;
            case "six":
                if(this.getSuit() == "clubs"){
                    return R.drawable.six_clubs;
                } else if(this.getSuit() == "diamonds"){
                    return R.drawable.six_diamonds;
                } else if(this.getSuit() == "hearts"){
                    return  R.drawable.six_hearts;
                } else if(this.getSuit() == "spades"){
                    return  R.drawable.six_spades;
                }
                break;
            default:
                break;
        }
        return R.drawable.blue_back;
    }
}
