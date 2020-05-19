package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    int size = 0;


    //Link's
    GlobalVariables Links;

    private String VAC_URL;

    //File's

    private static final String File_Name = "Program.json";

    String ID,TeamsCode,Email,Shift,VacationStatus,Name;

    //ProgramSetters

    ArrayList<ProgramGetterSetter> Days = new ArrayList<>();

    ProgramGetterSetter Monday = new ProgramGetterSetter("Monday", Shift, Name, TeamsCode, VacationStatus);
    ProgramGetterSetter Tuesday = new ProgramGetterSetter("Tuesday", Shift, Name, TeamsCode, VacationStatus);
    ProgramGetterSetter Wednesday = new ProgramGetterSetter("Wednesday", Shift, Name, TeamsCode, VacationStatus);
    ProgramGetterSetter Thursday = new ProgramGetterSetter("Thursday", Shift, Name, TeamsCode, VacationStatus);
    ProgramGetterSetter Friday = new ProgramGetterSetter("Friday", Shift, Name, TeamsCode, VacationStatus);
    ProgramGetterSetter Saturday = new ProgramGetterSetter("Saturday", Shift, Name, TeamsCode, VacationStatus);
    ProgramGetterSetter Sunday = new ProgramGetterSetter("Sunday", Shift, Name, TeamsCode, VacationStatus);


    ArrayList<ProgramGetterSetter> ProgramArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ID = getIntent().getStringExtra("id");
        TeamsCode = getIntent().getStringExtra("Teams_Code");
        Email = getIntent().getStringExtra("email");
        Shift = getIntent().getStringExtra("ShiftType");
        VacationStatus = getIntent().getStringExtra("vacation_status");
        Name = getIntent().getStringExtra("Name");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        Links = new GlobalVariables();


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
        WorkerUserName.setText(Email);

        //ArrayList's
        ProgramArrayList = new ArrayList<>();
        TrustMe();

        if(!VacationStatus.equals("1")) {
            ShowProgram();
        } else {
            Toast.makeText(getApplicationContext(), "Take a good rest because the next week he is coming for you ;)", Toast.LENGTH_SHORT).show();
        }

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
            public void onClick(View v) { Vacation(Email);
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Links.getDownloadProgramFromDB_URL(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            ArrayList<JSONObject> Shifts = new ArrayList<>();
                            if (success.equals("0")) {

                                for(int i = 0 ; i < jsonObject.getJSONArray("Shifts").length(); i++){
                                    Shifts.add(jsonObject.getJSONArray("Shifts").getJSONObject(i));
                                }

                                for(int i = 0 ; i < Shifts.size(); i++){
                                    if(Shifts.get(i).get("DayOfTheWeek").toString().equals("Monday")){
                                        Days.add(Monday);
                                    } else if(Shifts.get(i).get("DayOfTheWeek").toString().equals("Tuesday")){
                                        Days.add(Tuesday);
                                    } else if(Shifts.get(i).get("DayOfTheWeek").toString().equals("Wednesday")){
                                        Days.add(Wednesday);
                                    } else if(Shifts.get(i).get("DayOfTheWeek").toString().equals("Thursday")){
                                        Days.add(Thursday);
                                    } else if(Shifts.get(i).get("DayOfTheWeek").toString().equals("Friday")){
                                        Days.add(Friday);
                                    } else if(Shifts.get(i).get("DayOfTheWeek").toString().equals("Saturday")){
                                        Days.add(Saturday);
                                    } else {
                                        Days.add(Sunday);
                                    }
                                }

                                for(int i = 0 ; i < Days.size(); i++){
                                    Days.get(i).setDate(Shifts.get(i).get("DayOfTheWeek").toString());
                                    Days.get(i).setShiftID(Shifts.get(i).get("Shift").toString());
                                    Days.get(i).setWorkerID(Name);
                                    Days.get(i).setTilVAC(VacationStatus);
                                    Days.get(i).setTeamID(TeamsCode);
                                }

                                for(int i = 0 ; i < Days.size(); i++) {
                                    ProgramArrayList.add(Days.get(i));
                                }

                                ProgramListAdapter adapter = new ProgramListAdapter(getApplicationContext(),R.layout.adapter_worker_program,ProgramArrayList);
                                WorkerProgramListView.setAdapter(adapter);

                                Toast.makeText(getApplicationContext(), "Program downloaded successfully", Toast.LENGTH_LONG).show();

                            }
                            else if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Failed to download the weekly program", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Field stauros." + e.toString(), Toast.LENGTH_LONG).show();
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
                params.put("ID",ID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}