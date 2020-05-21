package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Random;

public class OWSLAProgram extends AppCompatActivity {

    //Array's

    protected ArrayList<String> Shifts = new ArrayList<>();
    protected ArrayList<JSONObject> Employees = new ArrayList<>();

    protected ArrayList<JSONObject> MorningEmployees = new ArrayList<>();
    protected ArrayList<JSONObject> NoonEmployees = new ArrayList<>();
    protected ArrayList<JSONObject> NightEmployees = new ArrayList<>();

    //Program Variables

    protected int TotalEmployees = 0 , TotalWorkHours = 0 , finalMonday = 0 ,  finalTuesday = 0 ,  finalWednesday = 0 ,
            finalThursday = 0 ,  finalFriday = 0 , finalSaturday = 0 ,  finalSunday = 0;

    protected double MorningRate = 0 , NoonRate = 0 , NightRate = 0;

    String UserName;
    String UserEmail;

    //Program EditText's

    EditText OWSLAProgramMondayMorning , OWSLAProgramMondayNoon , OWSLAProgramMondayNight , OWSLAProgramTuesdayMorning ,
            OWSLAProgramTuesdayNoon , OWSLAProgramTuesdayNight , OWSLAProgramWednesdayMorning , OWSLAProgramWednesdayNoon ,
            OWSLAProgramWednesdayNight , OWSLAProgramThursdayMorning , OWSLAProgramThursdayNoon , OWSLAProgramThursdayNight ,
            OWSLAProgramFridayMorning , OWSLAProgramFridayNoon , OWSLAProgramFridayNight , OWSLAProgramSaturdayMorning ,
            OWSLAProgramSaturdayNoon , OWSLAProgramSaturdayNight , OWSLAProgramSundayMorning , OWSLAProgramSundayNoon ,
            OWSLAProgramSundayNight ,OWSLAProgramTotalEmployees , OWSLAProgramTotalEmployeesHours , OWSLAProgramPercentageMorning ,
            OWSLAProgramPercentageNoon , OWSLAProgramPercentageNight;

    //Program Button's
    boolean Rates2NumberMorning = true;
    boolean Rates2NumberNoon = true;
    boolean Rates2NumberNight = true;
    Button OWSLAProgramConfirm;

    //URL

    private String VAC_Check_URL;

    //Links

