package com.example.pele_.kamala21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Comparator;

public class lmgsActivity extends Activity implements View.OnClickListener {
    RmPmGameState instance;
    ImageButton card0;
    ImageButton card1;
    ImageButton card2;
    ImageButton card3;
    ImageButton card4;
    ImageButton card5;
    ImageButton card6;
    ImageButton card7;
    ImageButton card8;
    ImageButton card9;
    ImageButton card10;
    ImageButton card11;
    ImageButton card12;
    Button playButton;
    Button passButton;
    TextView currentPlayerText;
    TextView whoAmI;
    TextView numCards1;
    TextView numCards2;
    TextView numCards3;
    TextView numCards4;
    TextView numCards5;
    FrameLayout firstPlayer;
    FrameLayout secondPlayer;
    FrameLayout thirdPlayer;
    FrameLayout fourthPlayer;
    FrameLayout fifthPlayer;
    ImageView currentSet0;
    ImageView currentSet1;
    ImageView currentSet2;
    ImageView currentSet3;
    ImageView currentSet4;
    ImageView currentSet5;
    ImageView currentSet6;
    ImageView currentSet7;
    ImageView currentSet8;
    ImageView currentSet9;

    FirebaseDatabase database;
    DatabaseReference lobbyRef;
    DatabaseReference gameStateRef;

    int count;
    int playerIndex;
    int numHumans;
    String intelligence;
    String serverID;

