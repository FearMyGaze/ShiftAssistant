package com.LAMPS.ShiftAssistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;


public class OWSLAWorker extends AppCompatActivity {
    //Popup Menu's

    EditText OWSLAWorkerName, OWSLAWorkerSurname , OWSLAWorkerAFM , OWSLAWorkerEmail , OWSLAWorkerPassword , OWSLAWorkerGender ,
            OWSLAWorkerLandLine , OWSLAWorkerCellPhone , OWSLAWorkerStreetAddress , OWSLAWorkerNumber , OWSLAWorkerPostalCode ,
            OWSLAWorkerTeamCode , OWSLAWorkerBirthDate , OWSLAWorkerNationality , OWSLAWorkerWorkHours ,
            OWSLAWorkerSearchBox;

    Button OWSLAWorkerConfirmButton , OWSLAWorkerSearchButton;

    ImageButton OWSLAWorkerPrevious , OWSLAWorkerNext , OWSLAWorkerAdd , OWSLAWorkerRemove;

    //String Update's

    private String PHP_OWSLAWorkerName , PHP_OWSLAWorkerSurname , PHP_OWSLAWorkerAFM , PHP_OWSLAWorkerEmail , PHP_OWSLAWorkerPassword ,
            PHP_OWSLAWorkerGender , PHP_OWSLAWorkerLandLine , PHP_OWSLAWorkerCellPhone , PHP_OWSLAWorkerStreetAddress ,
            PHP_OWSLAWorkerNumber , PHP_OWSLAWorkerPostalCode , PHP_OWSLAWorkerTeamCode , PHP_OWSLAWorkerBirthDate , PHP_OWSLANationality,
            PHP_OWSLAWorkerWorkerHours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owsla_worker);

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
        OWSLAWorkerConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { PHP_Update(); ClearText();
            }
        });
        OWSLAWorkerSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GUI_Update();
            }
        });
        OWSLAWorkerPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreviousWorker();
            }
        });
        OWSLAWorkerNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextWorker();
            }
        });
        OWSLAWorkerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWorker();
            }
        });
        OWSLAWorkerRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveWorker();
            }
        });




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

    }
    public void PHP_Update() {
        PHP_OWSLAWorkerName = this.OWSLAWorkerName.getText().toString().trim();
        PHP_OWSLAWorkerSurname = this.OWSLAWorkerSurname.getText().toString().trim();
        PHP_OWSLAWorkerAFM = this.OWSLAWorkerAFM.getText().toString().trim();
        PHP_OWSLAWorkerEmail = this.OWSLAWorkerEmail.getText().toString().trim();
        PHP_OWSLAWorkerPassword = this.OWSLAWorkerPassword.getText().toString().trim();
        PHP_OWSLAWorkerGender = this.OWSLAWorkerGender.getText().toString().trim();
        PHP_OWSLAWorkerLandLine = this.OWSLAWorkerLandLine.getText().toString().trim();
        PHP_OWSLAWorkerCellPhone = this.OWSLAWorkerCellPhone.getText().toString().trim();
        PHP_OWSLAWorkerStreetAddress = this.OWSLAWorkerStreetAddress.getText().toString().trim();
        PHP_OWSLAWorkerNumber = this.OWSLAWorkerNumber.getText().toString().trim();
        PHP_OWSLAWorkerPostalCode = this.OWSLAWorkerPostalCode.getText().toString().trim();
        PHP_OWSLAWorkerTeamCode = this.OWSLAWorkerTeamCode.getText().toString().trim();
        PHP_OWSLAWorkerBirthDate = this.OWSLAWorkerBirthDate.getText().toString().trim();
        PHP_OWSLANationality = this.OWSLAWorkerNationality.getText().toString().trim();
        PHP_OWSLAWorkerWorkerHours = this.OWSLAWorkerWorkHours.getText().toString().trim();


    }

    public void GUI_Update(){
        this.OWSLAWorkerName.setText(PHP_OWSLAWorkerName);
        this.OWSLAWorkerSurname.setText(PHP_OWSLAWorkerSurname);
        this.OWSLAWorkerAFM.setText(PHP_OWSLAWorkerAFM);
        this.OWSLAWorkerEmail.setText(PHP_OWSLAWorkerEmail);
        this.OWSLAWorkerPassword.setText(PHP_OWSLAWorkerPassword);
        this.OWSLAWorkerGender.setText(PHP_OWSLAWorkerGender);
        this.OWSLAWorkerLandLine.setText(PHP_OWSLAWorkerLandLine);
        this.OWSLAWorkerCellPhone.setText(PHP_OWSLAWorkerCellPhone);
        this.OWSLAWorkerStreetAddress.setText(PHP_OWSLAWorkerStreetAddress);
        this.OWSLAWorkerNumber.setText(PHP_OWSLAWorkerNumber);
        this.OWSLAWorkerPostalCode.setText(PHP_OWSLAWorkerPostalCode);
        this.OWSLAWorkerTeamCode.setText(PHP_OWSLAWorkerTeamCode);
        this.OWSLAWorkerBirthDate.setText(PHP_OWSLAWorkerBirthDate);
        this.OWSLAWorkerNationality.setText(PHP_OWSLANationality);
        this.OWSLAWorkerWorkHours.setText(PHP_OWSLAWorkerWorkerHours);
    }

    public void NextWorker(){

    }
    public void PreviousWorker(){

    }
    public void AddWorker(){

    }
    public void RemoveWorker(){

    }
    public void ClearText(){
        OWSLAWorkerName.setText("");
        OWSLAWorkerSurname.setText("");
        OWSLAWorkerAFM.setText("");
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
