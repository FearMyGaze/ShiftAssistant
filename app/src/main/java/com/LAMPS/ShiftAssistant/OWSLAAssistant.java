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

public class OWSLAAssistant extends AppCompatActivity {

    //EditText's

    EditText OWSLAAssistantSearchBox , OWSLAAssistantName , OWSLAAssistantSurname , OWSLAAssistantAFM , OWSLAAssistantEmail ,
            OWSLAAssistantPassword , OWSLAAssistantGender , OWSLAAssistantCellPhone , OWSLAAssistantLandLine ,
            OWSLAAssistantStreetAddress, OWSLAAssistantNumber , OWSLAAssistantPostalCode , OWSLAAssistantNationality ,
            OWSLAAssistantBirthDate;

    //Button's

    Button OWSLAAssistantSearchButton , OWSLAAssistantConfirmButton;

    //ImageButton's

    ImageButton OWSLAAssistantPrevious , OWSLAAssistantNext , OWSLAAssistantRemove, OWSLAAssistantAdd;

    //Link's

    private String CreateAssistant , DeleteAssistant , SearchAssistant , NextAssistant , PreviousAssistant;

    //Helper's

    String AssistantID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla_assistant);

        GlobalVariables Links = new GlobalVariables();

        //Links

        CreateAssistant = Links.getNewAssistant();
        DeleteAssistant = Links.getDeleteAssistants();
        SearchAssistant = Links.getSearchAssistants();
        NextAssistant = Links.getNextAssistant();
        PreviousAssistant = Links.getPreviousAssistant();

        //EditText's

        OWSLAAssistantSearchBox = findViewById(R.id.OWSLAAssistantSearchBox);
        OWSLAAssistantName = findViewById(R.id.OWSLAAssistantName);
        OWSLAAssistantSurname = findViewById(R.id.OWSLAAssistantSurname);
        OWSLAAssistantAFM = findViewById(R.id.OWSLAAssistantAFM);
        OWSLAAssistantEmail = findViewById(R.id.OWSLAAssistantEmail);
        OWSLAAssistantPassword = findViewById(R.id.OWSLAAssistantPassword);
        OWSLAAssistantGender = findViewById(R.id.OWSLAAssistantGender);
        OWSLAAssistantCellPhone = findViewById(R.id.OWSLAAssistantCellPhone);
        OWSLAAssistantLandLine = findViewById(R.id.OWSLAAssistantLandLine);
        OWSLAAssistantStreetAddress = findViewById(R.id.OWSLAAssistantStreetAddress);
        OWSLAAssistantNumber = findViewById(R.id.OWSLAAssistantNumber);
        OWSLAAssistantPostalCode = findViewById(R.id.OWSLAAssistantPostalCode);
        OWSLAAssistantNationality = findViewById(R.id.OWSLAAssistantNationality);
        OWSLAAssistantBirthDate = findViewById(R.id.OWSLAAssistantBirthDate);

        //Button's

        OWSLAAssistantSearchButton = findViewById(R.id.OWSLAAssistantSearchButton);
        OWSLAAssistantConfirmButton = findViewById(R.id.OWSLAAssistantConfirmButton);

        //ImageButton's

        OWSLAAssistantPrevious = findViewById(R.id.OWSLAAssistantPrevious);
        OWSLAAssistantNext = findViewById(R.id.OWSLAAssistantNext);
        OWSLAAssistantRemove = findViewById(R.id.OWSLAAssistantRemove);
        OWSLAAssistantAdd = findViewById(R.id.OWSLAAssistantAdd);

        //ClickListener's

        OWSLAAssistantAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearText();
                OWSLAAssistantConfirmButton.setText(R.string.OWSLATeamConfirmButtonADD);
                OWSLAAssistantConfirmButton.setVisibility(View.VISIBLE);
            }
        });

        OWSLAAssistantRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OWSLAAssistantConfirmButton.setText(R.string.OWSLATeamConfirmButtonDEL);
                OWSLAAssistantConfirmButton.setVisibility(View.VISIBLE);
            }
        });

        OWSLAAssistantConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {NewAssistants("0");}
        });

        OWSLAAssistantRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelAssistants();
            }
        });

        OWSLAAssistantSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SearchAssistant();}
        });

        OWSLAAssistantNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { NextAssistant(NextAssistant);
            }
        });

        OWSLAAssistantPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrevAssistant();

            }
        });

        //Method's

        NewAssistants("1");

    }
    
    protected void NewAssistants(final String switcher){
        final String AssistantName = this.OWSLAAssistantName.getText().toString().trim();
        final String AssistantSurname = this.OWSLAAssistantSurname.getText().toString().trim();
        final String AssistantAFM = this.OWSLAAssistantAFM.getText().toString().trim();
        final String AssistantEmail = this.OWSLAAssistantEmail.getText().toString().trim();
        final String AssistantPassword = this.OWSLAAssistantPassword.getText().toString().trim();
        final String AssistantGender = this.OWSLAAssistantGender.getText().toString().trim();
        final String AssistantCellPhone = this.OWSLAAssistantCellPhone.getText().toString().trim();
        final String AssistantLandLine = this.OWSLAAssistantLandLine.getText().toString().trim();
        final String AssistantStreetAddress = this.OWSLAAssistantStreetAddress.getText().toString().trim();
        final String AssistantNumber = this.OWSLAAssistantNumber.getText().toString().trim();
        final String AssistantPostalCode = this.OWSLAAssistantPostalCode.getText().toString().trim();
        final String AssistantNationality = this.OWSLAAssistantNationality.getText().toString().trim();
        final String AssistantBirthDate = this.OWSLAAssistantBirthDate.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, CreateAssistant, new Response.Listener<String>() {
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
                    String Email;
                    String ID;
                    if(switcher.equals("1")){
                        Firstname = jsonObject.getString("Firstname");
                        Lastname = jsonObject.getString("SurName");
                        Email = jsonObject.getString("Email");
                        TIN = jsonObject.getString("TIN");
                        User_Password = jsonObject.getString("Owner_Password");
                        Landline = jsonObject.getString("LandLine");
                        Mobile = jsonObject.getString("Mobile");
                        Address_Street = jsonObject.getString("Address_Street");
                        Address_Number = jsonObject.getString("Address_Number");
                        Postal_Code = jsonObject.getString("Postal_Code");
                        Gender = jsonObject.getString("Gender");
                        Birthday = jsonObject.getString("Birthday");
                        Citizenship = jsonObject.getString("Citizenship");
                        ID = jsonObject.getString("ID");
                        SetAssistantID(ID);
                        if(!ID.equals("0")) {
                            OWSLAAssistantName.setText(Firstname);
                            OWSLAAssistantSurname.setText(Lastname);
                            OWSLAAssistantEmail.setText(Email);
                            OWSLAAssistantAFM.setText(TIN);
                            OWSLAAssistantPassword.setText(User_Password);
                            OWSLAAssistantLandLine.setText(Landline);
                            OWSLAAssistantCellPhone.setText(Mobile);
                            OWSLAAssistantStreetAddress.setText(Address_Street);
                            OWSLAAssistantNumber.setText(Address_Number);
                            OWSLAAssistantPostalCode.setText(Postal_Code);
                            OWSLAAssistantGender.setText(Gender);
                            OWSLAAssistantBirthDate.setText(Birthday);
                            OWSLAAssistantNationality.setText(Citizenship);
                            OWSLAAssistantSearchBox.setText(ID);
                            SetAssistantID(ID);
                        } else {
                            Toast.makeText(getApplicationContext(), "There are no assistants registered", Toast.LENGTH_SHORT).show();
                            ClearText();
                        }
                    } else {
                        if (success.equals("0")) {
                            Toast.makeText(getApplicationContext(), "Creating Assistant succeed.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed creating Assistant .", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed connection" + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            protected Map<String , String> getParams() throws AuthFailureError{
                Map<String , String> params = new HashMap<>();
                params.put("RegisterName", AssistantName);
                params.put("RegisterSurname", AssistantSurname);
                params.put("RegisterPassword", AssistantPassword);
                params.put("RegisterGender", AssistantGender);
                params.put("RegisterBirthDate" ,AssistantBirthDate);
                params.put("RegisterNationality", AssistantNationality);
                params.put("RegisterEmail", AssistantEmail);
                params.put("RegisterLandLine" , AssistantLandLine);
                params.put("RegisterCellPhone" , AssistantCellPhone);
                params.put("RegisterStreetAddress" , AssistantStreetAddress);
                params.put("RegisterNumber" , AssistantNumber);
                params.put("RegisterPostalCode" , AssistantPostalCode);
                params.put("RegisterAFM" , AssistantAFM);
                params.put("Switcher",switcher);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void DelAssistants(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DeleteAssistant,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Assistant deleted successfully", Toast.LENGTH_SHORT).show();
                                ClearText();
                                PrevAssistant();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to delete the Assistant", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("AssistantID",AssistantID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void SearchAssistant(){
        final String id = this.OWSLAAssistantSearchBox.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SearchAssistant, new Response.Listener<String>() {
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
                    String ID;

                    if (success.equals("1")) {
                        Firstname = jsonObject.getString("Firstname");
                        Lastname = jsonObject.getString("SurName");
                        Email = jsonObject.getString("Email");
                        TIN = jsonObject.getString("TIN");
                        User_Password = jsonObject.getString("Owner_Password");
                        Landline = jsonObject.getString("LandLine");
                        Mobile = jsonObject.getString("Mobile");
                        Address_Street = jsonObject.getString("Address_Street");
                        Address_Number = jsonObject.getString("Address_Number");
                        Postal_Code = jsonObject.getString("Postal_Code");
                        Gender = jsonObject.getString("Gender");
                        Birthday = jsonObject.getString("Birthday");
                        Citizenship = jsonObject.getString("Citizenship");
                        ID = jsonObject.getString("ID");
                        SetAssistantID(ID);

                        OWSLAAssistantName.setText(Firstname);
                        OWSLAAssistantSurname.setText(Lastname);
                        OWSLAAssistantEmail.setText(Email);
                        OWSLAAssistantAFM.setText(TIN);
                        OWSLAAssistantPassword.setText(User_Password);
                        OWSLAAssistantLandLine.setText(Landline);
                        OWSLAAssistantCellPhone.setText(Mobile);
                        OWSLAAssistantStreetAddress.setText(Address_Street);
                        OWSLAAssistantNumber.setText(Address_Number);
                        OWSLAAssistantPostalCode.setText(Postal_Code);
                        OWSLAAssistantGender.setText(Gender);
                        OWSLAAssistantBirthDate.setText(Birthday);
                        OWSLAAssistantNationality.setText(Citizenship);
                    } else {
                        Toast.makeText(getApplicationContext(), "Cannot find a Assistant with ID: " + id, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            protected Map<String , String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("ID",id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void NextAssistant(String NextLink){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NextLink, new Response.Listener<String>() {
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
                    String ID;

                    if (success.equals("1")) {
                        Firstname = jsonObject.getString("Firstname");
                        Lastname = jsonObject.getString("SurName");
                        Email = jsonObject.getString("Email");
                        TIN = jsonObject.getString("TIN");
                        User_Password = jsonObject.getString("Owner_password");
                        Landline = jsonObject.getString("LandLine");
                        Mobile = jsonObject.getString("Mobile");
                        Address_Street = jsonObject.getString("Address_Street");
                        Address_Number = jsonObject.getString("Address_Number");
                        Postal_Code = jsonObject.getString("Postal_Code");
                        Gender = jsonObject.getString("Gender");
                        Birthday = jsonObject.getString("Birthday");
                        Citizenship = jsonObject.getString("Citizenship");
                        ID = jsonObject.getString("ID");
                        SetAssistantID(ID);

                        OWSLAAssistantName.setText(Firstname);
                        OWSLAAssistantSurname.setText(Lastname);
                        OWSLAAssistantEmail.setText(Email);
                        OWSLAAssistantAFM.setText(TIN);
                        OWSLAAssistantPassword.setText(User_Password);
                        OWSLAAssistantLandLine.setText(Landline);
                        OWSLAAssistantCellPhone.setText(Mobile);
                        OWSLAAssistantStreetAddress.setText(Address_Street);
                        OWSLAAssistantNumber.setText(Address_Number);
                        OWSLAAssistantPostalCode.setText(Postal_Code);
                        OWSLAAssistantGender.setText(Gender);
                        OWSLAAssistantBirthDate.setText(Birthday);
                        OWSLAAssistantNationality.setText(Citizenship);

                    } else if (success.equals("2")) {
                        Toast.makeText(getApplicationContext(), "This is the last Assistant created", Toast.LENGTH_SHORT).show();
                    } else if (success.equals("3")) {
                        Toast.makeText(getApplicationContext(), "There are no Assistants", Toast.LENGTH_SHORT).show();
                    } else if (success.equals("view")) {
                        Toast.makeText(getApplicationContext(), "Error during converting ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("AssistantID",AssistantID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void PrevAssistant(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PreviousAssistant, new Response.Listener<String>() {
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
                    String ID;

                    if (success.equals("-1")) {
                        Firstname = jsonObject.getString("Firstname");
                        Lastname = jsonObject.getString("SurName");
                        Email = jsonObject.getString("Email");
                        TIN = jsonObject.getString("TIN");
                        User_Password = jsonObject.getString("Owner_password");
                        Landline = jsonObject.getString("LandLine");
                        Mobile = jsonObject.getString("Mobile");
                        Address_Street = jsonObject.getString("Address_Street");
                        Address_Number = jsonObject.getString("Address_Number");
                        Postal_Code = jsonObject.getString("Postal_Code");
                        Gender = jsonObject.getString("Gender");
                        Birthday = jsonObject.getString("Birthday");
                        Citizenship = jsonObject.getString("Citizenship");
                        ID = jsonObject.getString("ID");
                        SetAssistantID(ID);

                        OWSLAAssistantName.setText(Firstname);
                        OWSLAAssistantSurname.setText(Lastname);
                        OWSLAAssistantEmail.setText(Email);
                        OWSLAAssistantAFM.setText(TIN);
                        OWSLAAssistantPassword.setText(User_Password);
                        OWSLAAssistantLandLine.setText(Landline);
                        OWSLAAssistantCellPhone.setText(Mobile);
                        OWSLAAssistantStreetAddress.setText(Address_Street);
                        OWSLAAssistantNumber.setText(Address_Number);
                        OWSLAAssistantPostalCode.setText(Postal_Code);
                        OWSLAAssistantGender.setText(Gender);
                        OWSLAAssistantBirthDate.setText(Birthday);
                        OWSLAAssistantNationality.setText(Citizenship);


                    } else if (success.equals("-2")) {
                        Toast.makeText(getApplicationContext(), "This is the first Assistant created", Toast.LENGTH_SHORT).show();
                    } else if (success.equals("-3")) {
                        Toast.makeText(getApplicationContext(), "There are no Assistant", Toast.LENGTH_SHORT).show();
                    } else if (success.equals("view")) {
                        Toast.makeText(getApplicationContext(), "Error during converting ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Field trash." + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed connection" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("AssistantID",AssistantID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void SetAssistantID(String AIDS){
        this.AssistantID = AIDS;
    }

    protected void ClearText(){
        OWSLAAssistantSearchBox.setText("");
        OWSLAAssistantName.setText("");
        OWSLAAssistantSurname.setText("");
        OWSLAAssistantAFM.setText("");
        OWSLAAssistantEmail.setText("");
        OWSLAAssistantPassword.setText("");
        OWSLAAssistantGender.setText("");
        OWSLAAssistantCellPhone.setText("");
        OWSLAAssistantLandLine.setText("");
        OWSLAAssistantStreetAddress.setText("");
        OWSLAAssistantNumber.setText("");
        OWSLAAssistantPostalCode.setText("");
        OWSLAAssistantNationality.setText("");
        OWSLAAssistantBirthDate.setText("");
    }
}
