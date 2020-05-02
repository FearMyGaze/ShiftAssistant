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

    EditText WorkerDateLeave , WorkerDateVacation;

    TextView WorkerUserName , WorkerDateTime;

    ListView WorkerProgramListView;

    String LeavingDate;//We sent the date that the worker wants to leave

    String paramEmail;

    int WorkerDaysOff = 2;//Coming from different Class

    boolean Trust = true;

    private static final String File_Name = "Program.json";

    final String VAC_URL = "http://192.168.1.8/Shifts/Vacation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String UserName = getIntent().getStringExtra("DeadMauFive");
        final String UserEmail = getIntent().getStringExtra("TestPilot");
        final String shift_type = getIntent().getStringExtra("goat2");
        final String vacation_status = getIntent().getStringExtra("goat3");
        final String id = getIntent().getStringExtra("goat1");
        final String Teams_Code = getIntent().getStringExtra("goat4");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        //Button's
        WorkerDateVacation = findViewById(R.id.WorkerDateVacation);
        WorkerDateVacationConfirm = findViewById(R.id.WorkerDateVacationConfirm);
        WorkerDateLeaveConfirm = findViewById(R.id.WorkerDateLeaveConfirm);

        //TextView's

        WorkerUserName = findViewById(R.id.WorkerUserName);
        WorkerDateTime = findViewById(R.id.OWSLADateTime);

        //EditText's

        WorkerDateLeave = findViewById(R.id.WorkerDateLeave);

        //List View's

        WorkerProgramListView = findViewById(R.id.WorkerProgramListView);


        //ArrayList'sΗ


        ProgramGetterSetter test1 = new ProgramGetterSetter("Monday", shift_type,  Integer.valueOf(Teams_Code),  Integer.valueOf(id),Integer.valueOf(vacation_status));
        ProgramGetterSetter test2 = new ProgramGetterSetter("Tuesday", shift_type,  Integer.valueOf(Teams_Code), Integer.valueOf(id), Integer.valueOf(vacation_status));
        ProgramGetterSetter test3 = new ProgramGetterSetter("Wednesday", shift_type,  Integer.valueOf(Teams_Code),  Integer.valueOf(id),Integer.valueOf(vacation_status));
        ProgramGetterSetter test4 = new ProgramGetterSetter("Thursday", shift_type,  Integer.valueOf(Teams_Code),  Integer.valueOf(id),Integer.valueOf(vacation_status));
        ProgramGetterSetter test5 = new ProgramGetterSetter("Friday", shift_type,  Integer.valueOf(Teams_Code),  Integer.valueOf(id),Integer.valueOf(vacation_status));
        ProgramGetterSetter test6 = new ProgramGetterSetter("Saturday", shift_type,  Integer.valueOf(Teams_Code),  Integer.valueOf(id),Integer.valueOf(vacation_status));
        ProgramGetterSetter test7 = new ProgramGetterSetter("Sunday", shift_type,Integer.valueOf(Teams_Code),  Integer.valueOf(id),  Integer.valueOf(vacation_status));

//            System.out.println(vacation_status+id+Teams_Code+shift_type);
        ArrayList<ProgramGetterSetter> ProgramArrayList = new ArrayList<>();
        ProgramArrayList.add(test1);
        ProgramArrayList.add(test2);
        ProgramArrayList.add(test3);
        ProgramArrayList.add(test4);
        ProgramArrayList.add(test5);
        ProgramArrayList.add(test6);
        ProgramArrayList.add(test7);

        ProgramListAdapter adapter = new ProgramListAdapter(this,R.layout.adapter_worker_program,ProgramArrayList);
        WorkerProgramListView.setAdapter(adapter);


        TrustMe();



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

        WorkerDateVacationConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vacation(UserEmail);
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