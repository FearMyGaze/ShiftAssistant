package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OWSLA extends AppCompatActivity {

    //General Information

    TextView OWSLAUserName , OWSLADateTime ;

    //Choices

    Button OWSLAWorkers , OWSLAAssistants , OWSLATeams , OWSLAPrograms , OWSLASettings;

    //Links
    String FetchEmployees_URL;

    GlobalVariables Links;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla);

        Links = new GlobalVariables();

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

        //Links
        FetchEmployees_URL = Links.getFetchEmployees_URL();

        //Intend's

        final String UserName = getIntent().getStringExtra("DeadMau5");
        final String UserEmail = getIntent().getStringExtra("GOATdm5");

        OWSLAUserName.setText(UserName);

        EndVacation();
        FetchEmployeesData(UserEmail);

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
                Activity.putExtra("DeadMau5", UserName);
                Activity.putExtra("GOATdm5",UserEmail);
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
    private void FetchEmployeesData(final String Email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, FetchEmployees_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.isEmpty()){
                            Toast.makeText(getApplicationContext(),
                                    "This account does not exist.", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                if (success.equals("1")) {
                                    FileOutputStream fos = null;
                                    JSONObject object = new JSONObject();
                                    try
                                    {
                                        fos = openFileOutput(Links.getFileEmployees(),MODE_PRIVATE);
                                        try {
                                            object.put("Employees",jsonObject.get("Employees"));
                                            fos.write(object.toString().getBytes());
                                            fos.flush();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                    catch(FileNotFoundException e){
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    finally {
                                        if (fos != null){
                                            try {
                                                fos.close();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),
                                            "There are no records in the Database", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "An Error came through" +e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Failed connection" +error.toString(), Toast.LENGTH_SHORT).show();

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

    public void EndVacation(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Links.getEndVacation_URL(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Vacation status updated", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "There are no employees", Toast.LENGTH_LONG).show();
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
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}

