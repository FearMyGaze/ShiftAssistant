package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Worker extends AppCompatActivity {

    Button WorkerDateLeaveConfirm , WorkerDateVacationConfirm;

    EditText WorkerDateLeave;

    TextView WorkerUserName , WorkerDateTime;

    ListView WorkerProgramListView;

    //Variable's

    String LeavingDate;//We sent the date that the worker wants to leave

    String paramEmail;

    int WorkerDaysOff = 2;//Coming from different Class

    boolean Trust = true;



    //Link's

    private String VAC_URL;

    //File's

    private static final String File_Name = "Program.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String UserName = getIntent().getStringExtra("DeadMauFive");
        final String UserEmail = getIntent().getStringExtra("TestPilot");
        final String shift_type = getIntent().getStringExtra("goat2");
        final String vacation_status = getIntent().getStringExtra("goat3");
        final String Name = getIntent().getStringExtra("goat1");
        final String Teams_Code = getIntent().getStringExtra("goat4");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        GlobalVariables Links = new GlobalVariables();


        //Button's

        WorkerDateVacationConfirm = findViewById(R.id.WorkerDateVacationConfirm);
        WorkerDateLeaveConfirm = findViewById(R.id.WorkerDateLeaveConfirm);

        //TextView's

        WorkerUserName = findViewById(R.id.WorkerUserName);
        WorkerDateTime = findViewById(R.id.OWSLADateTime);

        //EditText's

        WorkerDateLeave = findViewById(R.id.WorkerDateLeave);

        //List View's

        WorkerProgramListView = findViewById(R.id.WorkerProgramListView);

        //Links
        VAC_URL = Links.getVacation_URL();

        //Worker name on the TopBar
        WorkerUserName.setText(UserName);

        //ArrayList's


        ProgramGetterSetter Monday = new ProgramGetterSetter("Monday", shift_type , Name , Integer.valueOf(Teams_Code),Integer.valueOf(vacation_status));
        ProgramGetterSetter Tuesday = new ProgramGetterSetter("Tuesday", shift_type, Name , Integer.valueOf(Teams_Code),Integer.valueOf(vacation_status));
        ProgramGetterSetter Wednesday = new ProgramGetterSetter("Wednesday", shift_type, Name , Integer.valueOf(Teams_Code),Integer.valueOf(vacation_status));
        ProgramGetterSetter Thursday = new ProgramGetterSetter("Thursday", shift_type, Name , Integer.valueOf(Teams_Code),Integer.valueOf(vacation_status));
        ProgramGetterSetter Friday = new ProgramGetterSetter("Friday", shift_type, Name , Integer.valueOf(Teams_Code),Integer.valueOf(vacation_status));
        ProgramGetterSetter Saturday = new ProgramGetterSetter("Saturday", shift_type, Name , Integer.valueOf(Teams_Code),Integer.valueOf(vacation_status));
        ProgramGetterSetter Sunday = new ProgramGetterSetter("Sunday", shift_type, Name , Integer.valueOf(Teams_Code),Integer.valueOf(vacation_status));

//            System.out.println(vacation_status+Name+Teams_Code+shift_type);
        ArrayList<ProgramGetterSetter> ProgramArrayList = new ArrayList<>();
        ProgramArrayList.add(Monday);
        ProgramArrayList.add(Tuesday);
        ProgramArrayList.add(Wednesday);
        ProgramArrayList.add(Thursday);
        ProgramArrayList.add(Friday);
        ProgramArrayList.add(Saturday);
        ProgramArrayList.add(Sunday);

        ProgramListAdapter adapter = new ProgramListAdapter(this,R.layout.adapter_worker_program,ProgramArrayList);
        WorkerProgramListView.setAdapter(adapter);


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
                        TrustMe();
                        System.out.println("---___---"+WorkerDaysOff);
                    }else{
                        TrustMe();
                    }

                }
            }
        });

        WorkerDateVacationConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Vacation(UserEmail);
            }
        });

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

    private void Vacation(final String Email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  VAC_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String difference = jsonObject.getString("difference");
                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Vacation request accepted.", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Vacation request rejected.", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Field trash." + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Failed connection" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Email",Email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void ShowProgram(){
        String json;
        try {
            InputStream is = openFileInput(File_Name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();



            json = new String(buffer,"UTF-8");
            JSONObject object = new JSONObject(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}