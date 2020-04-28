package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Worker extends AppCompatActivity {

    Button WorkerDateLeaveConfirm;

    EditText WorkerDateLeave;

    TextView WorkerProgramVisualizer , WorkerUserName , WorkerDateTime;

    int WorkerDaysOff = 2;//Coming from different Class
    String LeavingDate;//We sent the date that the worker wants to leave
    boolean Trust = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        //Handler's
        final Handler WorkerHandlerDateTime = new Handler(getMainLooper());

        //Button's

        WorkerDateLeaveConfirm = findViewById(R.id.WorkerDateLeaveConfirm);

        //TextView's

        WorkerUserName = findViewById(R.id.WorkerUserName);
        WorkerDateTime = findViewById(R.id.OWSLADateTime);
        WorkerProgramVisualizer = findViewById(R.id.WorkerProgramVisualizer);

        //EditText's

        WorkerDateLeave = findViewById(R.id.WorkerDateLeave);

        TrustMe();

        String UserName = getIntent().getStringExtra("DeadMauFive");
        WorkerUserName.setText(UserName);


        WorkerDateLeaveConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CheckButton = WorkerDateLeave.getText().toString().trim();
                if (CheckButton.isEmpty()){
                    WorkerDateLeave.setError("Please fill the field");
                }else{
                    if(WorkerDaysOff >= 1){
                        LeavingDate = WorkerDateLeave.getText().toString().trim();
                        System.out.println(LeavingDate);
                        WorkerDaysOff = WorkerDaysOff - 1;
                        TrustMe();
                        System.out.println("---___---"+WorkerDaysOff);
                    }else{
                        TrustMe();
                    }

                }
            }
        });

        WorkerProgramVisualizer.setText("");




        //Handler's
/*
        WorkerHandlerDateTime.postDelayed(new Runnable() {
            @Override
            public void run() {
                WorkerDateTime.setText(new SimpleDateFormat("dd-MM-yyyy" + " " + "HH:mm:ss",Locale.getDefault()).format(new Date()));
                WorkerHandlerDateTime.postDelayed(this,1000);
            }
        },10);
*/
    }

    private void TrustMe(){
        int ChangeString;
        ChangeString = WorkerDaysOff;
       if(Trust && ChangeString >= 1){
           WorkerDateLeaveConfirm.setText(R.string.WorkerPaidLeave);
       }else if(ChangeString == 0 && Trust){
            WorkerDateLeaveConfirm.setText(R.string.WorkerUnPaidLeave);
       }else if(!Trust){
           WorkerDateLeaveConfirm.setText(R.string.WorkerLeave);
       }

    }
}