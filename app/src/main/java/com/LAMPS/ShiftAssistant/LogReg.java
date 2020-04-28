package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Switch;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogReg extends AppCompatActivity {
//============================================== GLOBAL ===============================================================================

    //===============================================Forms===========================================================================

    LinearLayout LoginForm , RegisterForm , ForgetForm;

    //===========================================Register form=======================================================================

    EditText RegisterName , RegisterSurname , RegisterAFM , RegisterEmail , RegisterPassword , RegisterConfirmPassword ,
            RegisterGender , RegisterLandLine , RegisterCellPhone , RegisterStreetAddress , RegisterNumber , RegisterPostalCode ,
            RegisterBirthDate , RegisterNationality , RegisterTeamCode , RegisterWorkHours , RegisterShiftType;

    Button RegisterButton , RegisterConfirmButton;

    Switch RegisterUserType;

    LinearLayout RegisterWorkHoursForm , RegisterShiftTypeForm , RegisterTeamCodeForm;

    //==============================================Login form=======================================================================

    EditText LoginName , LoginPassword;

    Button LoginButton , LoginConfirmButton;

    Switch LoginUserType;

    //===========================================Forget Form=========================================================================

    EditText ForgetName;

    TextView LoginForgotText;

    Button ForgetSend;

    //URL's

    private String Login_URL = "http://192.168.1.8/Shifts/LoginEmployee.php";
    private String LoginOwner_URL ="http://192.168.1.8/Shifts/LoginOwner.php";
    private String Register_URL = "http://192.168.1.8/Shifts/RegisterEmployees.php";
    private String RegisterOwners_URL = "http://192.168.1.8/Shifts/RegisterOwners.php";
    private String FetchEmployeesData_URL = "http://192.168.1.8/Shifts/FetchEmployees.php";

    //Json

    private static final String File_Name = "AlgorithmTest.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);

        //Switch's

        LoginUserType = findViewById(R.id.UserType);
        RegisterUserType = findViewById(R.id.RegisterUserType);

        //Form changer

        LoginButton = findViewById(R.id.LoginButton);
        RegisterButton = findViewById(R.id.RegisterButton);

        //Forms

        LoginForm = findViewById(R.id.LoginForm);
        RegisterForm = findViewById(R.id.RegisterForm);
        ForgetForm = findViewById(R.id.ForgetForm);

        //Various Buttons

        LoginConfirmButton = findViewById(R.id.LoginConfirmButton);
        RegisterConfirmButton = findViewById(R.id.RegisterConfirmButton);

        //Login EditText's

        LoginName = findViewById(R.id.LoginName);
        LoginPassword = findViewById(R.id.LoginPassword);

        //Register EditText's

        RegisterName = findViewById(R.id.RegisterName);
        RegisterSurname = findViewById(R.id.RegisterSurname);
        RegisterAFM = findViewById(R.id.RegisterAFM);
        RegisterEmail = findViewById(R.id.RegisterEmail);
        RegisterPassword = findViewById(R.id.RegisterPassword);
        RegisterConfirmPassword = findViewById(R.id.RegisterConfirmPassword);
        RegisterGender = findViewById(R.id.RegisterGender);
        RegisterLandLine = findViewById(R.id.RegisterLandLine);
        RegisterCellPhone = findViewById(R.id.RegisterCellPhone);
        RegisterStreetAddress = findViewById(R.id.RegisterStreetAddress);
        RegisterNumber = findViewById(R.id.RegisterNumber);
        RegisterPostalCode = findViewById(R.id.RegisterPostalCode);
        RegisterBirthDate = findViewById(R.id.RegisterBirthDate);
        RegisterNationality = findViewById(R.id.RegisterNationality);
        RegisterTeamCode = findViewById(R.id.RegisterTeamCode);
        RegisterWorkHours = findViewById(R.id.RegisterWorkHours);
        RegisterShiftType =findViewById(R.id.RegisterShiftType);
        RegisterWorkHoursForm = findViewById(R.id.RegisterWorkHoursForm);
        RegisterShiftTypeForm = findViewById(R.id.RegisterShiftTypeForm);
        RegisterTeamCodeForm = findViewById(R.id.RegisterTeamCodeForm);

        //Forget Form

        LoginForgotText = findViewById(R.id.LoginForgotText);
        ForgetName = findViewById(R.id.ForgetName);
        ForgetSend = findViewById(R.id.ForgetSend);

        //ClickListener 's

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchEmployeesData();
                ClearText();
                LoginForm.setVisibility(View.VISIBLE);
                RegisterForm.setVisibility(View.GONE);
                ForgetForm.setVisibility(View.GONE);
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearText();
                LoginForm.setVisibility(View.GONE);
                RegisterForm.setVisibility(View.VISIBLE);
                ForgetForm.setVisibility(View.GONE);
            }
        });

        LoginForgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearText();
                LoginForm.setVisibility(View.GONE);
                RegisterForm.setVisibility(View.GONE);
                ForgetForm.setVisibility(View.VISIBLE);
            }
        });

        RegisterUserType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RegisterUserType.isChecked()){
                    RegisterNationality.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    RegisterShiftTypeForm.setVisibility(View.GONE);
                    RegisterWorkHoursForm.setVisibility(View.GONE);
                    RegisterTeamCodeForm.setVisibility(View.GONE);
                }
                else{
                    RegisterNationality.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                    RegisterShiftTypeForm.setVisibility(View.VISIBLE);
                    RegisterWorkHoursForm.setVisibility(View.VISIBLE);
                    RegisterTeamCodeForm.setVisibility(View.VISIBLE);
                }
            }
        });

        LoginConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = LoginName.getText().toString().trim();
                String mPass = LoginPassword.getText().toString().trim();
                if(!mEmail.isEmpty() || !mPass.isEmpty()) {
                    if(LoginUserType.isChecked() ) {
                        Login(mEmail,mPass,LoginOwner_URL);
                    }else{
                        Login(mEmail,mPass,Login_URL);
                    }
                } else {
                    if(LoginUserType.isChecked()){
                        LoginName.setError("Please fill the username field");
                        LoginPassword.setError("Please fill the password field");
                    }else {
                        LoginName.setError("Please fill the username field");
                        LoginPassword.setError("Please fill the password field");
                    }
                }
            }
        });

        RegisterConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RegisterUserType.isChecked()){
                    RegisterOwners();
                }
                else{
                    RegisterEmployees();
                }

            }
        });

        ForgetSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        //Popup Menu
        RegisterWorkHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu WorkHours = new PopupMenu(LogReg.this,v);
                WorkHours.getMenuInflater().inflate(R.menu.work_hours_menu,WorkHours.getMenu());

                WorkHours.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.MenuWorkHours4:
                                RegisterWorkHours.setText(R.string.MenuWorkHours4);
                                return true;
                            case R.id.MenuWorkHours6:
                                RegisterWorkHours.setText(R.string.MenuWorkHours6);
                                return true;
                            case R.id.MenuWorkHours8:
                                RegisterWorkHours.setText(R.string.MenuWorkHours8);
                                return true;
                        }
                        return true;
                    }
                });
                WorkHours.show();
            }
        });

        RegisterShiftType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu ShiftType = new PopupMenu(LogReg.this,v);
                ShiftType.getMenuInflater().inflate(R.menu.shift_type_menu,ShiftType.getMenu());

                ShiftType.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.MenuShiftTypeMorning:
                                RegisterShiftType.setText(R.string.MenuShiftTypeMorningIF);
                                return true;
                            case R.id.MenuShiftTypeNoon:
                                RegisterShiftType.setText(R.string.MenuShiftTypeNoonIF);
                                return true;
                            case R.id.MenuShiftTypeNight:
                                RegisterShiftType.setText(R.string.MenuShiftTypeNightIF);
                                return true;
                        }
                        return true;
                    }
                });
                ShiftType.show();
            }
        });





    }

    private void FetchEmployeesData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, FetchEmployeesData_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.isEmpty()){
                            Toast.makeText(LogReg.this,
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
                                        fos = openFileOutput(File_Name,MODE_PRIVATE);
                                        try {
                                            object.put("Employees",jsonObject.get("Employees"));
                                            fos.write(object.toString().getBytes());
                                            fos.flush();
                                            //Toast.makeText(getApplicationContext(),"Saved to" + getFilesDir() + "/" + File_Name,Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(LogReg.this,
                                            "There are no records in the Database", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(LogReg.this, "An Error came through" +e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogReg.this, "Failed connection" +error.toString(), Toast.LENGTH_SHORT).show();

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

    private void RegisterEmployees(){
        final String RegisterName = this.RegisterName.getText().toString().trim();
        final String RegisterSurname = this.RegisterSurname.getText().toString().trim();
        final String RegisterAFM = this.RegisterAFM.getText().toString().trim();
        final String RegisterEmail = this.RegisterEmail.getText().toString().trim();
        final String RegisterPassword = this.RegisterPassword.getText().toString().trim();
        final String RegisterGender = this.RegisterGender.getText().toString().trim();
        final String RegisterLandLine = this.RegisterLandLine.getText().toString().trim();
        final String RegisterCellPhone = this.RegisterCellPhone.getText().toString().trim();
        final String RegisterStreetAddress = this.RegisterStreetAddress.getText().toString().trim();
        final String RegisterNumber = this.RegisterNumber.getText().toString().trim();
        final String RegisterPostalCode = this.RegisterPostalCode.getText().toString().trim();
        final String RegisterBirthDate = this.RegisterBirthDate.getText().toString().trim();
        final String RegisterNationality = this.RegisterNationality.getText().toString().trim();
        final String RegisterTeamCode = this.RegisterTeamCode.getText().toString().trim();
        final String RegisterWorkHours = this.RegisterWorkHours.getText().toString().trim();
        final String RegisterShiftType = this.RegisterShiftType.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Register_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Registration succeed.", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Registration failed!", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("2")) {
                                Toast.makeText(getApplicationContext(), "Registration failed. Invalid Email.", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("3")) {
                                Toast.makeText(getApplicationContext(), "Registration failed. Invalid ΤΙΝ.", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("4")) {
                                Toast.makeText(getApplicationContext(), "Registration failed. Invalid Department.", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LogReg.this, "Field trash." + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogReg.this, "Failed connection" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("RegisterName",RegisterName);
                params.put("RegisterSurname",RegisterSurname);
                params.put("RegisterAFM",RegisterAFM);
                params.put("RegisterEmail",RegisterEmail);
                params.put("RegisterPassword",RegisterPassword);
                params.put("RegisterGender",RegisterGender);
                params.put("RegisterLandLine",RegisterLandLine);
                params.put("RegisterCellPhone",RegisterCellPhone);
                params.put("RegisterStreetAddress",RegisterStreetAddress);
                params.put("RegisterNumber",RegisterNumber);
                params.put("RegisterPostalCode",RegisterPostalCode);
                params.put("RegisterBirthDate",RegisterBirthDate);
                params.put("RegisterNationality",RegisterNationality);
                params.put("RegisterTeamCode",RegisterTeamCode);
                params.put("RegisterShiftType",RegisterShiftType);
                params.put("RegisterWorkHours",RegisterWorkHours);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void RegisterOwners(){
        final String RegisterName = this.RegisterName.getText().toString().trim();
        final String RegisterSurname = this.RegisterSurname.getText().toString().trim();
        final String RegisterAFM = this.RegisterAFM.getText().toString().trim();
        final String RegisterEmail = this.RegisterEmail.getText().toString().trim();
        final String RegisterPassword = this.RegisterPassword.getText().toString().trim();
        final String RegisterGender = this.RegisterGender.getText().toString().trim();
        final String RegisterLandLine = this.RegisterLandLine.getText().toString().trim();
        final String RegisterCellPhone = this.RegisterCellPhone.getText().toString().trim();
        final String RegisterStreetAddress = this.RegisterStreetAddress.getText().toString().trim();
        final String RegisterNumber = this.RegisterNumber.getText().toString().trim();
        final String RegisterPostalCode = this.RegisterPostalCode.getText().toString().trim();
        final String RegisterBirthDate = this.RegisterBirthDate.getText().toString().trim();
        final String RegisterNationality = this.RegisterNationality.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  RegisterOwners_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Registration succeed.", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Registration failed!", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("2")) {
                                Toast.makeText(getApplicationContext(), "Registration failed. Invalid Email.", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("3")) {
                                Toast.makeText(getApplicationContext(), "Registration failed. Invalid ΤΙΝ.", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LogReg.this, "Field trash." + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogReg.this, "Failed connection " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("RegisterName",RegisterName);
                params.put("RegisterSurname",RegisterSurname);
                params.put("RegisterAFM",RegisterAFM);
                params.put("RegisterEmail",RegisterEmail);
                params.put("RegisterPassword",RegisterPassword);
                params.put("RegisterGender",RegisterGender);
                params.put("RegisterLandLine",RegisterLandLine);
                params.put("RegisterCellPhone",RegisterCellPhone);
                params.put("RegisterStreetAddress",RegisterStreetAddress);
                params.put("RegisterNumber",RegisterNumber);
                params.put("RegisterPostalCode",RegisterPostalCode);
                params.put("RegisterBirthDate",RegisterBirthDate);
                params.put("RegisterNationality",RegisterNationality);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void Login(final String email, final String password,final String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if(response.isEmpty()){
                            Toast.makeText(LogReg.this,
                                    "This account does not exist.", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                JSONArray jsonArray = jsonObject.getJSONArray("login");

                                if (success.equals("1")) {
                                    String name = "0";
                                    String email = "0";
                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject object = jsonArray.getJSONObject(i);

                                        name = object.getString("name").trim();

                                        email = object.getString("email").trim();

                                        Toast.makeText(LogReg.this,
                                                "Success Login. Welcome "+name, Toast.LENGTH_SHORT)
                                                .show();

                                    }
                                    if (url.equals(Login_URL)) {
                                        Intent LoginEmployee = new Intent(LogReg.this, Worker.class);
                                        LoginEmployee.putExtra("DeadMauFive",name);
                                        startActivity(LoginEmployee);

                                    }
                                    else{
                                        Intent LoginOwner = new Intent(LogReg.this, OWSLA.class);
                                        LoginOwner.putExtra("DeadMau5", name);
                                        startActivity(LoginOwner);
                                    }

                                }else{
                                    Toast.makeText(LogReg.this,
                                            "Username or password is wrong try again by typing more carefully.", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(LogReg.this, "An Error came through" +e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogReg.this, "Failed connection" +error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void ClearText(){
        LoginName.setText("");
        LoginPassword.setText("");
        RegisterName.setText("");
        RegisterSurname.setText("");
        RegisterAFM.setText("");
        RegisterEmail.setText("");
        RegisterPassword.setText("");
        RegisterConfirmPassword.setText("");
        RegisterGender.setText("");
        RegisterLandLine.setText("");
        RegisterCellPhone.setText("");
        RegisterStreetAddress.setText("");
        RegisterNumber.setText("");
        RegisterPostalCode.setText("");
        RegisterTeamCode.setText("");
        RegisterBirthDate.setText("");
        RegisterNationality.setText("");
        RegisterWorkHours.setText("");
        RegisterShiftType.setText("");
        ForgetName.setText("");
    }
}
