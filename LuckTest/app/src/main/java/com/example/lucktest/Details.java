package com.example.lucktest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        SharedPreferences preferences;

        preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        int startOfRange = preferences.getInt("StartOfRange", 0 );
        int diff = preferences.getInt("Difference", 0 );
        int endOfRange = startOfRange+diff;
        int luckyNum = preferences.getInt("Number", 0 );
        String result = preferences.getString("Result", "" );
        String date = preferences.getString("Date", "");
        String difficulty = preferences.getString("Difficulty", "");

        TextView dateValue = (TextView) findViewById(R.id.dateValue);
        dateValue.setText(date);

        TextView difficultyValue = (TextView) findViewById(R.id.difficultyValue);
        difficultyValue.setText(difficulty);

        TextView startOfRangeValue = (TextView) findViewById(R.id.startOfRangeValue);
        startOfRangeValue.setText(Integer.toString(startOfRange));

        TextView endOfRangeValue = (TextView) findViewById(R.id.endOfRangeValue);
        endOfRangeValue.setText(Integer.toString(endOfRange));

        TextView numberValue = (TextView) findViewById(R.id.numberValue);
        numberValue.setText(Integer.toString(luckyNum));

        TextView resultValue = (TextView) findViewById(R.id.resultValue);
        resultValue.setText(result);
    }

    public void GoToHome(View view) {
        Intent intent = new Intent(Details.this, Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
