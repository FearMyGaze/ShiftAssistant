package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Worker extends AppCompatActivity {

    Button WorkerDateLeaveConfirm;

    EditText WorkerDateLeave;

    TextView WorkerUserName , WorkerDateTime;

    ListView WorkerProgramListView;

    String LeavingDate;//We sent the date that the worker wants to leave

    int WorkerDaysOff = 2;//Coming from different Class

    boolean Trust = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        //Button's

        WorkerDateLeaveConfirm = findViewById(R.id.WorkerDateLeaveConfirm);

        //TextView's

        WorkerUserName = findViewById(R.id.WorkerUserName);
        WorkerDateTime = findViewById(R.id.OWSLADateTime);

        //EditText's

        WorkerDateLeave = findViewById(R.id.WorkerDateLeave);

        //List View's

        WorkerProgramListView = findViewById(R.id.WorkerProgramListView);

        //Handler's

        final Handler WorkerHandlerDateTime = new Handler(getMainLooper());

        //ArrayList's












        ProgramGetterSetter test1 = new ProgramGetterSetter("test1","2",3,4,5);
        ProgramGetterSetter test2 = new ProgramGetterSetter("test2","2",3,4,5);
        ProgramGetterSetter test3 = new ProgramGetterSetter("test3","2",3,4,5);
        ProgramGetterSetter test4 = new ProgramGetterSetter("test4","2",3,4,5);
        ProgramGetterSetter test5 = new ProgramGetterSetter("test5","2",3,4,5);
        ProgramGetterSetter test6 = new ProgramGetterSetter("test6","2",3,4,5);
        ProgramGetterSetter test7 = new ProgramGetterSetter("test7","2",3,4,5);
        ProgramGetterSetter test8 = new ProgramGetterSetter("test8","2",3,4,5);


        ArrayList<ProgramGetterSetter> ProgramArrayList = new ArrayList<>();
        ProgramArrayList.add(test1);
        ProgramArrayList.add(test2);
        ProgramArrayList.add(test3);
        ProgramArrayList.add(test4);
        ProgramArrayList.add(test5);
        ProgramArrayList.add(test6);
        ProgramArrayList.add(test7);
        ProgramArrayList.add(test8);

        ProgramListAdapter adapter = new ProgramListAdapter(this,R.layout.adapter_worker_program,ProgramArrayList);
        WorkerProgramListView.setAdapter(adapter);


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



        //Handler's

        WorkerHandlerDateTime.postDelayed(new Runnable() {
            @Override
            public void run() {
                WorkerDateTime.setText(new SimpleDateFormat("dd-MM-yyyy"+ "  " + "HH:mm:ss", Locale.getDefault()).format(new Date()));
                WorkerHandlerDateTime.postDelayed(this, 1000);
            }
        },10);

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