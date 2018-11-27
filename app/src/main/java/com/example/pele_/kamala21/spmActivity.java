package com.example.pele_.kamala21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class spmActivity extends Activity implements  View.OnClickListener{
    Button returnButton;
    Button startButton;
    Button increaseButton;
    Button decreaseButton;
    Button addButton;
    Button removeButton;
    TextView difficulty;
    TextView numBots;
    int numInt;

    FirebaseDatabase database;
    DatabaseReference lobbyRef;
    DatabaseReference numPlayersRef;
    DatabaseReference diffAIRef;
    DatabaseReference gameStateRef;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmpm_single_player_menu);

        returnButton = (Button)findViewById(R.id.spmReturnButton);
        returnButton.setOnClickListener(this);
        startButton = (Button)findViewById(R.id.spmStartButton);
        startButton.setOnClickListener(this);
        increaseButton = (Button)findViewById(R.id.spmIncreaseButton);
        increaseButton.setOnClickListener(this);
        decreaseButton = (Button)findViewById(R.id.spmDecreaseButton);
        decreaseButton.setOnClickListener(this);
        addButton = (Button)findViewById(R.id.spmAddButton);
        addButton.setOnClickListener(this);
        removeButton = (Button)findViewById(R.id.spmRemoveButton);
        removeButton.setOnClickListener(this);
        difficulty = (TextView)findViewById(R.id.difficultyBotsText);
        numBots = (TextView)findViewById(R.id.spmNumBotsText);
        numInt = 3;

        database = FirebaseDatabase.getInstance();
        lobbyRef = database.getReference().child("singlePlayerLobby");
        gameStateRef = database.getReference().child("singlePlayerGameState");
        numPlayersRef = lobbyRef.child("numberOfPlayers");
        numPlayersRef.setValue(Integer.toString(numInt+1));
        diffAIRef = lobbyRef.child("intelligence");
        diffAIRef.setValue("dumb");
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.spmReturnButton:
                Intent mmIntent = new Intent(this, mmActivity.class);
                startActivity(mmIntent);
                finish();
                break;
            case R.id.spmStartButton:
                RmPmGameState instance = new RmPmGameState(numInt+1);
                gameStateRef.setValue(instance);
                Intent spgsIntent = new Intent(this, spgsActivity.class);
                spgsIntent.putExtra("serverID", Integer.toString(1));
                startActivity(spgsIntent);
                finish();
                break;
            case R.id.spmIncreaseButton:
                diffAIRef.setValue("smart");
                difficulty.setText("smart");
                break;
            case R.id.spmDecreaseButton:
                diffAIRef.setValue("dumb");
                difficulty.setText("dumb");
                break;
            case R.id.spmAddButton:
                if(numInt+1 < 6){
                    numInt++;
                    numBots.setText(Integer.toString(numInt));
                    numPlayersRef.setValue(Integer.toString(numInt+1));
                }
                break;
            case R.id.spmRemoveButton:
                if(numInt-1 > 0){
                    numInt--;
                    numBots.setText(Integer.toString(numInt));
                    numPlayersRef.setValue(Integer.toString(numInt+1));
                }
                break;
            default:
                break;
        }
    }
}
