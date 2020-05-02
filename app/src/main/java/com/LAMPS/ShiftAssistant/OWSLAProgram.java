package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateUtils;
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

import static java.lang.Boolean.FALSE;

public class OWSLAProgram extends AppCompatActivity {

    protected ArrayList<String> Shifts = new ArrayList<>();
    protected ArrayList<JSONObject> Employees = new ArrayList<>();
    protected ArrayList<JSONObject> Morning = new ArrayList<>();
    protected ArrayList<JSONObject> Noon = new ArrayList<>();
    protected ArrayList<JSONObject> Night = new ArrayList<>();
    protected int TotalEmployees = 0, TotalWorkHours = 0, finalMonday = 0,  finalTuesday = 0,  finalWednesday = 0,  finalThursday = 0,  finalFriday = 0,  finalSaturday = 0,  finalSunday = 0 ;
    protected double MorningRate = 0, NoonRate = 0, NightRate = 0;

    EditText OWSLAProgramMondayMorning , OWSLAProgramMondayNoon , OWSLAProgramMondayNight , OWSLAProgramTuesdayMorning ,
            OWSLAProgramTuesdayNoon , OWSLAProgramTuesdayNight , OWSLAProgramWednesdayMorning , OWSLAProgramWednesdayNoon ,
            OWSLAProgramWednesdayNight , OWSLAProgramThursdayMorning , OWSLAProgramThursdayNoon , OWSLAProgramThursdayNight ,
            OWSLAProgramFridayMorning , OWSLAProgramFridayNoon , OWSLAProgramFridayNight , OWSLAProgramSaturdayMorning ,
            OWSLAProgramSaturdayNoon , OWSLAProgramSaturdayNight , OWSLAProgramSundayMorning , OWSLAProgramSundayNoon ,
            OWSLAProgramSundayNight ,OWSLAProgramTotalEmployees , OWSLAProgramTotalEmployeesHours , OWSLAProgramPercentageMorning , OWSLAProgramPercentageNoon ,
            OWSLAProgramPercentageNight;

    Button OWSLAProgramConfirm;

    private static final String File_Name = "AlgorithmTest.json";
    private static final String GeneratedSchedule = "Program.json";
    private String FetchEmployeesData_URL = "http://192.168.1.8/Shifts/FetchEmployees.php";
    final String VAC_Check_URL = "http://192.168.1.8/Shifts/ProgramGen.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla_program);
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
        String UserName = getIntent().getStringExtra("DeadMau5");
        final String UserEmail = getIntent().getStringExtra("GOATdm5");



        OWSLAProgramConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shifts.clear();
                GetText();
                if(!weHaveMissingFields()) {
                    if(restriction(Shifts)){
                        FetchEmployeesData(UserEmail);
                        ProgramEntry(finalMonday,finalTuesday,finalWednesday,finalThursday,finalFriday,finalSaturday,finalSunday);
                        vacationcheck(UserEmail);
                        ClearText();
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
        this.OWSLAProgramPercentageMorning.setText(String.format("%.2f ",(this.MorningRate/this.TotalEmployees)*100)+"%");
        this.OWSLAProgramPercentageNoon.setText(String.format("%.2f ",(this.NoonRate/this.TotalEmployees)*100)+"%");
        this.OWSLAProgramPercentageNight.setText(String.format("%.2f ",(this.NightRate/this.TotalEmployees)*100)+"%");

    }
    public void get_json(){
        String json;
        try {
            InputStream is = openFileInput(File_Name);
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
    public void CalculateEmployeesRequirements(){
        String json;
        try {
            InputStream is = openFileInput(File_Name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONObject object = new JSONObject(json);

            getEmployees(object.getJSONArray("Employees").length());

            for(int i = 0; i < object.getJSONArray("Employees").length();i++) {
                if(object.getJSONArray("Employees").getJSONObject(i).getString("Shift_Type").equals("MORNING")){
                    this.MorningRate++;
                    //   this.Morning.add(object.getJSONArray("Employees").getJSONObject(i));
                } else if (object.getJSONArray("Employees").getJSONObject(i).getString("Shift_Type").equals("NOON")) {
                    this.NoonRate++;
                    //    this.Noon.add(object.getJSONArray("Employees").getJSONObject(i));
                } else {
                    this.NightRate++;
                    //   this.Night.add(object.getJSONArray("Employees").getJSONObject(i));
                }
                this.TotalWorkHours = Integer.valueOf(object.getJSONArray("Employees").getJSONObject(i).getString("WorkHours")) + this.TotalWorkHours;
                this.Employees.add(object.getJSONArray("Employees").getJSONObject(i));
//                if (object.getJSONArray("Employees").getJSONObject(i).getString("VacationStatus").equals("1")) {
//                    this.Employees.remove(i);
//                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
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
    public void getEmployees(int total){
        this.TotalEmployees = total;
    }
    private void ProgramEntry(int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday){
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
            fos = openFileOutput(GeneratedSchedule,MODE_PRIVATE);
//            fos.write(skt.getBytes());
            try {
                object.put("Monday",Monday);
                object.put("Tuesday",Tuesday);
                object.put("Wednesday",Wednesday);
                object.put("Thursday",Thursday);
                object.put("Friday",Friday);
                object.put("Saturday",Saturday);
                object.put("Sunday",Sunday);


                for(int i = 0; i<monday; i++){
                    Monday.put(this.Employees.get(i));
                }
                for(int i = 0; i<tuesday; i++){
                    Tuesday.put(this.Employees.get(i));
                }
                for(int i = 0; i<wednesday; i++){
                    Wednesday.put(this.Employees.get(i));
                }
                for(int i = 0; i<thursday; i++){
                    Thursday.put(this.Employees.get(i));
                }
                for(int i = 0; i<friday; i++){
                    Friday.put(this.Employees.get(i));
                }
                for(int i = 0; i<saturday; i++){
                    Saturday.put(this.Employees.get(i));
                }
                for(int i = 0; i<sunday; i++){
                    Sunday.put(this.Employees.get(i));
                }

                fos.write(object.toString().getBytes());
                fos.flush();
                Toast.makeText(this,"saved to " + getFilesDir() + "/" + GeneratedSchedule,Toast.LENGTH_LONG).show();
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


    private void FetchEmployeesData(final String Email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, FetchEmployeesData_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.isEmpty()){
                            Toast.makeText(OWSLAProgram.this,
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
                                    Toast.makeText(OWSLAProgram.this,
                                            "There are no records in the Database", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(OWSLAProgram.this, "An Error came through" +e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OWSLAProgram.this, "Failed connection" +error.toString(), Toast.LENGTH_SHORT).show();

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

    private void vacationcheck(final String Email){

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
}

//                OverTime(monday,Monday);
//                OverTime(tuesday,Tuesday);
//                OverTime(wednesday,Wednesday);
//                OverTime(thursday,Thursday);
//                OverTime(friday,Friday);
//                OverTime(saturday,Saturday);
//                OverTime(sunday,Sunday);
//    private void OverTime(int day,JSONArray Day){
//
//        for(int i = 0; i<this.Morning.size(); i++){
//            Day.put(this.Employees.get(i));
//        }
//        for(int i = 0; i<this.Noon.size(); i++){
//            Day.put(this.Employees.get(i));
//        }
//        for(int i = 0; i<this.Night.size(); i++){
//            Day.put(this.Employees.get(i));
//        }
//        remaining = this.TotalEmployees - day;
//        if(remaining > 0){
//            for(int i = 0; i<remaining; i++){
//                Day.put(this.Employees.get(i));
//            }
//        }
//    }
