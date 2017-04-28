package com.example.lucktest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Test extends AppCompatActivity {

    int startOfRange;
    int endOfRange;
    String aim;
    Random rand;
    Date dtNow;
    String result;
    int diff;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        preferences = getSharedPreferences("data", Context.MODE_PRIVATE);

        startOfRange = preferences.getInt("StartOfRange", 0 );
        diff = preferences.getInt("Difference", 0 );

        endOfRange = startOfRange+diff;
         aim = startOfRange + " - " + endOfRange; // NOTE: IT MAY EXPLODE! It did not.

         TextView aimT = (TextView) findViewById(R.id.aimTV);
         aimT.setText("Aim between those numbers : "+ aim);

        Button generateNum = (Button) findViewById(R.id.generateNum);
        Button detailsBtn = (Button) findViewById(R.id.detailsBtn);
        Button newBnt = (Button) findViewById(R.id.newBtn);
        generateNum.setVisibility(View.VISIBLE); //To set visible
        newBnt.setVisibility(View.INVISIBLE);
        detailsBtn.setVisibility(View.INVISIBLE);
    }

   public void generateNum(View view)
   {
       preferences = getSharedPreferences("data", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = preferences.edit();

       dtNow= new Date();
       Date today = new Date();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       String d= (dateFormat.format(today));
       rand = new Random();
       int luckyN = rand.nextInt((100 - startOfRange) + 1) + startOfRange;

      TextView currentLuckyN = (TextView) findViewById(R.id.currentLuckyN);
      currentLuckyN.setText("Your number is: "+ luckyN);
       TextView resultValue = (TextView) findViewById(R.id.resultValue);

      if (luckyN>=startOfRange && luckyN<=endOfRange)
      {
            result = "lucky";
           resultValue.setText("You are "+result+" today!");
       }
       else{
           result="no luck. Try again!";
           resultValue.setText("You have "+ result);
       }

       editor.putString("Date",d);
       editor.putInt("Number", luckyN);
       editor.putString("Result", result);
       editor.apply();

       Button generateNum = (Button) findViewById(R.id.generateNum);
       Button newBnt = (Button) findViewById(R.id.newBtn);
       Button detailsBtn = (Button) findViewById(R.id.detailsBtn);

       generateNum.setVisibility(View.INVISIBLE);
       newBnt.setVisibility(View.VISIBLE);
       detailsBtn.setVisibility(View.VISIBLE);
   }

    public void GoToHome(View view) {
        Intent intent = new Intent(Test.this, Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void ShowDetails(View view) {
        Intent intent = new Intent(Test.this, Details.class);
        startActivity(intent);
    }
}
