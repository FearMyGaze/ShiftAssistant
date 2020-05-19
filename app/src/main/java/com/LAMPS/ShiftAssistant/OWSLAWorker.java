package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
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


public class OWSLAWorker extends AppCompatActivity {

    //Edit Text's

    EditText OWSLAWorkerName, OWSLAWorkerSurname , OWSLAWorkerAFM , OWSLAWorkerEmail , OWSLAWorkerPassword , OWSLAWorkerGender ,
            OWSLAWorkerLandLine , OWSLAWorkerCellPhone , OWSLAWorkerStreetAddress , OWSLAWorkerNumber , OWSLAWorkerPostalCode ,
            OWSLAWorkerTeamCode , OWSLAWorkerBirthDate , OWSLAWorkerNationality , OWSLAWorkerWorkHours , OWSLAWorkerShiftType ,
            OWSLAWorkerSearchBox;

    //Button

    Button OWSLAWorkerConfirmButton , OWSLAWorkerSearchButton;

    //ImageButtons

    ImageButton OWSLAWorkerPrevious , OWSLAWorkerNext , OWSLAWorkerAdd , OWSLAWorkerRemove;

    //Link

    private String CreateWorker , DeleteWorker , SearchWorker , NextWorker , PreviousWorker;

    //Helpers
    String employeeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla_worker);

        GlobalVariables PHPLinks = new GlobalVariables();

        //Links

        CreateWorker = PHPLinks.getNewWorker_URL();
        DeleteWorker = PHPLinks.getDeleteWorker_URL();
        SearchWorker = PHPLinks.getSearchWorker_URL();
        NextWorker = PHPLinks.getNextWorker_URL();
        PreviousWorker = PHPLinks.getPreviousWorker_URL();

        //EditText's

        OWSLAWorkerName = findViewById(R.id.OWSLAWorkerName);
        OWSLAWorkerSurname = findViewById(R.id.OWSLAWorkerSurname);
        OWSLAWorkerAFM = findViewById(R.id.OWSLAWorkerAFM);
        OWSLAWorkerEmail = findViewById(R.id.OWSLAWorkerEmail);
        OWSLAWorkerPassword = findViewById(R.id.OWSLAWorkerPassword);
        OWSLAWorkerGender = findViewById(R.id.OWSLAWorkerGender);
        OWSLAWorkerLandLine = findViewById(R.id.OWSLAWorkerLandLine);
        OWSLAWorkerCellPhone = findViewById(R.id.OWSLAWorkerCellPhone);
        OWSLAWorkerStreetAddress = findViewById(R.id.OWSLAWorkerStreetAddress);
        OWSLAWorkerNumber = findViewById(R.id.OWSLAWorkerNumber);
        OWSLAWorkerPostalCode = findViewById(R.id.OWSLAWorkerPostalCode);
        OWSLAWorkerTeamCode = findViewById(R.id.OWSLAWorkerTeamCode);
        OWSLAWorkerBirthDate = findViewById(R.id.OWSLAWorkerBirthDate);
        OWSLAWorkerNationality = findViewById(R.id.OWSLAWorkerNationality);
        OWSLAWorkerWorkHours = findViewById(R.id.OWSLAWorkerWorkHours);
        OWSLAWorkerShiftType = findViewById(R.id.OWSLAWorkerShiftType);
        OWSLAWorkerSearchBox = findViewById(R.id.OWSLAWorkerSearchBox);

        //Button's

        OWSLAWorkerConfirmButton = findViewById(R.id.OWSLAWorkerConfirmButton);
        OWSLAWorkerSearchButton = findViewById(R.id.OWSLAWorkerSearchButton);

        //ImageButton's

        OWSLAWorkerPrevious = findViewById(R.id.OWSLAWorkerPrevious);
        OWSLAWorkerNext = findViewById(R.id.OWSLAWorkerNext);
        OWSLAWorkerAdd = findViewById(R.id.OWSLAWorkerAdd);
        OWSLAWorkerRemove = findViewById(R.id.OWSLAWorkerRemove);

        //ClickListener's

        OWSLAWorkerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearText();
                OWSLAWorkerConfirmButton.setText(R.string.OWSLATeamConfirmButtonADD);
                OWSLAWorkerConfirmButton.setVisibility(View.VISIBLE);
            }
        });

        OWSLAWorkerRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OWSLAWorkerConfirmButton.setText(R.string.OWSLATeamConfirmButtonDEL);
                OWSLAWorkerConfirmButton.setVisibility(View.VISIBLE);
            }
        });

        OWSLAWorkerConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewWorkers(CreateWorker,"2");
            }
        });

        OWSLAWorkerRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelWorkers(DeleteWorker);
                Toast.makeText(getApplicationContext(),"Not yet implemented",Toast.LENGTH_LONG).show();
            }
        });

        OWSLAWorkerSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { SearchWorker(SearchWorker,"0","0");
            }
        });

        OWSLAWorkerNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { NextWorker(NextWorker);

            }
        });

        OWSLAWorkerPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { PrevWorker(PreviousWorker);
            }
        });

        //Methods

        NewWorkers(CreateWorker,"0");

        //Popup Menu's
        OWSLAWorkerWorkHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu WorkHours = new PopupMenu(OWSLAWorker.this,v);
                WorkHours.getMenuInflater().inflate(R.menu.work_hours_menu,WorkHours.getMenu());

                WorkHours.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.MenuWorkHours4:
                                OWSLAWorkerWorkHours.setText(R.string.MenuWorkHours4);
                                return true;
                            case R.id.MenuWorkHours6:
                                OWSLAWorkerWorkHours.setText(R.string.MenuWorkHours6);
                                return true;
                            case R.id.MenuWorkHours8:
                                OWSLAWorkerWorkHours.setText(R.string.MenuWorkHours8);
                                return true;
                        }
                        return true;
                    }
                });
                WorkHours.show();
            }
        });

        OWSLAWorkerShiftType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu ShiftType = new PopupMenu(OWSLAWorker.this,v);
                ShiftType.getMenuInflater().inflate(R.menu.shift_type_menu,ShiftType.getMenu());

                ShiftType.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.MenuShiftTypeMorning:
                                OWSLAWorkerShiftType.setText(R.string.MenuShiftTypeMorningIF);
                                return true;
                            case R.id.MenuShiftTypeNoon:
                                OWSLAWorkerShiftType.setText(R.string.MenuShiftTypeNoonIF);
                                return true;
                            case R.id.MenuShiftTypeNight:
                                OWSLAWorkerShiftType.setText(R.string.MenuShiftTypeNightIF);
                                return true;
                        }
                        return true;
                    }
                });
                ShiftType.show();
            }
        });

    }

    protected void NewWorkers(String CreateLink , final String Switcher){
        final String WorkerName = this.OWSLAWorkerName.getText().toString().trim();
        final String WorkerSurname = this.OWSLAWorkerSurname.getText().toString().trim();
        final String WorkerAFM = this.OWSLAWorkerAFM.getText().toString().trim();
        final String WorkerEmail = this.OWSLAWorkerEmail.getText().toString().trim();
        final String WorkerPassword = this.OWSLAWorkerPassword.getText().toString().trim();
        final String WorkerGender = this.OWSLAWorkerGender.getText().toString().trim();
        final String WorkerLandLine = this.OWSLAWorkerLandLine.getText().toString().trim();
        final String WorkerCellPhone = this.OWSLAWorkerCellPhone.getText().toString().trim();
        final String WorkerStreetAddress = this.OWSLAWorkerStreetAddress.getText().toString().trim();
        final String WorkerNumber = this.OWSLAWorkerNumber.getText().toString().trim();
        final String WorkerPostalCode = this.OWSLAWorkerPostalCode.getText().toString().trim();
        final String WorkerTeamCode = this.OWSLAWorkerTeamCode.getText().toString().trim();
        final String WorkerBirthDate = this.OWSLAWorkerBirthDate.getText().toString().trim();
        final String WorkerNationality = this.OWSLAWorkerNationality.getText().toString().trim();
        final String WorkerWorkHours = this.OWSLAWorkerWorkHours.getText().toString().trim();
        final String ShiftType = this.OWSLAWorkerShiftType.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, CreateLink, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (Switcher.equals("0")){
                        String id = jsonObject.getString("ID");
                        setEmployeeID(id);
                        SearchWorker(SearchWorker,employeeID,"1");
                        //System.out.println(employeeID);
                    } else {
                        if (success.equals("0")) {
                            Toast.makeText(getApplicationContext(), "Creating worker succeed.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed creating worker .", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(OWSLAWorker.this, "Field trash." + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OWSLAWorker.this, "Failed connection" + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String , String> getParams() throws AuthFailureError{
                Map<String , String> params = new HashMap<>();
                params.put("RegisterName", WorkerName);
                params.put("RegisterSurname", WorkerSurname);
                params.put("RegisterPassword", WorkerPassword);
                params.put("RegisterGender", WorkerGender);
                params.put("RegisterBirthDate" ,WorkerBirthDate);
                params.put("RegisterNationality", WorkerNationality);
                params.put("RegisterEmail", WorkerEmail);
                params.put("RegisterLandLine" , WorkerLandLine);
                params.put("RegisterCellPhone" , WorkerCellPhone);
                params.put("RegisterStreetAddress" , WorkerStreetAddress);
                params.put("RegisterNumber" , WorkerNumber);
                params.put("RegisterPostalCode" , WorkerPostalCode);
                params.put("RegisterAFM" , WorkerAFM);
                params.put("RegisterWorkHours" , WorkerWorkHours);
                params.put("RegisterShiftType" , ShiftType);
                params.put("RegisterTeamCode" , WorkerTeamCode);
                params.put("Switcher" , Switcher);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void DelWorkers(String DeleteLink){

    }

    protected void SearchWorker(String SearchWorker, final String WorkerID,final String pointer){
        final String WorkerAFM = this.OWSLAWorkerSearchBox.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SearchWorker, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    String Firstname;
                    String Lastname;
                    String Email;
                    String TIN;
                    String User_Password;
                    String Landline;
                    String Mobile;
                    String Address_Street;
                    String Address_Number;
                    String Postal_Code;
                    String Gender;
                    String Birthday;
                    String Citizenship;
                    String WorkHours;
                    String Teams_Code;
                    String ShiftType;
                    String ID;

                    if (success.equals("1")) {
                        Firstname = jsonObject.getString("Firstname");
                        Lastname = jsonObject.getString("Lastname");
                        Email = jsonObject.getString("Email");
                        TIN = jsonObject.getString("TIN");
                        User_Password = jsonObject.getString("User_Password");
                        Landline = jsonObject.getString("Landline");
                        Mobile = jsonObject.getString("Mobile");
                        Address_Street = jsonObject.getString("Address_Street");
                        Address_Number = jsonObject.getString("Address_Number");
                        Postal_Code = jsonObject.getString("Postal_Code");
                        Gender = jsonObject.getString("Gender");
                        Birthday = jsonObject.getString("Birthday");
                        Citizenship = jsonObject.getString("Citizenship");
                        WorkHours = jsonObject.getString("WorkHours");
                        Teams_Code = jsonObject.getString("Teams_Code");
                        ShiftType = jsonObject.getString("ShiftType");
                        ID = jsonObject.getString("ID");
                        setEmployeeID(ID);

                        OWSLAWorkerName.setText(Firstname);
                        OWSLAWorkerSurname.setText(Lastname);
                        OWSLAWorkerEmail.setText(Email);
                        OWSLAWorkerAFM.setText(TIN);
                        OWSLAWorkerPassword.setText(User_Password);
                        OWSLAWorkerLandLine.setText(Landline);
                        OWSLAWorkerCellPhone.setText(Mobile);
                        OWSLAWorkerStreetAddress.setText(Address_Street);
                        OWSLAWorkerNumber.setText(Address_Number);
                        OWSLAWorkerPostalCode.setText(Postal_Code);
                        OWSLAWorkerGender.setText(Gender);
                        OWSLAWorkerBirthDate.setText(Birthday);
                        OWSLAWorkerNationality.setText(Citizenship);
                        OWSLAWorkerWorkHours.setText(WorkHours);
                        OWSLAWorkerTeamCode.setText(Teams_Code);
                        OWSLAWorkerShiftType.setText(ShiftType);
                        //Needs shift

                    } else {
                        Toast.makeText(getApplicationContext(), "Cannot find a employee with ID: " + WorkerAFM, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(OWSLAWorker.this, "Field trash." + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OWSLAWorker.this, "Failed connection" + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID",WorkerID);
                params.put("TIN",WorkerAFM);
                params.put("pointer",pointer);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void NextWorker(String NextWorker){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, NextWorker, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    String Firstname;
                    String Lastname;
                    String TIN;
                    String User_Password;
                    String Landline;
                    String Mobile;
                    String Address_Street;
                    String Address_Number;
                    String Postal_Code;
                    String Gender;
                    String Birthday;
                    String Citizenship;
                    String WorkHours;
                    String Teams_Code;
                    String ShiftType;
                    String ID;

                    if (success.equals("1")) {
                        Firstname = jsonObject.getString("Firstname");
                        Lastname = jsonObject.getString("Lastname");
                        TIN = jsonObject.getString("TIN");
                        User_Password = jsonObject.getString("User_Password");
                        Landline = jsonObject.getString("Landline");
                        Mobile = jsonObject.getString("Mobile");
                        Address_Street = jsonObject.getString("Address_Street");
                        Address_Number = jsonObject.getString("Address_Number");
                        Postal_Code = jsonObject.getString("Postal_Code");
                        Gender = jsonObject.getString("Gender");
                        Birthday = jsonObject.getString("Birthday");
                        Citizenship = jsonObject.getString("Citizenship");
                        WorkHours = jsonObject.getString("WorkHours");
                        Teams_Code = jsonObject.getString("Teams_Code");
                        ShiftType = jsonObject.getString("ShiftType");
                        ID = jsonObject.getString("ID");
                        setEmployeeID(ID);

                        OWSLAWorkerName.setText(Firstname);
                        OWSLAWorkerSurname.setText(Lastname);
                        OWSLAWorkerAFM.setText(TIN);
                        OWSLAWorkerPassword.setText(User_Password);
                        OWSLAWorkerLandLine.setText(Landline);
                        OWSLAWorkerCellPhone.setText(Mobile);
                        OWSLAWorkerStreetAddress.setText(Address_Street);
                        OWSLAWorkerNumber.setText(Address_Number);
                        OWSLAWorkerPostalCode.setText(Postal_Code);
                        OWSLAWorkerGender.setText(Gender);
                        OWSLAWorkerBirthDate.setText(Birthday);
                        OWSLAWorkerNationality.setText(Citizenship);
                        OWSLAWorkerWorkHours.setText(WorkHours);
                        OWSLAWorkerTeamCode.setText(Teams_Code);
                        OWSLAWorkerShiftType.setText(ShiftType);
                        OWSLAWorkerSearchBox.setText("");

                    } else if (success.equals("2")) {
                        Toast.makeText(getApplicationContext(), "This is the last worker created", Toast.LENGTH_LONG).show();
                        System.out.println(employeeID);
                    } else if (success.equals("3")) {
                        Toast.makeText(getApplicationContext(), "There are no workers", Toast.LENGTH_LONG).show();
                    } else if (success.equals("view")) {
                        Toast.makeText(getApplicationContext(), "Error during converting ", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(OWSLAWorker.this, "Field trash." + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OWSLAWorker.this, "Failed connection" + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("WorkerID",employeeID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void PrevWorker(String PreviousWorker){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, PreviousWorker, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    String Firstname;
                    String Lastname;
                    String TIN;
                    String User_Password;
                    String Landline;
                    String Mobile;
                    String Address_Street;
                    String Address_Number;
                    String Postal_Code;
                    String Gender;
                    String Birthday;
                    String Citizenship;
                    String WorkHours;
                    String Teams_Code;
                    String ShiftType;
                    String ID;

                    if (success.equals("-1")) {
                        Firstname = jsonObject.getString("Firstname");
                        Lastname = jsonObject.getString("Lastname");
                        TIN = jsonObject.getString("TIN");
                        User_Password = jsonObject.getString("User_Password");
                        Landline = jsonObject.getString("Landline");
                        Mobile = jsonObject.getString("Mobile");
                        Address_Street = jsonObject.getString("Address_Street");
                        Address_Number = jsonObject.getString("Address_Number");
                        Postal_Code = jsonObject.getString("Postal_Code");
                        Gender = jsonObject.getString("Gender");
                        Birthday = jsonObject.getString("Birthday");
                        Citizenship = jsonObject.getString("Citizenship");
                        WorkHours = jsonObject.getString("WorkHours");
                        Teams_Code = jsonObject.getString("Teams_Code");
                        ShiftType = jsonObject.getString("ShiftType");
                        ID = jsonObject.getString("ID");
                        setEmployeeID(ID);

                        OWSLAWorkerName.setText(Firstname);
                        OWSLAWorkerSurname.setText(Lastname);
                        OWSLAWorkerAFM.setText(TIN);
                        OWSLAWorkerPassword.setText(User_Password);
                        OWSLAWorkerLandLine.setText(Landline);
                        OWSLAWorkerCellPhone.setText(Mobile);
                        OWSLAWorkerStreetAddress.setText(Address_Street);
                        OWSLAWorkerNumber.setText(Address_Number);
                        OWSLAWorkerPostalCode.setText(Postal_Code);
                        OWSLAWorkerGender.setText(Gender);
                        OWSLAWorkerBirthDate.setText(Birthday);
                        OWSLAWorkerNationality.setText(Citizenship);
                        OWSLAWorkerWorkHours.setText(WorkHours);
                        OWSLAWorkerTeamCode.setText(Teams_Code);
                        OWSLAWorkerShiftType.setText(ShiftType);
                        OWSLAWorkerSearchBox.setText("");

                    } else if (success.equals("-2")) {
                        Toast.makeText(getApplicationContext(), "This is the first worker created", Toast.LENGTH_LONG).show();
                        System.out.println(employeeID);
                    } else if (success.equals("-3")) {
                        Toast.makeText(getApplicationContext(), "There are no workers", Toast.LENGTH_LONG).show();
                    } else if (success.equals("view")) {
                        Toast.makeText(getApplicationContext(), "Error during converting ", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(OWSLAWorker.this, "Field trash." + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OWSLAWorker.this, "Failed connection" + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("WorkerID",String.valueOf(Integer.valueOf(employeeID)));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setEmployeeID(String eID){
        this.employeeID = eID;
    }

    public void ClearText(){
        OWSLAWorkerName.setText("");
        OWSLAWorkerSurname.setText("");
        OWSLAWorkerEmail.setText("");
        OWSLAWorkerPassword.setText("");
        OWSLAWorkerGender.setText("");
        OWSLAWorkerLandLine.setText("");
        OWSLAWorkerCellPhone.setText("");
        OWSLAWorkerStreetAddress.setText("");
        OWSLAWorkerNumber.setText("");
        OWSLAWorkerPostalCode.setText("");
        OWSLAWorkerTeamCode.setText("");
        OWSLAWorkerBirthDate.setText("");
        OWSLAWorkerNationality.setText("");
        OWSLAWorkerWorkHours.setText("");
    }

}
