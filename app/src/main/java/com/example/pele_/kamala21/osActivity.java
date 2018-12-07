package com.example.pele_.kamala21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class osActivity extends Activity implements  View.OnClickListener{
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Creates optionScreen Button in the GUI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rmpm_options_screen);

        returnButton = (Button)findViewById(R.id.osReturnButton);
        returnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        //Opens OptionScreen and closes mainmenu
        switch (v.getId()){
            case R.id.osReturnButton:
                Intent mmIntent = new Intent(this, mmActivity.class);
                startActivity(mmIntent);
                finish();
                break;
            default:
                break;
        }
    }
}