    Button rageQuitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmpm_game_screen);

        playerIndex = getIntent().getIntExtra("playerIndex", -1);
        serverID = getIntent().getStringExtra("serverID");
        numHumans = getIntent().getIntExtra("numHumans", 0);
        whoAmI = (TextView) findViewById(R.id.whoAmITextView);
        whoAmI.setText("You Are: P" + Integer.toString(playerIndex + 1));
        count = 0;
        card0 = (ImageButton) findViewById(R.id.card0);
        card0.setOnClickListener(this);
        card1 = (ImageButton) findViewById(R.id.card1);
        card1.setOnClickListener(this);
        card2 = (ImageButton) findViewById(R.id.card2);
        card2.setOnClickListener(this);
        card3 = (ImageButton) findViewById(R.id.card3);
        card3.setOnClickListener(this);
        card4 = (ImageButton) findViewById(R.id.card4);
        card4.setOnClickListener(this);
        card5 = (ImageButton) findViewById(R.id.card5);
        card5.setOnClickListener(this);
        card6 = (ImageButton) findViewById(R.id.card6);
        card6.setOnClickListener(this);
        card7 = (ImageButton) findViewById(R.id.card7);
        card7.setOnClickListener(this);
        card8 = (ImageButton) findViewById(R.id.card8);
        card8.setOnClickListener(this);
        card9 = (ImageButton) findViewById(R.id.card9);
        card9.setOnClickListener(this);
        card10 = (ImageButton) findViewById(R.id.card10);
        card10.setOnClickListener(this);
        card11 = (ImageButton) findViewById(R.id.card11);
        card11.setOnClickListener(this);
        card12 = (ImageButton) findViewById(R.id.card12);
        card12.setOnClickListener(this);
        playButton = (Button) findViewById(R.id.playSetButton);
        playButton.setOnClickListener(this);
        passButton = (Button) findViewById(R.id.passTurnButton);
        passButton.setOnClickListener(this);
        currentPlayerText = (TextView) findViewById(R.id.currentPlayerText);
        numCards1 = (TextView) findViewById(R.id.numCardsP1);
        numCards2 = (TextView) findViewById(R.id.numCardsP2);
        numCards3 = (TextView) findViewById(R.id.numCardsP3);
        numCards4 = (TextView) findViewById(R.id.numCardsP4);
        numCards5 = (TextView) findViewById(R.id.numCardsP5);
        firstPlayer = (FrameLayout) findViewById(R.id.firstPlayer);
        secondPlayer = (FrameLayout) findViewById(R.id.secondPlayer);
        thirdPlayer = (FrameLayout) findViewById(R.id.thirdPlayer);
        fourthPlayer = (FrameLayout) findViewById(R.id.fourthPlayer);
        fifthPlayer = (FrameLayout) findViewById(R.id.fifthPlayer);
        currentSet0 = (ImageView) findViewById(R.id.currentSet0);
        currentSet1 = (ImageView) findViewById(R.id.currentSet1);
        currentSet2 = (ImageView) findViewById(R.id.currentSet2);
        currentSet3 = (ImageView) findViewById(R.id.currentSet3);
        currentSet4 = (ImageView) findViewById(R.id.currentSet4);
        currentSet5 = (ImageView) findViewById(R.id.currentSet5);
        currentSet6 = (ImageView) findViewById(R.id.currentSet6);
        currentSet7 = (ImageView) findViewById(R.id.currentSet7);
        currentSet8 = (ImageView) findViewById(R.id.currentSet8);
        currentSet9 = (ImageView) findViewById(R.id.currentSet9);
        database = FirebaseDatabase.getInstance();
        gameStateRef = database.getReference().child("multiPlayerGameState" + serverID);
        lobbyRef = database.getReference().child("multiPlayerLobby" + serverID);
        lobbyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                intelligence = dataSnapshot.child("intelligence").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        gameStateRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                instance = dataSnapshot.getValue(RmPmGameState.class);
                if (instance != null) {
                    updateScreen();
                    if (playerIndex == 0) {
                        if (instance.playersWithCards() < 2) {
                            instance.reDeal();
                            instance.getCurrentSet().clear();
                            RmPmGameState updatedInstance = new RmPmGameState(instance);
                            gameStateRef.setValue(updatedInstance);
                            recreate();
                        }
                        if (instance.getCurrentPlayer() > numHumans) {
                            instance.dumbAi(instance.getCurrentPlayer());
                            RmPmGameState updatedInstance = new RmPmGameState(instance);
                            gameStateRef.setValue(updatedInstance);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        rageQuitButton = (Button) findViewById(R.id.rageQuitButton);
        rageQuitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card0:
                if (card0.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 0)) {
                        card0.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 0)) {
                        card0.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card1:
                if (card1.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 1)) {
                        card1.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 1)) {
                        card1.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card2:
                if (card2.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 2)) {
                        card2.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 2)) {
                        card2.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card3:
                if (card3.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 3)) {
                        card3.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 3)) {
                        card3.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card4:
                if (card0.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 4)) {
                        card4.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 4)) {
                        card4.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card5:
                if (card5.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 5)) {
                        card5.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 5)) {
                        card5.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card6:
                if (card6.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 6)) {
                        card6.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 6)) {
                        card6.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card7:
                if (card7.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 7)) {
                        card7.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 7)) {
                        card7.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card8:
                if (card8.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 8)) {
                        card8.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 8)) {
                        card8.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card9:
                if (card9.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 9)) {
                        card9.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 9)) {
                        card9.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card10:
                if (card10.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 10)) {
                        card10.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 10)) {
                        card10.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card11:
                if (card11.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 11)) {
                        card11.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 11)) {
                        card11.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.card12:
                if (card12.getColorFilter() == null) {
                    if (instance.selectCard(playerIndex, 12)) {
                        card12.setColorFilter(R.color.highlight);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (instance.deselectCard(playerIndex, 12)) {
                        card12.setColorFilter(null);
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Invalid Move",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.playSetButton:
                if (instance.playSet(playerIndex)) {
                    RmPmGameState updatedInstance = new RmPmGameState(instance);
                    gameStateRef.setValue(updatedInstance);
                } //if the player plays a valid move it sends the updated game state to the online database
                else {
                    Toast.makeText(getApplication().getApplicationContext(), "Invalid Play / Not Your Turn",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.passTurnButton:
                if (instance.passTurn(playerIndex)) {
                    RmPmGameState updatedInstance = new RmPmGameState(instance);
                    gameStateRef.setValue(updatedInstance);
                } //if the player does a valid pass it sends the updated game state to the online database
                break;
            case R.id.rageQuitButton:
                if (playerIndex == 0) {
                    lobbyRef.setValue(null);
                    gameStateRef.setValue(null);
                    Intent mmIntent = new Intent(this, mmActivity.class);
                    startActivity(mmIntent);
                    finish();
                } else {
                    Intent mmIntent = new Intent(this, mmActivity.class);
                    startActivity(mmIntent);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    public void updateCards() {
        card0.setColorFilter(null);
        card1.setColorFilter(null);
        card2.setColorFilter(null);
        card3.setColorFilter(null);
        card4.setColorFilter(null);
        card5.setColorFilter(null);
        card6.setColorFilter(null);
        card7.setColorFilter(null);
        card9.setColorFilter(null);
        card8.setColorFilter(null);
        card10.setColorFilter(null);
        card11.setColorFilter(null);
        card12.setColorFilter(null);
        Collections.sort(instance.getPlayers().get(playerIndex).getHand(), new Comparator<RmPmCard>() {
            @Override
            public int compare(RmPmCard o1, RmPmCard o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        int i = instance.getPlayers().get(playerIndex).getHand().size();
        if (i > 0) {
            card0.setVisibility(View.VISIBLE);
            card0.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(0).getCardName(), "drawable", getPackageName()));
            if (i > 1) {
                card1.setVisibility(View.VISIBLE);
                card1.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(1).getCardName(), "drawable", getPackageName()));
                if (i > 2) {
                    card2.setVisibility(View.VISIBLE);
                    card2.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(2).getCardName(), "drawable", getPackageName()));
                    if (i > 3) {
                        card3.setVisibility(View.VISIBLE);
                        card3.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(3).getCardName(), "drawable", getPackageName()));
                        if (i > 4) {
                            card4.setVisibility(View.VISIBLE);
                            card4.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(4).getCardName(), "drawable", getPackageName()));
                            if (i > 5) {
                                card5.setVisibility(View.VISIBLE);
                                card5.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(5).getCardName(), "drawable", getPackageName()));
                                if (i > 6) {
                                    card6.setVisibility(View.VISIBLE);
                                    card6.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(6).getCardName(), "drawable", getPackageName()));
                                    if (i > 7) {
                                        card7.setVisibility(View.VISIBLE);
                                        card7.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(7).getCardName(), "drawable", getPackageName()));
                                        if (i > 8) {
                                            card8.setVisibility(View.VISIBLE);
                                            card8.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(8).getCardName(), "drawable", getPackageName()));
                                            if (i > 9) {
                                                card9.setVisibility(View.VISIBLE);
                                                card9.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(9).getCardName(), "drawable", getPackageName()));
                                                if (i > 10) {
                                                    card10.setVisibility(View.VISIBLE);
                                                    card10.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(10).getCardName(), "drawable", getPackageName()));
                                                    if (i > 11) {
                                                        card11.setVisibility(View.VISIBLE);
                                                        card11.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(11).getCardName(), "drawable", getPackageName()));
                                                        if (i > 12) {
                                                            card12.setVisibility(View.VISIBLE);
                                                            card12.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(12).getCardName(), "drawable", getPackageName()));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (i < 13) {
            card12.setVisibility(View.INVISIBLE);
            if (i < 12) {
                card11.setVisibility(View.INVISIBLE);
                if (i < 11) {
                    card10.setVisibility(View.INVISIBLE);
                    if (i < 10) {
                        card9.setVisibility(View.INVISIBLE);
                        if (i < 9) {
                            card8.setVisibility(View.INVISIBLE);
                            if (i < 8) {
                                card7.setVisibility(View.INVISIBLE);
                                if (i < 7) {
                                    card6.setVisibility(View.INVISIBLE);
                                    if (i < 6) {
                                        card5.setVisibility(View.INVISIBLE);
                                        if (i < 5) {
                                            card4.setVisibility(View.INVISIBLE);
                                            if (i < 4) {
                                                card3.setVisibility(View.INVISIBLE);
                                                if (i < 3) {
                                                    card2.setVisibility(View.INVISIBLE);
                                                    if (i < 2) {
                                                        card1.setVisibility(View.INVISIBLE);
                                                        if (i < 1) {
                                                            card0.setVisibility(View.INVISIBLE);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateCurrentSet() {
        int i = instance.getCurrentSet().size();
        if (i > 0) {
            currentSet0.setVisibility(View.VISIBLE);
            currentSet0.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(0).getCardName(), "drawable", getPackageName()));
            if (i > 1) {
                currentSet1.setVisibility(View.VISIBLE);
                currentSet1.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(1).getCardName(), "drawable", getPackageName()));
                if (i > 2) {
                    currentSet2.setVisibility(View.VISIBLE);
                    currentSet2.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(2).getCardName(), "drawable", getPackageName()));
                    if (i > 3) {
                        currentSet3.setVisibility(View.VISIBLE);
                        currentSet3.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(3).getCardName(), "drawable", getPackageName()));
                        if (i > 4) {
                            currentSet4.setVisibility(View.VISIBLE);
                            currentSet4.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(4).getCardName(), "drawable", getPackageName()));
                            if (i > 5) {
                                currentSet5.setVisibility(View.VISIBLE);
                                currentSet5.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(5).getCardName(), "drawable", getPackageName()));
                                if (i > 6) {
                                    currentSet6.setVisibility(View.VISIBLE);
                                    currentSet6.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(6).getCardName(), "drawable", getPackageName()));
                                    if (i > 7) {
                                        currentSet7.setVisibility(View.VISIBLE);
                                        currentSet7.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(7).getCardName(), "drawable", getPackageName()));
                                        if (i > 8) {
                                            currentSet8.setVisibility(View.VISIBLE);
                                            currentSet8.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(8).getCardName(), "drawable", getPackageName()));
                                            if (i > 9) {
                                                currentSet9.setVisibility(View.VISIBLE);
                                                currentSet9.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(9).getCardName(), "drawable", getPackageName()));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (i < 10) {
            currentSet9.setVisibility(View.INVISIBLE);
            if (i < 9) {
                currentSet8.setVisibility(View.INVISIBLE);
                if (i < 8) {
                    currentSet7.setVisibility(View.INVISIBLE);
                    if (i < 7) {
                        currentSet6.setVisibility(View.INVISIBLE);
                        if (i < 6) {
                            currentSet5.setVisibility(View.INVISIBLE);
                            if (i < 5) {
                                currentSet4.setVisibility(View.INVISIBLE);
                                if (i < 4) {
                                    currentSet3.setVisibility(View.INVISIBLE);
                                    if (i < 3) {
                                        currentSet2.setVisibility(View.INVISIBLE);
                                        if (i < 2) {
                                            currentSet1.setVisibility(View.INVISIBLE);
                                            if (i < 1) {
                                                currentSet0.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateOpponents() {
        int i = instance.getPlayers().size();
        if (i < 6) {
            fifthPlayer.setVisibility(View.INVISIBLE);
            if (i < 5) {
                fourthPlayer.setVisibility(View.INVISIBLE);
                if (i < 4) {
                    thirdPlayer.setVisibility(View.INVISIBLE);
                    if (i < 3) {
                        secondPlayer.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
        numCards1.setText(Integer.toString(instance.getPlayers().get(1).getHand().size()));
        if (i > 2) {
            numCards2.setText(Integer.toString(instance.getPlayers().get(2).getHand().size()));
            if (i > 3) {
                numCards3.setText(Integer.toString(instance.getPlayers().get(3).getHand().size()));
                if (i > 4) {
                    numCards4.setText(Integer.toString(instance.getPlayers().get(4).getHand().size()));
                    if (i > 5) {
                        numCards5.setText(Integer.toString(instance.getPlayers().get(5).getHand().size()));
                    }
                }
            }
        }
    }

    public void updateTurn() {
        String output = "Current Player: P" + Integer.toString(instance.getCurrentPlayer() + 1);
        currentPlayerText.setText(output);
    }

    public void updateScreen() {
        updateOpponents();
        updateCards();
        updateCurrentSet();
        updateTurn();
    }
}
