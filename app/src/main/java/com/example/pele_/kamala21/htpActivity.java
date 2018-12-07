package com.example.pele_.kamala21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class htpActivity extends Activity implements  View.OnClickListener{
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //creates a How to Play button in the GUI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmpm_how_to_play);

        returnButton = (Button)findViewById(R.id.htpReturnButton);
        returnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        //exits the current activity and goes to the htpActivity
        switch (v.getId()){
            case R.id.htpReturnButton:
                Intent mmIntent = new Intent(this, mmActivity.class);
                startActivity(mmIntent);
                System.exit(0);
                //looked at https://developer.android.com/training/basics/firstapp/starting-activity for help
            default:
                break;
        }
    }
}
