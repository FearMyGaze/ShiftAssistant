package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OWSLA extends AppCompatActivity {

    //General Information

    TextView OWSLAUserName , OWSLADateTime ;

    //Choices

    Button OWSLAWorkers , OWSLAAssistants , OWSLATeams , OWSLAPrograms , OWSLASettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla);

        //Handler's
        final Handler DateTime = new Handler(getMainLooper());

        //General Information

        OWSLAUserName = findViewById(R.id.OWSLAUserName);
        OWSLADateTime = findViewById(R.id.OWSLADateTime);

        //Choices buttons

        OWSLAPrograms = findViewById(R.id.OWSLAPrograms);
        OWSLATeams = findViewById(R.id.OWSLATeams);
        OWSLAAssistants = findViewById(R.id.OWSLAAssistants);
        OWSLAWorkers = findViewById(R.id.OWSLAWorkers);
        OWSLASettings = findViewById(R.id.OWSLASettings);

        //Getter's

        String UserName = getIntent().getStringExtra("DeadMau5");




        OWSLAUserName.setText(UserName);


        //ByPassers
        OWSLAWorkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity = new Intent(OWSLA.this, OWSLAWorker.class);
                startActivity(Activity);
            }
        });

        OWSLATeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity = new Intent(OWSLA.this, OWSLATeam.class);
                startActivity(Activity);
            }
        });

        OWSLAPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity = new Intent(OWSLA.this, OWSLAProgram.class);
                startActivity(Activity);
            }
        });

        OWSLAAssistants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity = new Intent(OWSLA.this, OWSLAAssistant.class);
                startActivity(Activity);
            }
        });
        OWSLASettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity = new Intent(OWSLA.this , OWSLASetting.class);
                startActivity(Activity);
            }
        });

        //Handler's
        DateTime.postDelayed(new Runnable() {
            @Override
            public void run() {
                OWSLADateTime.setText(new SimpleDateFormat("dd-MM-yyyy"+ "  " + "HH:mm:ss", Locale.getDefault()).format(new Date()));
                DateTime.postDelayed(this, 1000);
            }
        }, 10);
    }

}
