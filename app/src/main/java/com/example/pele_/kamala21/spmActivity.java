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
    int serverID;

    FirebaseDatabase database;
    DatabaseReference lobbyRef;
    DatabaseReference numPlayersRef;
    DatabaseReference diffAIRef;
    DatabaseReference gameStateRef;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Creates items in single player mode menu
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
        serverID = this.getIntent().getIntExtra("serverID", -1);
        lobbyRef = database.getReference().child("singlePlayerLobby" + Integer.toString(serverID));
        gameStateRef = database.getReference().child("singlePlayerGameState" + Integer.toString(serverID));
        numPlayersRef = lobbyRef.child("numberOfPlayers");
        numPlayersRef.setValue(Integer.toString(numInt+1));
        diffAIRef = lobbyRef.child("intelligence");
        diffAIRef.setValue("dumb");
    }

    @Override
    public void onClick(View v){
        //Defines actions for each item when clicked
        switch (v.getId()){
            case R.id.spmReturnButton://Return to mainmenu when button is clicked
                lobbyRef.setValue(null);
                Intent mmIntent = new Intent(this, mmActivity.class);
                startActivity(mmIntent);
                finish();
                break;
            case R.id.spmStartButton://Starts a single player game
                RmPmGameState instance = new RmPmGameState(numInt+1);
                gameStateRef.setValue(instance);
                Intent spgsIntent = new Intent(this, spgsActivity.class);
                spgsIntent.putExtra("serverID", serverID);
                startActivity(spgsIntent);
                finish();
                break;
                //Next two buttons set AI difficulty
            case R.id.spmIncreaseButton:
                diffAIRef.setValue("smart");
                difficulty.setText("smart");
                break;
            case R.id.spmDecreaseButton:
                diffAIRef.setValue("dumb");
                difficulty.setText("dumb");
                break;
                //Next two buttons add and remove AI players
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
