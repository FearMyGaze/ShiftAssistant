package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class Worker extends AppCompatActivity {

    Button WorkerDateLeaveConfirm;
    EditText WorkerDateLeave;
    TextView WorkerProgramVisualizer;

    int WorkerDaysOff = 2;//Coming from different Class
    String LeavingDate;//We sent the date that the worker wants to leave
    boolean Trust = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        WorkerDateLeave = findViewById(R.id.WorkerDateLeave);
        WorkerDateLeaveConfirm = findViewById(R.id.WorkerDateLeaveConfirm);
        WorkerProgramVisualizer = findViewById(R.id.WorkerProgramVisualizer);
        TrustMe();

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
                        System.out.println("---___---"+WorkerDaysOff);
                    }else{

                    }

                }
            }
        });

        WorkerProgramVisualizer.setText("");

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