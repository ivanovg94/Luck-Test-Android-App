package com.example.lucktest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import java.util.Random;

public class Main extends AppCompatActivity {

    int diff = 0;
    String difficulty="";
    SharedPreferences preferences;
    Button lastTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        lastTestBtn = (Button) findViewById(R.id.lastTestBtn);
        String value = preferences.getString("Result",null);
        if (value == null) {
            lastTestBtn.setVisibility(View.INVISIBLE);
        } else {
            lastTestBtn.setVisibility(View.VISIBLE);
        }

        diff=30;
        difficulty="Easy";
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked() ;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.opt1:
                if (checked)
                diff=30;
                difficulty="Easy";
                break;
            case R.id.opt2:
                if (checked)
                   diff=20;
                difficulty="Medium";
                break;
            case R.id.opt3:
                if (checked)
                    diff=10;
                difficulty="Hard";
                break;
        }
    }

   public void startTest(View view) {

       lastTestBtn = (Button) findViewById(R.id.lastTestBtn);
       lastTestBtn.setVisibility(View.INVISIBLE);

       preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = preferences.edit();
       editor.clear();
       editor.apply();

       Random rand = new Random();
       int startOfRange = rand.nextInt(100-diff);

       editor.putInt("StartOfRange",startOfRange);
       editor.putInt("Difference",diff);
       editor.putString("Difficulty",difficulty);
       editor.apply();

        Intent intent = new Intent(this, Test.class);
       startActivity(intent);
   }


    public void GoToResults(View view) {
        Intent intent = new Intent(this, Details.class);
        startActivity(intent);
    }
}

