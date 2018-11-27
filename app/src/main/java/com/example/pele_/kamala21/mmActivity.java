package com.example.pele_.kamala21;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mmActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    Button exitButton;
    Button rulesButton;
    Button playSingleButton;
    Button optionsButton;
    Button startLocalButton;
    EditText startServerID;
    Button joinButton;
    EditText joinServerID;
    FirebaseDatabase database;
    DatabaseReference databaseRef;
    DatabaseReference lobbyRef;
    DatabaseReference numPlayersRef;
    int playerIndex;
    boolean serverExists = false;
    boolean soloServer = false;
    int soloServerID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmpm_main_menu);

        exitButton = (Button) findViewById(R.id.mmExitButton);
        exitButton.setOnClickListener(this);
        rulesButton = (Button) findViewById(R.id.mmRulesButton);
        rulesButton.setOnClickListener(this);
        playSingleButton = (Button) findViewById(R.id.mmSingleButton);
        playSingleButton.setOnClickListener(this);
        optionsButton = (Button) findViewById(R.id.mmOptionsButton);
        optionsButton.setOnClickListener(this);
        startLocalButton = (Button) findViewById(R.id.mmLocalButton);
        startLocalButton.setOnClickListener(this);
        startServerID = (EditText) findViewById(R.id.mmStartID);
        joinButton = (Button) findViewById(R.id.mmJoinButton);
        joinButton.setOnClickListener(this);
        joinServerID = (EditText) findViewById(R.id.mmJoinID);
        joinServerID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //does nothing before text is changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //does nothing while text is changed
            }

            @Override
            public void afterTextChanged(Editable s) {
                databaseRef.child("temp").setValue(1);
                databaseRef.child("temp").setValue(null);
            } //updates the server causing onDataChange to fire
        });
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
        databaseRef.addValueEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.hasChild("multiPlayerLobby" + joinServerID.getText())) {
            if (dataSnapshot.child("multiPlayerLobby" + joinServerID.getText().toString()).child("numHumans").getValue(Integer.class) != null) {
                playerIndex = dataSnapshot.child("multiPlayerLobby" + joinServerID.getText().toString()).child("numHumans").getValue(Integer.class) + 1;
                serverExists = true;
            }
        } else {
            serverExists = false;
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mmExitButton:
                System.exit(0);
                break;
            case R.id.mmRulesButton:
                Intent htpIntent = new Intent(this, htpActivity.class);
                startActivity(htpIntent);
                finish();
                break;
            case R.id.mmSingleButton:
                Intent spmIntent = new Intent(this, spmActivity.class);
                soloServerID = (int)(Math.random() * 200);
                spmIntent.putExtra("serverID", soloServerID);
                startActivity(spmIntent);
                finish();
                break;
            case R.id.mmOptionsButton:
                Intent osIntent = new Intent(this, osActivity.class);
                startActivity(osIntent);
                finish();
                break;
            case R.id.mmLocalButton:
                if (!serverExists) {
                    Intent lmmIntent = new Intent(this, lmmActivity.class);
                    lmmIntent.putExtra("serverID", startServerID.getText().toString());
                    startActivity(lmmIntent);
                    finish();
                } else {
                    Toast.makeText(this.getApplicationContext(), "Server already exists", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mmJoinButton:
                if (serverExists) {
                    Intent lmgsJoinIntent = new Intent(this, lmwActivity.class);
                    lmgsJoinIntent.putExtra("playerIndex", playerIndex);
                    lmgsJoinIntent.putExtra("serverID", joinServerID.getText().toString());
                    startActivity(lmgsJoinIntent);
                    finish();
                } else {
                    Toast.makeText(this.getApplicationContext(), "Server does not exist", Toast.LENGTH_SHORT).show();
                }
            default:
                break;
        }
    }
}
