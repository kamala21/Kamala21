package com.example.pele_.kamala21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class lmmActivity extends Activity implements View.OnClickListener {
    Button returnButton;
    Button startButton;
    Button increaseButton;
    Button decreaseButton;
    Button addBotButton;
    Button removeBotButton;
    Button addHumanButton;
    Button removeHumanButton;
    TextView difficulty;
    TextView numBots;
    TextView numHumans;
    int numAiInt;
    int numHumanInt;
    int playerIndex;
    String serverID;

    FirebaseDatabase database;
    DatabaseReference lobbyRef;
    DatabaseReference numPlayersRef;
    DatabaseReference diffAIRef;
    DatabaseReference gameStateRef;
    DatabaseReference numHumanRef;
    DatabaseReference numAiRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Creates multi player menu screen and all contents in the menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmpm_local_multiplayer_menu);

        returnButton = (Button) findViewById(R.id.lmmReturnButton);
        returnButton.setOnClickListener(this);
        startButton = (Button) findViewById(R.id.lmmStartServerButton);
        startButton.setOnClickListener(this);
        increaseButton = (Button) findViewById(R.id.lmmIncreaseButton);
        increaseButton.setOnClickListener(this);
        decreaseButton = (Button) findViewById(R.id.lmmDecreaseButton);
        decreaseButton.setOnClickListener(this);
        addBotButton = (Button) findViewById(R.id.lmmAddButton);
        addBotButton.setOnClickListener(this);
        removeBotButton = (Button) findViewById(R.id.lmmRemoveButton);
        removeBotButton.setOnClickListener(this);
        addHumanButton = (Button) findViewById(R.id.lmmAddHumanButton);
        addHumanButton.setOnClickListener(this);
        removeHumanButton = (Button) findViewById(R.id.lmmRemoveHumanButton);
        removeHumanButton.setOnClickListener(this);
        difficulty = (TextView) findViewById(R.id.lmmDifficultyBotsText);
        numBots = (TextView) findViewById(R.id.lmmNumBotsText);
        numHumans = (TextView) findViewById(R.id.lmmNumHumanText);
        numAiInt = 0;
        numHumanInt = 3;
        playerIndex = 0;

        database = FirebaseDatabase.getInstance();
        serverID = this.getIntent().getStringExtra("serverID");
        lobbyRef = database.getReference().child("multiPlayerLobby" + serverID);
        gameStateRef = database.getReference().child("multiPlayerGameState" + serverID);

        numPlayersRef = lobbyRef.child("totalNumberOfPlayers");
        numPlayersRef.setValue(numHumanInt + 1);
        diffAIRef = lobbyRef.child("intelligence");
        diffAIRef.setValue("dumb");//sets values off AI to dumb
        numHumanRef = lobbyRef.child("numHumans");
        numHumanRef.setValue(numHumanInt);
        numAiRef = lobbyRef.child("numAi");
        numAiRef.setValue(numAiInt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lmmReturnButton://exits multi player menu and opens main menu
                lobbyRef.setValue(null);
                Intent mmIntent = new Intent(this, mmActivity.class);
                startActivity(mmIntent);
                finish();
                break;
            case R.id.lmmStartServerButton://Starts multi player server and opens wait screen
                numHumanRef.setValue(0);
                Intent lmgsIntent = new Intent(this, lmwActivity.class);
                lmgsIntent.putExtra("playerIndex", 0);
                lmgsIntent.putExtra("serverID", serverID);
                startActivity(lmgsIntent);
                finish();
                break;
            case R.id.lmmIncreaseButton://sets AI value to smart
                diffAIRef.setValue("smart");
                difficulty.setText("smart");
                break;
            case R.id.lmmDecreaseButton://Sets AI value to dumb
                diffAIRef.setValue("dumb");
                difficulty.setText("dumb");
                break;
                //Next two buttons add or drop AI players
            case R.id.lmmAddButton:
                if (numAiInt + numHumanInt < 5) {
                    numAiInt++;
                    numBots.setText(Integer.toString(numAiInt));
                    numAiRef.setValue(numAiInt);
                    numPlayersRef.setValue(numAiInt + numHumanInt + 1);
                }
                break;
            case R.id.lmmRemoveButton:
                if (numAiInt + numHumanInt > 1 && numAiInt > 0) {
                    numAiInt--;
                    numBots.setText(Integer.toString(numAiInt));
                    numAiRef.setValue(numAiInt);
                    numPlayersRef.setValue(numAiInt + numHumanInt + 1);
                }
                break;
                //Next two buttons add or drop Human players
            case R.id.lmmAddHumanButton:
                if (numAiInt + numHumanInt < 5) {
                    numHumanInt++;
                    numHumans.setText(Integer.toString(numHumanInt));
                    numHumanRef.setValue(numHumanInt);
                    numPlayersRef.setValue(numAiInt + numHumanInt + 1);
                }
                break;
            case R.id.lmmRemoveHumanButton:
                if (numAiInt + numHumanInt > 1 && numHumanInt > 0) {
                    numHumanInt--;
                    numHumans.setText(Integer.toString(numHumanInt));
                    numHumanRef.setValue(numHumanInt);
                    numPlayersRef.setValue(numAiInt + numHumanInt + 1);
                }
                break;
            default:
                break;
        }
    }
}
