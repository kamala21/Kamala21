package com.example.pele_.kamala21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class spgsActivity extends Activity implements View.OnClickListener, ValueEventListener {
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
    String intelligence;
    int serverID;

    View leaderBoard;
    View player3;
    View player4;
    View player5;
    View player6;
    TextView wins1;
    TextView wins2;
    TextView wins3;
    TextView wins4;
    TextView wins5;
    TextView wins6;
    TextView standing1;
    TextView standing2;
    TextView standing3;
    TextView standing4;
    TextView standing5;
    TextView standing6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmpm_game_screen);

        serverID = getIntent().getIntExtra("serverID", -1);
        playerIndex = 0;
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
        gameStateRef = database.getReference().child("singlePlayerGameState" + Integer.toString(serverID));
        lobbyRef = database.getReference().child("singlePlayerLobby" + Integer.toString(serverID));
        lobbyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                intelligence = dataSnapshot.child("intelligence").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        gameStateRef.addValueEventListener(this);
        leaderBoard = (View) findViewById(R.id.leaderBoardLayout);
        player3 = (View) findViewById(R.id.infoP3);
        player4 = (View) findViewById(R.id.infoP4);
        player5 = (View) findViewById(R.id.infoP5);
        player6 = (View) findViewById(R.id.infoP6);
        wins1 = (TextView) findViewById(R.id.wins1);
        wins2 = (TextView) findViewById(R.id.wins2);
        wins3 = (TextView) findViewById(R.id.wins3);
        wins4 = (TextView) findViewById(R.id.wins4);
        wins5 = (TextView) findViewById(R.id.wins5);
        wins6 = (TextView) findViewById(R.id.wins6);
        standing1 = (TextView) findViewById(R.id.standing1);
        standing2 = (TextView) findViewById(R.id.standing2);
        standing3 = (TextView) findViewById(R.id.standing3);
        standing4 = (TextView) findViewById(R.id.standing4);
        standing5 = (TextView) findViewById(R.id.standing5);
        standing6 = (TextView) findViewById(R.id.standing6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.leaderboard:
                if (leaderBoard.getVisibility() == View.GONE) {
                    leaderBoard.setVisibility(View.VISIBLE);
                } else {
                    leaderBoard.setVisibility(View.GONE);
                }
                return true;
            case R.id.leaveGame:
                gameStateRef.setValue(null);
                lobbyRef.setValue(null);
                Intent mmIntent = new Intent(this, mmActivity.class);
                startActivity(mmIntent);
                System.exit(0);
                return true;
            case R.id.exitApp:
                gameStateRef.setValue(null);
                lobbyRef.setValue(null);
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        instance = dataSnapshot.getValue(RmPmGameState.class);
        if (instance != null) {
            updateScreen();
            if (instance.playersWithCards() < 2) {
                Toast.makeText(getApplication().getApplicationContext(), "Round done, starting next round",
                        Toast.LENGTH_SHORT).show();
                updateLeaderboard();
                instance.reDeal();
                instance.getCurrentSet().clear();
                RmPmGameState updatedInstance = new RmPmGameState(instance);
                gameStateRef.setValue(updatedInstance);
            } else if (instance.getCurrentPlayer() != playerIndex) {
                try
                {
                    Thread.sleep(750);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                if(intelligence != null) {
                    if (intelligence.equals("smart")) {
                        instance.smartAi(instance.getCurrentPlayer());
                    } else {
                        instance.dumbAi(instance.getCurrentPlayer());
                    }
                } else{
                    instance.smartAi(instance.getCurrentPlayer());
                }
                RmPmGameState updatedInstance = new RmPmGameState(instance);
                gameStateRef.setValue(updatedInstance);
            } //skips over ai
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

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
            card0.setVisibility(View.GONE);
            card0.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(0).getCardName(), "drawable", getPackageName()));
            card0.setVisibility(View.VISIBLE);
            if (i > 1) {
                card1.setVisibility(View.GONE);
                card1.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(1).getCardName(), "drawable", getPackageName()));
                card1.setVisibility(View.VISIBLE);
                if (i > 2) {
                    card2.setVisibility(View.GONE);
                    card2.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(2).getCardName(), "drawable", getPackageName()));
                    card2.setVisibility(View.VISIBLE);
                    if (i > 3) {
                        card3.setVisibility(View.GONE);
                        card3.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(3).getCardName(), "drawable", getPackageName()));
                        card3.setVisibility(View.VISIBLE);
                        if (i > 4) {
                            card4.setVisibility(View.GONE);
                            card4.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(4).getCardName(), "drawable", getPackageName()));
                            card4.setVisibility(View.VISIBLE);
                            if (i > 5) {
                                card5.setVisibility(View.GONE);
                                card5.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(5).getCardName(), "drawable", getPackageName()));
                                card5.setVisibility(View.VISIBLE);
                                if (i > 6) {
                                    card6.setVisibility(View.GONE);
                                    card6.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(6).getCardName(), "drawable", getPackageName()));
                                    card6.setVisibility(View.VISIBLE);
                                    if (i > 7) {
                                        card7.setVisibility(View.GONE);
                                        card7.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(7).getCardName(), "drawable", getPackageName()));
                                        card7.setVisibility(View.VISIBLE);
                                        if (i > 8) {
                                            card8.setVisibility(View.GONE);
                                            card8.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(8).getCardName(), "drawable", getPackageName()));
                                            card8.setVisibility(View.VISIBLE);
                                            if (i > 9) {
                                                card9.setVisibility(View.GONE);
                                                card9.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(9).getCardName(), "drawable", getPackageName()));
                                                card9.setVisibility(View.VISIBLE);
                                                if (i > 10) {
                                                    card10.setVisibility(View.GONE);
                                                    card10.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(10).getCardName(), "drawable", getPackageName()));
                                                    card10.setVisibility(View.VISIBLE);
                                                    if (i > 11) {
                                                        card11.setVisibility(View.GONE);
                                                        card11.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(11).getCardName(), "drawable", getPackageName()));
                                                        card11.setVisibility(View.VISIBLE);
                                                        if (i > 12) {
                                                            card12.setVisibility(View.GONE);
                                                            card12.setImageResource(getResources().getIdentifier(instance.getPlayers().get(playerIndex).getHand().get(12).getCardName(), "drawable", getPackageName()));
                                                            card12.setVisibility(View.VISIBLE);
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
            card12.setVisibility(View.GONE);
            if (i < 12) {
                card11.setVisibility(View.GONE);
                if (i < 11) {
                    card10.setVisibility(View.GONE);
                    if (i < 10) {
                        card9.setVisibility(View.GONE);
                        if (i < 9) {
                            card8.setVisibility(View.GONE);
                            if (i < 8) {
                                card7.setVisibility(View.GONE);
                                if (i < 7) {
                                    card6.setVisibility(View.GONE);
                                    if (i < 6) {
                                        card5.setVisibility(View.GONE);
                                        if (i < 5) {
                                            card4.setVisibility(View.GONE);
                                            if (i < 4) {
                                                card3.setVisibility(View.GONE);
                                                if (i < 3) {
                                                    card2.setVisibility(View.GONE);
                                                    if (i < 2) {
                                                        card1.setVisibility(View.GONE);
                                                        if (i < 1) {
                                                            card0.setVisibility(View.GONE);
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
            currentSet0.setVisibility(View.GONE);
            currentSet0.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(0).getCardName(), "drawable", getPackageName()));
            currentSet0.setVisibility(View.VISIBLE);
            if (i > 1) {
                currentSet1.setVisibility(View.GONE);
                currentSet1.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(1).getCardName(), "drawable", getPackageName()));
                currentSet1.setVisibility(View.VISIBLE);
                if (i > 2) {
                    currentSet2.setVisibility(View.GONE);
                    currentSet2.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(2).getCardName(), "drawable", getPackageName()));
                    currentSet2.setVisibility(View.VISIBLE);
                    if (i > 3) {
                        currentSet3.setVisibility(View.GONE);
                        currentSet3.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(3).getCardName(), "drawable", getPackageName()));
                        currentSet3.setVisibility(View.VISIBLE);
                        if (i > 4) {
                            currentSet4.setVisibility(View.GONE);
                            currentSet4.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(4).getCardName(), "drawable", getPackageName()));
                            currentSet4.setVisibility(View.VISIBLE);
                            if (i > 5) {
                                currentSet5.setVisibility(View.GONE);
                                currentSet5.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(5).getCardName(), "drawable", getPackageName()));
                                currentSet5.setVisibility(View.VISIBLE);
                                if (i > 6) {
                                    currentSet6.setVisibility(View.GONE);
                                    currentSet6.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(6).getCardName(), "drawable", getPackageName()));
                                    currentSet6.setVisibility(View.VISIBLE);
                                    if (i > 7) {
                                        currentSet7.setVisibility(View.GONE);
                                        currentSet7.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(7).getCardName(), "drawable", getPackageName()));
                                        currentSet7.setVisibility(View.VISIBLE);
                                        if (i > 8) {
                                            currentSet8.setVisibility(View.GONE);
                                            currentSet8.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(8).getCardName(), "drawable", getPackageName()));
                                            currentSet8.setVisibility(View.VISIBLE);
                                            if (i > 9) {
                                                currentSet9.setVisibility(View.GONE);
                                                currentSet9.setImageResource(getResources().getIdentifier(instance.getCurrentSet().get(9).getCardName(), "drawable", getPackageName()));
                                                currentSet9.setVisibility(View.VISIBLE);
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
            currentSet9.setVisibility(View.GONE);
            if (i < 9) {
                currentSet8.setVisibility(View.GONE);
                if (i < 8) {
                    currentSet7.setVisibility(View.GONE);
                    if (i < 7) {
                        currentSet6.setVisibility(View.GONE);
                        if (i < 6) {
                            currentSet5.setVisibility(View.GONE);
                            if (i < 5) {
                                currentSet4.setVisibility(View.GONE);
                                if (i < 4) {
                                    currentSet3.setVisibility(View.GONE);
                                    if (i < 3) {
                                        currentSet2.setVisibility(View.GONE);
                                        if (i < 2) {
                                            currentSet1.setVisibility(View.GONE);
                                            if (i < 1) {
                                                currentSet0.setVisibility(View.GONE);
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

    public void updateLeaderboard() {
        int i = instance.getPlayers().size();
        if (i < 6) {
            player6.setVisibility(View.GONE);
            if (i < 5) {
                player5.setVisibility(View.GONE);
                if (i < 4) {
                    player4.setVisibility(View.GONE);
                    if (i < 3) {
                        player3.setVisibility(View.GONE);
                    }
                }
            }
        }
        wins1.setText(Integer.toString(instance.getPlayers().get(0).getWins()));
        wins2.setText(Integer.toString(instance.getPlayers().get(1).getWins()));
        standing1.setText("Middle Class");
        standing2.setText("Middle Class");
        if (i > 2) {
            wins3.setText(Integer.toString(instance.getPlayers().get(2).getWins()));
            standing3.setText("Middle Class");
            if (i > 3) {
                wins4.setText(Integer.toString(instance.getPlayers().get(3).getWins()));
                standing4.setText("Middle Class");
                if (i > 4) {
                    wins5.setText(Integer.toString(instance.getPlayers().get(4).getWins()));
                    standing5.setText("Middle Class");
                    if (i > 5) {
                        wins6.setText(Integer.toString(instance.getPlayers().get(5).getWins()));
                        standing6.setText("Middle Class");
                    }
                }
            }
        }
        for (int j = 0; j < i; j++) {
            if (instance.getPlayers().get(j).getStanding() == i-2) {
                if (j == 0) {
                    standing1.setText("Rich Man");
                } else if (j == 1) {
                    standing2.setText("Rich Man");
                } else if (j == 2) {
                    standing3.setText("Rich Man");
                } else if (j == 3) {
                    standing4.setText("Rich Man");
                } else if (j == 4) {
                    standing5.setText("Rich Man");
                } else if (j == 5) {
                    standing6.setText("Rich Man");
                }
            } else if (instance.getPlayers().get(j).getStanding() == -1) {
                if (j == 0) {
                    standing1.setText("Poor Man");
                } else if (j == 1) {
                    standing2.setText("Poor Man");
                } else if (j == 2) {
                    standing3.setText("Poor Man");
                } else if (j == 3) {
                    standing4.setText("Poor Man");
                } else if (j == 4) {
                    standing5.setText("Poor Man");
                } else if (j == 5) {
                    standing6.setText("Poor Man");
                }
            }
        }
    }

    public void updateScreen() {
        updateOpponents();
        updateCards();
        updateCurrentSet();
        updateTurn();
    }
}
