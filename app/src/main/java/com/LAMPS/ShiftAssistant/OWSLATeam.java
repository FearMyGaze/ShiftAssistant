package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private String CreateTeam , DeleteTeam , SearchTeam , NextTeam , PreviousTeam;

    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla_team);

        GlobalVariables PHPLinks = new GlobalVariables();

        //Link's

        CreateTeam = PHPLinks.getNewTeams_URL();
        DeleteTeam = PHPLinks.getDeleteTeams_URL();
        SearchTeam = PHPLinks.getSearchTeams_URL();
        NextTeam = PHPLinks.getNextTeam_URL();
        PreviousTeam = PHPLinks.getPreviousTeam_URL();

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

        //Method's

        NewTeams(CreateTeam,"3");

        //ClickListener's
        OWSLATeamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OWSLATeamName.setText(" ");
                OWSLATeamCapacity.setText(" ");
                OWSLATeamShiftStart.setText(" ");
                OWSLATeamShiftEnd.setText(" ");
                NewTeams(CreateTeam,"1");
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
                NewTeams(CreateTeam,"2");
            }
        });

        OWSLATeamRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelTeams();
            }
        });

        OWSLATeamSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { SearchTeam(SearchTeam);
            }
        });

        OWSLATeamNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextTeam(NextTeam);
            }
        });

        OWSLATeamPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrevTeam(PreviousTeam);
            }
        });

    }

    private void setID(String id){
        this.ID = id;
    }

    protected void NewTeams(String CreateLink , final String Switcher){
        final String TeamName = this.OWSLATeamName.getText().toString().trim();
        final String TeamCapacity = this.OWSLATeamCapacity.getText().toString().trim();
        final String TeamShiftStart= this.OWSLATeamShiftStart.getText().toString().trim();
        final String TeamShiftEnd = this.OWSLATeamShiftEnd.getText().toString().trim();
        final String id = this.OWSLATeamID.getText().toString().trim();
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
                                OWSLATeamSearchBox.setText(OWSLATeamID.getText());
                            }else if(Switcher.equals("2")){
                                if (success.equals("0")) {
                                    Toast.makeText(getApplicationContext(), "Creating team succeed.", Toast.LENGTH_SHORT).show();
                                    OWSLATeamID.setText("");
                                    OWSLATeamName.setText("");
                                    OWSLATeamCapacity.setText("");
                                    OWSLATeamShiftStart.setText("");
                                    OWSLATeamShiftEnd.setText("");
                                }else{
                                    Toast.makeText(getApplicationContext(),"Failed creating team .", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                String id = jsonObject.getString("ID");
                                String TeamName = jsonObject.getString("TeamName");
                                String Capacity = jsonObject.getString("Capacity");
                                String Shift_Start = jsonObject.getString("Shift_Start");
                                String Shift_End = jsonObject.getString("Shift_End");
                                setID(id);

                                if(id.equals("0") && TeamName.equals("0") && Capacity.equals("0") && Shift_Start.equals("0") && Shift_End.equals("0")){
                                    OWSLATeamID.setText("");
                                    OWSLATeamName.setText("");
                                    OWSLATeamCapacity.setText("");
                                    OWSLATeamShiftStart.setText("");
                                    OWSLATeamShiftEnd.setText("");
                                    Toast.makeText(getApplicationContext(), "There are no teams to preview", Toast.LENGTH_SHORT).show();
                                } else {

                                    OWSLATeamID.setText(id);
                                    OWSLATeamSearchBox.setText(id);
                                    OWSLATeamName.setText(TeamName);
                                    OWSLATeamCapacity.setText(Capacity);
                                    OWSLATeamShiftStart.setText(Shift_Start);
                                    OWSLATeamShiftEnd.setText(Shift_End);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OWSLATeam.this, "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OWSLATeam.this, "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID",id);
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

    protected void DelTeams(){
        deleteTeam();
    }

    protected void SearchTeam(String SearchTeam){
        final String TeamCode = this.OWSLATeamSearchBox.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SearchTeam,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String TeamName;
                            String Capacity;
                            String Shift_Start;
                            String Shift_End;
                            String ID;
                            if (success.equals("0")) {
                                ID = jsonObject.getString("ID");
                                TeamName = jsonObject.getString("TeamName");
                                Capacity = jsonObject.getString("Capacity");
                                Shift_Start = jsonObject.getString("Shift_Start");
                                Shift_End = jsonObject.getString("Shift_End");

                                OWSLATeamID.setText(ID);
                                OWSLATeamName.setText(TeamName);
                                OWSLATeamCapacity.setText(Capacity);
                                OWSLATeamShiftStart.setText(Shift_Start);
                                OWSLATeamShiftEnd.setText(Shift_End);

                            } else {
                                Toast.makeText(getApplicationContext(),"Cannot find a team with ID: "+TeamCode, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OWSLATeam.this, "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OWSLATeam.this, "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TeamCode",TeamCode);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void NextTeam(String NextTeam){
        final String TeamCode = this.OWSLATeamID.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, NextTeam,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String TeamName;
                            String Capacity;
                            String Shift_Start;
                            String Shift_End;
                            String ID;
                            if (success.equals("1")) {
                                ID = jsonObject.getString("ID");
                                TeamName = jsonObject.getString("TeamName");
                                Capacity = jsonObject.getString("Capacity");
                                Shift_Start = jsonObject.getString("Shift_Start");
                                Shift_End = jsonObject.getString("Shift_End");

                                OWSLATeamID.setText(ID);
                                OWSLATeamName.setText(TeamName);
                                OWSLATeamCapacity.setText(Capacity);
                                OWSLATeamShiftStart.setText(Shift_Start);
                                OWSLATeamShiftEnd.setText(Shift_End);
                                OWSLATeamSearchBox.setText(String.valueOf(Integer.valueOf(OWSLATeamSearchBox.getText().toString().trim())+1));

                            } else if(success.equals("2")) {
                                Toast.makeText(getApplicationContext(),"This is the last team created", Toast.LENGTH_SHORT).show();
                            } else if(success.equals("3")) {
                                Toast.makeText(getApplicationContext(),"There are no teams", Toast.LENGTH_SHORT).show();
                            } else if(success.equals("view")) {
                                Toast.makeText(getApplicationContext(),"Error during converting ", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OWSLATeam.this, "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OWSLATeam.this, "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TeamCode",TeamCode);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void PrevTeam(String PreviousTeam){
        final String TeamCode = this.OWSLATeamID.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, PreviousTeam,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String TeamName;
                            String Capacity;
                            String Shift_Start;
                            String Shift_End;
                            String ID;
                            if (success.equals("-1")) {

                                ID = jsonObject.getString("ID");
                                TeamName = jsonObject.getString("TeamName");
                                Capacity = jsonObject.getString("Capacity");
                                Shift_Start = jsonObject.getString("Shift_Start");
                                Shift_End = jsonObject.getString("Shift_End");

                                OWSLATeamID.setText(ID);
                                OWSLATeamName.setText(TeamName);
                                OWSLATeamCapacity.setText(Capacity);
                                OWSLATeamShiftStart.setText(Shift_Start);
                                OWSLATeamShiftEnd.setText(Shift_End);
                                OWSLATeamSearchBox.setText(String.valueOf(Integer.valueOf(OWSLATeamSearchBox.getText().toString().trim())-1));


                            } else if(success.equals("-2")) {
                                Toast.makeText(getApplicationContext(),"This is the first team created", Toast.LENGTH_SHORT).show();
                            } else if(success.equals("-3")) {
                                Toast.makeText(getApplicationContext(),"There are no teams", Toast.LENGTH_SHORT).show();
                            } else if(success.equals("view")) {
                                Toast.makeText(getApplicationContext(),"Error during converting ", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OWSLATeam.this, "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OWSLATeam.this, "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TeamCode",TeamCode);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void deleteTeam(){
        final String TeamCode = this.OWSLATeamSearchBox.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DeleteTeam,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String TeamName;
                            String Capacity;
                            String Shift_Start;
                            String Shift_End;
                            String ID;
                            if (success.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Team deleted successfully", Toast.LENGTH_SHORT).show();
                                ID = jsonObject.getString("ID");
                                TeamName = jsonObject.getString("TeamName");
                                Capacity = jsonObject.getString("Capacity");
                                Shift_Start = jsonObject.getString("Shift_Start");
                                Shift_End = jsonObject.getString("Shift_End");

                                OWSLATeamID.setText(ID);
                                OWSLATeamName.setText(TeamName);
                                OWSLATeamCapacity.setText(Capacity);
                                OWSLATeamShiftStart.setText(Shift_Start);
                                OWSLATeamShiftEnd.setText(Shift_End);
                                OWSLATeamSearchBox.setText(String.valueOf(Integer.valueOf(OWSLATeamSearchBox.getText().toString().trim())));

                            } else if(success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Failed to delete the team", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "You have reached the first Team", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(OWSLATeam.this, "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OWSLATeam.this, "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TeamID",TeamCode);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