    GlobalVariables Links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla_program);


        Links = new GlobalVariables();

        Shifts.clear();

        //EditText's

        OWSLAProgramMondayMorning = findViewById(R.id.OWSLAProgramMondayMorning);
        OWSLAProgramMondayNoon = findViewById(R.id.OWSLAProgramMondayNoon);
        OWSLAProgramMondayNight = findViewById(R.id.OWSLAProgramMondayNight);
        OWSLAProgramTuesdayMorning = findViewById(R.id.OWSLAProgramTuesdayMorning);
        OWSLAProgramTuesdayNoon = findViewById(R.id.OWSLAProgramTuesdayNoon);
        OWSLAProgramTuesdayNight = findViewById(R.id.OWSLAProgramTuesdayNight);
        OWSLAProgramWednesdayMorning = findViewById(R.id.OWSLAProgramWednesdayMorning);
        OWSLAProgramWednesdayNoon = findViewById(R.id.OWSLAProgramWednesdayNoon);
        OWSLAProgramWednesdayNight = findViewById(R.id.OWSLAProgramWednesdayNight);
        OWSLAProgramThursdayMorning = findViewById(R.id.OWSLAProgramThursdayMorning);
        OWSLAProgramThursdayNoon = findViewById(R.id.OWSLAProgramThursdayNoon);
        OWSLAProgramThursdayNight = findViewById(R.id.OWSLAProgramThursdayNight);
        OWSLAProgramFridayMorning = findViewById(R.id.OWSLAProgramFridayMorning);
        OWSLAProgramFridayNoon = findViewById(R.id.OWSLAProgramFridayNoon);
        OWSLAProgramFridayNight = findViewById(R.id.OWSLAProgramFridayNight);
        OWSLAProgramSaturdayMorning = findViewById(R.id.OWSLAProgramSaturdayMorning);
        OWSLAProgramSaturdayNoon = findViewById(R.id.OWSLAProgramSaturdayNoon);
        OWSLAProgramSaturdayNight = findViewById(R.id.OWSLAProgramSaturdayNight);
        OWSLAProgramSundayMorning = findViewById(R.id.OWSLAProgramSundayMorning);
        OWSLAProgramSundayNoon = findViewById(R.id.OWSLAProgramSundayNoon);
        OWSLAProgramSundayNight = findViewById(R.id.OWSLAProgramSundayNight);
        OWSLAProgramTotalEmployees = findViewById(R.id.OWSLAProgramTotalEmployees);
        OWSLAProgramTotalEmployeesHours = findViewById(R.id.OWSLAProgramTotalEmployeesHours);
        OWSLAProgramPercentageMorning = findViewById(R.id.OWSLAProgramPercentageMorning);
        OWSLAProgramPercentageNoon = findViewById(R.id.OWSLAProgramPercentageNoon);
        OWSLAProgramPercentageNight = findViewById(R.id.OWSLAProgramPercentageNight);

        //Button's

        OWSLAProgramConfirm = findViewById(R.id.OWSLAProgramConfirm);

        //Intend's

        UserName = getIntent().getStringExtra("DeadMau5");
        UserEmail = getIntent().getStringExtra("GOATdm5");

        //Links
        VAC_Check_URL = Links.getProgramGenerate_URL();

        //ClickListener's
        OWSLAProgramConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int scenario;
                Shifts.clear();
                GetText();
                if(!weHaveMissingFields()) {
                    if(restriction(Shifts)){
                        scenario = ProgramEntry();
                        switch(scenario){
                            case 0:
                                VacationCheck(UserEmail);
                                fetchProgram();
                                ClearText();
                                break;
                            case 3:
                                Toast.makeText(getApplicationContext(),"You have exceeded the maximum number of Morning Shift Employees",Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                Toast.makeText(getApplicationContext(),"You have exceeded the maximum number of Noon Shift Employees",Toast.LENGTH_SHORT).show();
                                break;
                            case 15:
                                Toast.makeText(getApplicationContext(),"You have exceeded the maximum number of Night Shift Employees",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Υou have exceeded the limit of available employees, Please check the fields and try again",Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }

            }
        });

        CalculateEmployeesRequirements();
        this.OWSLAProgramTotalEmployees.setText(String.valueOf(this.TotalEmployees));
        this.OWSLAProgramTotalEmployeesHours.setText(String.valueOf(this.TotalWorkHours));

        OWSLAProgramPercentageMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Rates2NumberMorning) {
                    OWSLAProgramPercentageMorning.setText((int) MorningRate + "/" + TotalEmployees);
                    Rates2NumberMorning = false;
                } else {
                    OWSLAProgramPercentageMorning.setText(String.format("%.2f ",(MorningRate/TotalEmployees)*100)+"%");
                    Rates2NumberMorning = true;
                }

            }
        });

        OWSLAProgramPercentageNoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Rates2NumberNoon){
                    OWSLAProgramPercentageNoon.setText((int)NoonRate + "/" + TotalEmployees);
                    Rates2NumberNoon = false;
                }else {
                    OWSLAProgramPercentageNoon.setText(String.format("%.2f ",(NoonRate/TotalEmployees)*100)+"%");
                    Rates2NumberNoon = true;
                }
            }
        });

        OWSLAProgramPercentageNight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Rates2NumberNight){
                    OWSLAProgramPercentageNight.setText((int)NightRate + "/" + TotalEmployees);
                    Rates2NumberNight = false;
                }else{
                    OWSLAProgramPercentageNight.setText(String.format("%.2f ",(NightRate/TotalEmployees)*100)+"%");
                    Rates2NumberNight = true;
                }
            }
        });

        this.OWSLAProgramPercentageMorning.setText(String.format("%.2f ",(this.MorningRate/this.TotalEmployees)*100)+"%");
        this.OWSLAProgramPercentageNoon.setText(String.format("%.2f ",(this.NoonRate/this.TotalEmployees)*100)+"%");
        this.OWSLAProgramPercentageNight.setText(String.format("%.2f ",(this.NightRate/this.TotalEmployees)*100)+"%");

    }

    public void GetText(){
        this.Shifts.add(OWSLAProgramMondayMorning.getText().toString().trim());
        this.Shifts.add(OWSLAProgramMondayNoon.getText().toString().trim());
        this.Shifts.add(OWSLAProgramMondayNight.getText().toString().trim());
        this.Shifts.add(OWSLAProgramTuesdayMorning.getText().toString().trim());
        this.Shifts.add(OWSLAProgramTuesdayNoon.getText().toString().trim());
        this.Shifts.add(OWSLAProgramTuesdayNight.getText().toString().trim());
        this.Shifts.add(OWSLAProgramWednesdayMorning.getText().toString().trim());
        this.Shifts.add(OWSLAProgramWednesdayNoon.getText().toString().trim());
        this.Shifts.add(OWSLAProgramWednesdayNight.getText().toString().trim());
        this.Shifts.add(OWSLAProgramThursdayMorning.getText().toString().trim());
        this.Shifts.add(OWSLAProgramThursdayNoon.getText().toString().trim());
        this.Shifts.add(OWSLAProgramThursdayNight.getText().toString().trim());
        this.Shifts.add(OWSLAProgramFridayMorning.getText().toString().trim());
        this.Shifts.add(OWSLAProgramFridayNoon.getText().toString().trim());
        this.Shifts.add(OWSLAProgramFridayNight.getText().toString().trim());
        this.Shifts.add(OWSLAProgramSaturdayMorning.getText().toString().trim());
        this.Shifts.add(OWSLAProgramSaturdayNoon.getText().toString().trim());
        this.Shifts.add(OWSLAProgramSaturdayNight.getText().toString().trim());
        this.Shifts.add(OWSLAProgramSundayMorning.getText().toString().trim());
        this.Shifts.add(OWSLAProgramSundayNoon.getText().toString().trim());
        this.Shifts.add(OWSLAProgramSundayNight.getText().toString().trim());
    }

    public boolean weHaveMissingFields(){
        boolean result = false;
        for(int i = 0; i < Shifts.size(); i++){
            if(this.Shifts.get(i).isEmpty()) {
                result = true;
            }
        }
        return result;
    }


    public void CalculateEmployeesRequirements(){
        String json;
        try {
            InputStream is = openFileInput(Links.getFileEmployees());
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONObject object = new JSONObject(json);

            getEmployees(object.getJSONArray("Employees").length());

            for(int i = 0; i < object.getJSONArray("Employees").length();i++) {
                if(object.getJSONArray("Employees").getJSONObject(i).getString("ShiftType").equals("MORNING")){
                    this.MorningRate++;
                    this.MorningEmployees.add(object.getJSONArray("Employees").getJSONObject(i));
                } else if (object.getJSONArray("Employees").getJSONObject(i).getString("ShiftType").equals("NOON")) {
                    this.NoonRate++;
                    this.NoonEmployees.add(object.getJSONArray("Employees").getJSONObject(i));
                } else {
                    this.NightRate++;
                    this.NightEmployees.add(object.getJSONArray("Employees").getJSONObject(i));
                }
                this.TotalWorkHours = Integer.valueOf(object.getJSONArray("Employees").getJSONObject(i).getString("WorkHours")) + this.TotalWorkHours;
                this.Employees.add(object.getJSONArray("Employees").getJSONObject(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getEmployees(int total){
        this.TotalEmployees = total;
    }

    public boolean restriction(ArrayList<String> DecoyShifts){
        this.finalMonday = 0;
        this.finalTuesday = 0;
        this.finalWednesday = 0;
        this.finalThursday = 0;
        this.finalFriday = 0;
        this.finalSaturday = 0;
        this.finalSunday = 0;
        for(int i = 0; i < DecoyShifts.size(); i++){
            if(i <= 2){
                this.finalMonday = Integer.valueOf(DecoyShifts.get(i)) + this.finalMonday;
            } else if(i <= 5){
                this.finalTuesday= Integer.valueOf(DecoyShifts.get(i)) + this.finalTuesday;
            }
            else if(i <= 8){
                this.finalWednesday = Integer.valueOf(DecoyShifts.get(i)) + this.finalWednesday;
            }
            else if(i <= 11){
                this.finalThursday = Integer.valueOf(DecoyShifts.get(i)) + this.finalThursday;
            }
            else if(i <= 14){
                this.finalFriday = Integer.valueOf(DecoyShifts.get(i)) + this.finalFriday;
            }
            else if(i <= 17){
                this.finalSaturday = Integer.valueOf(DecoyShifts.get(i)) + this.finalSaturday;
            }
            else {
                this.finalSunday = Integer.valueOf(DecoyShifts.get(i)) + this.finalSunday;
            }
        }
        if(finalMonday > this.TotalEmployees || finalTuesday > this.TotalEmployees || finalWednesday > this.TotalEmployees || finalThursday > this.TotalEmployees || finalFriday > this.TotalEmployees || finalSaturday > this.TotalEmployees || finalSunday > this.TotalEmployees){
            return false;
        }
        else{
            return true;
        }
    }

    private int FillRandomEmployees(JSONArray Day,int shiftSize,int a){
        int result = 0;
        Random randomas = new Random();
        int rEmployee;
        switch (a){
            case 0:
            case 3:
            case 6:
            case 9:
            case 12:
            case 15:
            case 18:

                if(Integer.valueOf(this.Shifts.get(a)) <= this.MorningEmployees.size()){
                    for (int i = 0; i < shiftSize; i++) {
                        rEmployee = randomas.nextInt(MorningEmployees.size());
                        Day.put(MorningEmployees.get(rEmployee));
                        MorningEmployees.remove(MorningEmployees.remove(rEmployee));
                    }
                    result = 0;
                } else {
                    result = 3;
                }

                break;
            case 1:
            case 4:
            case 7:
            case 10:
            case 13:
            case 16:
            case 19:

                if(Integer.valueOf(this.Shifts.get(a)) <= this.NoonEmployees.size()){
                    for (int i = 0; i < shiftSize; i++) {
                        rEmployee = randomas.nextInt(NoonEmployees.size());
                        Day.put(NoonEmployees.get(rEmployee));
                        NoonEmployees.remove(NoonEmployees.remove(rEmployee));
                    }
                    result = 0;
                } else {
                    result = 5;
                }

                break;
            case 2:
            case 5:
            case 8:
            case 11:
            case 14:
            case 17:
            case 20:

                if(Integer.valueOf(this.Shifts.get(a)) <= this.NightEmployees.size()){
                    for (int i = 0; i < shiftSize; i++) {
                        rEmployee = randomas.nextInt(NightEmployees.size());
                        Day.put(NightEmployees.get(rEmployee));
                        NightEmployees.remove(NightEmployees.remove(rEmployee));
                    }
                    result = 0;
                } else {
                    result = 15;
                }

                break;
        }
        return result;
    }

    private int ProgramEntry(){
        dropLastProgram();
        int let = 0;
        FileOutputStream fos = null;
        JSONObject object = new JSONObject();
        JSONArray Monday = new JSONArray();
        JSONArray Tuesday = new JSONArray();
        JSONArray Wednesday = new JSONArray();
        JSONArray Thursday = new JSONArray();
        JSONArray Friday = new JSONArray();
        JSONArray Saturday = new JSONArray();
        JSONArray Sunday = new JSONArray();
        try
        {
            fos = openFileOutput(Links.getFileProgram(),MODE_PRIVATE);
//            fos.write(skt.getBytes());
            try {
                object.put("Monday",Monday);
                object.put("Tuesday",Tuesday);
                object.put("Wednesday",Wednesday);
                object.put("Thursday",Thursday);
                object.put("Friday",Friday);
                object.put("Saturday",Saturday);
                object.put("Sunday",Sunday);

                for(int i = 0 ; i < Shifts.size() ; i++){
                    if(i <= 2){
                        let = FillRandomEmployees(Monday, Integer.valueOf(Shifts.get(i)), i);
                        if(let != 0){
                            return let;
                        } else {
                            refreshShifts();
                        }
                        //checkFor2ble(Monday);
                    }  else if(i <= 5){
                        let = FillRandomEmployees(Tuesday, Integer.valueOf(Shifts.get(i)), i);
                        if(let != 0){
                            return let;
                        } else {
                            refreshShifts();
                        }
                        //checkFor2ble(Tuesday);
                    } else if(i <= 8){
                        let = FillRandomEmployees(Wednesday, Integer.valueOf(Shifts.get(i)), i);
                        if(let != 0){
                            return let;
                        } else {
                            refreshShifts();
                        }
                        //checkFor2ble(Wednesday);
                    } else if(i <= 11){
                        let = FillRandomEmployees(Thursday, Integer.valueOf(Shifts.get(i)), i);
                        if(let != 0){
                            return let;
                        } else {
                            refreshShifts();
                        }
                        //checkFor2ble(Thursday);
                    } else if(i <= 14){
                        let = FillRandomEmployees(Friday, Integer.valueOf(Shifts.get(i)), i);
                        if(let != 0){
                            return let;
                        } else {
                            refreshShifts();
                        }
                        //checkFor2ble(Friday);
                    } else if(i <= 17){
                        let = FillRandomEmployees(Saturday, Integer.valueOf(Shifts.get(i)), i);
                        if(let != 0){
                            return let;
                        } else {
                            refreshShifts();
                        }
                        //checkFor2ble(Saturday);
                    } else {
                        let = FillRandomEmployees(Sunday, Integer.valueOf(Shifts.get(i)), i);
                        if(let != 0){
                            return let;
                        } else {
                            refreshShifts();
                        }
                        //checkFor2ble(Sunday);
                    }
                }
                fos.write(object.toString().getBytes());
                fos.flush();
                Toast.makeText(this,"saved to " + getFilesDir() + "/" + Links.getFileProgram(),Toast.LENGTH_LONG).show();

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

        return let;
    }

    public void refreshShifts(){

        this.MorningEmployees.clear();

        this.NoonEmployees.clear();

        this.NightEmployees.clear();

        CalculateEmployeesRequirements();
    }

    public void checkFor2ble(JSONArray day){
        for (int i = 0; i < day.length(); i++) {
            for (int j = i + 1 ; j < day.length(); j++) {
                try {
                    if (day.getJSONObject(i).toString().equals(day.getJSONObject(j).toString())) {
                        System.out.println("Double = "+day.getJSONObject(i).toString()+" = "+day.getJSONObject(j).toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ClearText(){
        OWSLAProgramMondayMorning.setText("");
        OWSLAProgramMondayNoon.setText("");
        OWSLAProgramMondayNight.setText("");
        OWSLAProgramTuesdayMorning.setText("");
        OWSLAProgramTuesdayNoon.setText("");
        OWSLAProgramTuesdayNight.setText("");
        OWSLAProgramWednesdayMorning.setText("");
        OWSLAProgramWednesdayNoon.setText("");
        OWSLAProgramWednesdayNight.setText("");
        OWSLAProgramThursdayMorning.setText("");
        OWSLAProgramThursdayNoon.setText("");
        OWSLAProgramThursdayNight.setText("");
        OWSLAProgramFridayMorning.setText("");
        OWSLAProgramFridayNoon.setText("");
        OWSLAProgramFridayNight.setText("");
        OWSLAProgramSaturdayMorning.setText("");
        OWSLAProgramSaturdayNoon.setText("");
        OWSLAProgramSaturdayNight.setText("");
        OWSLAProgramSundayMorning.setText("");
        OWSLAProgramSundayNoon.setText("");
        OWSLAProgramSundayNight.setText("");

    }

    //Rules

    private void VacationCheck(final String Email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  VAC_Check_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Program generated successfully", Toast.LENGTH_LONG).show();
                            }
                            else if (success.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Program generated unsuccessfully", Toast.LENGTH_LONG).show();
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

    private void fetchProgram(){
        String workerID,Shift;
        String json;
        try {
            InputStream is = openFileInput(Links.getFileProgram());
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONObject object = new JSONObject(json);

            for(int i = 0; i < object.getJSONArray("Monday").length();i++) {
                workerID = object.getJSONArray("Monday").getJSONObject(i).get("ID").toString();
                Shift = object.getJSONArray("Monday").getJSONObject(i).get("ShiftType").toString();
                uploadLastProgram("Monday",workerID,this.UserEmail,Shift);
            }
            for(int i = 0; i < object.getJSONArray("Tuesday").length();i++) {
                workerID = object.getJSONArray("Tuesday").getJSONObject(i).get("ID").toString();
                Shift = object.getJSONArray("Tuesday").getJSONObject(i).get("ShiftType").toString();
                uploadLastProgram("Tuesday",workerID,this.UserEmail,Shift);
            }
            for(int i = 0; i < object.getJSONArray("Wednesday").length();i++) {
                workerID = object.getJSONArray("Wednesday").getJSONObject(i).get("ID").toString();
                Shift = object.getJSONArray("Wednesday").getJSONObject(i).get("ShiftType").toString();
                uploadLastProgram("Wednesday",workerID,this.UserEmail,Shift);
            }
            for(int i = 0; i < object.getJSONArray("Thursday").length();i++) {
                workerID = object.getJSONArray("Thursday").getJSONObject(i).get("ID").toString();
                Shift = object.getJSONArray("Thursday").getJSONObject(i).get("ShiftType").toString();
                uploadLastProgram("Thursday",workerID,this.UserEmail,Shift);
            }
            for(int i = 0; i < object.getJSONArray("Friday").length();i++) {
                workerID = object.getJSONArray("Friday").getJSONObject(i).get("ID").toString();
                Shift = object.getJSONArray("Friday").getJSONObject(i).get("ShiftType").toString();
                uploadLastProgram("Friday",workerID,this.UserEmail,Shift);
            }
            for(int i = 0; i < object.getJSONArray("Saturday").length();i++) {
                workerID = object.getJSONArray("Saturday").getJSONObject(i).get("ID").toString();
                Shift = object.getJSONArray("Saturday").getJSONObject(i).get("ShiftType").toString();
                uploadLastProgram("Saturday",workerID,this.UserEmail,Shift);
            }
            for(int i = 0; i < object.getJSONArray("Sunday").length();i++) {
                workerID = object.getJSONArray("Sunday").getJSONObject(i).get("ID").toString();
                Shift = object.getJSONArray("Sunday").getJSONObject(i).get("ShiftType").toString();
                uploadLastProgram("Sunday",workerID,this.UserEmail,Shift);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void uploadLastProgram(final String nameOfTheDay, final String ID, final String Email, final String Shift){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Links.getUploadProgramToDB_URL(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Error during uploading", Toast.LENGTH_LONG).show();
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
                params.put("nameOfTheDay",nameOfTheDay);
                params.put("ID",ID);
                params.put("Email",Email);
                params.put("Shift",Shift);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void get_json(){
        String json;
        try {
            InputStream is = openFileInput(Links.getFileEmployees());
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONObject object = new JSONObject(json);
            //print's the line
            // System.out.println(object.getJSONArray("Employees").getString(0));
            for(int i = 0; i < object.getJSONArray("Employees").length();i++) {
                System.out.println(object.getJSONArray("Employees").getJSONObject(i).getString("ID"));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void dropLastProgram() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.getDropLastProgram_URL(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "There are no older program generations", Toast.LENGTH_LONG).show();
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
                }) {
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