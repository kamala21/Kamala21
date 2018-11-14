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


public class lmwActivity extends Activity implements ValueEventListener {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmpm_wait_screen);

        numAiInt = 0;
        numHumanInt = 3;
        playerIndex = 0;
        playerIndex = getIntent().getIntExtra("playerIndex", -1);
        serverID = getIntent().getStringExtra("serverID");
        database = FirebaseDatabase.getInstance();
        lobbyRef = database.getReference().child("multiPlayerLobby" +serverID);
        gameStateRef = database.getReference().child("multiPlayerGameState" +serverID);
        numPlayersRef = lobbyRef.child("totalNumberOfPlayers");
        numHumanRef = lobbyRef.child("numHumans");
        numAiRef = lobbyRef.child("numAi");
        if (playerIndex != 0) {
            numHumanRef.setValue(playerIndex);
        }
        lobbyRef.addValueEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        numAiInt = dataSnapshot.child("numAi").getValue(Integer.class);
        numHumanInt = dataSnapshot.child("numHumans").getValue(Integer.class);

        if (playerIndex == 0) {
            if (numAiInt + numHumanInt + 1 == dataSnapshot.child("totalNumberOfPlayers").getValue(Integer.class)) {
                RmPmGameState instance = new RmPmGameState(numAiInt + numHumanInt + 1);
                gameStateRef.setValue(instance);
                Intent lmgsStartIntent = new Intent(this, lmgsActivity.class);
                lmgsStartIntent.putExtra("playerIndex", 0);
                lmgsStartIntent.putExtra("numHumans", numHumanInt);
                lmgsStartIntent.putExtra("serverID", serverID);
                startActivity(lmgsStartIntent);
                finish();
            }
        } else {
            if (dataSnapshot.child("numAi").getValue(Integer.class) + dataSnapshot.child("numHumans").getValue(Integer.class)
                    + 1 == dataSnapshot.child("totalNumberOfPlayers").getValue(Integer.class)) {
                Intent lmgsJoinIntent = new Intent(this, lmgsActivity.class);
                lmgsJoinIntent.putExtra("playerIndex", playerIndex);
                lmgsJoinIntent.putExtra("serverID", serverID);
                startActivity(lmgsJoinIntent);
                finish();
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

}
