package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
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

import java.util.HashMap;
import java.util.Map;

public class OWSLATeam extends AppCompatActivity {

    ImageButton OWSLATeamPrevious , OWSLATeamNext , OWSLATeamRemove , OWSLATeamAdd;

    Button OWSLATeamSearchButton , OWSLATeamConfirm;

    EditText OWSLATeamSearchBox , OWSLATeamID , OWSLATeamName , OWSLATeamCapacity , OWSLATeamShiftStart , OWSLATeamShiftEnd;

    //Links
    private String CreateTeam , DeleteTeam , SearchTeam , NextPreviousTeam ;

    //Return
    String Switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla_team);

        GlobalVariables PHPLinks = new GlobalVariables();

        //Link's

        CreateTeam = PHPLinks.getRegisterTeams_URL();
        DeleteTeam = PHPLinks.getDeleteTeams_URL();
        SearchTeam = PHPLinks.getEmpty_URL();
        SearchTeam = PHPLinks.getEmpty_URL();

        //ImageButton's

        OWSLATeamPrevious = findViewById(R.id.OWSLATeamPrevious);
        OWSLATeamNext = findViewById(R.id.OWSLATeamNext);
        OWSLATeamRemove = findViewById(R.id.OWSLATeamRemove);
        OWSLATeamAdd = findViewById(R.id.OWSLATeamAdd);

        //Button's

        OWSLATeamSearchButton = findViewById(R.id.OWSLATeamSearchButton);
        OWSLATeamConfirm = findViewById(R.id.OWSLATeamConfirm);

        //EditText's

        OWSLATeamSearchBox = findViewById(R.id.OWSLATeamSearchBox);
        OWSLATeamID = findViewById(R.id.OWSLATeamID);
        OWSLATeamName = findViewById(R.id.OWSLATeamName);
        OWSLATeamCapacity = findViewById(R.id.OWSLATeamCapacity);
        OWSLATeamShiftStart = findViewById(R.id.OWSLATeamShiftStart);
        OWSLATeamShiftEnd = findViewById(R.id.OWSLATeamShiftEnd);


        NewTeams(CreateTeam,"1");

        //ClickListener's
        OWSLATeamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OWSLATeamConfirm.setText(R.string.OWSLATeamConfirmButtonADD);
                OWSLATeamConfirm.setVisibility(View.VISIBLE);
            }
        });
        OWSLATeamRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OWSLATeamConfirm.setText(R.string.OWSLATeamConfirmButtonDEL);
                OWSLATeamConfirm.setVisibility(View.VISIBLE);
            }
        });

        OWSLATeamConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTeams(CreateTeam,"0");
            }
        });
        OWSLATeamRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    protected void NewTeams(String CreateLink , final String Switcher){
        final String TeamName = this.OWSLATeamName.getText().toString().trim();
        final String TeamCapacity = this.OWSLATeamCapacity.getText().toString().trim();
        final String TeamShiftStart= this.OWSLATeamShiftStart.getText().toString().trim();
        final String TeamShiftEnd = this.OWSLATeamShiftEnd.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,CreateLink ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(Switcher.equals("1")){
                                String id = jsonObject.getString("ID");
                                OWSLATeamID.setText(String.valueOf(Integer.valueOf(id)+1));
                            }else{
                                if (success.equals("0")) {
                                    Toast.makeText(getApplicationContext(), "Creating team succeed.", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Failed creating team .", Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OWSLATeam.this, "Field trash." + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OWSLATeam.this, "Failed connection" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TeamName",TeamName);
                params.put("Capacity",TeamCapacity);
                params.put("Shift_Start",TeamShiftStart);
                params.put("Shift_End",TeamShiftEnd);
                params.put("Switcher" ,Switcher);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
