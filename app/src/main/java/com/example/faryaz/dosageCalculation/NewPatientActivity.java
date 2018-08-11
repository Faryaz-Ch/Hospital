package com.example.faryaz.anglesea;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;

import java.util.List;

/**
 * Created by faryaz on 6/13/2018.
 */

public class NewPatientActivity  extends AppCompatActivity{

    MyDBHandler db = new MyDBHandler(this);
    EditText editTextFirstName, editTextLastName, editTextNhiNumber, editTextDob, editTextWeight;
    Spinner spinnerRoom;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button addPatient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName= (EditText) findViewById(R.id.editTextLastName);
        editTextNhiNumber = (EditText) findViewById(R.id.editTextNhiNumber);
        editTextDob = (EditText) findViewById(R.id.editTextDOB);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioButton = (RadioButton) findViewById(R.id.rbMale);

        spinnerRoom = (Spinner) findViewById(R.id.spinnerRoom);
        fillSpinnerRoom();

        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });


        Button addPatient = (Button) findViewById(R.id.btnAddPatient);
        addPatient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int checkedRadioBtnId = radioGroup.getCheckedRadioButtonId();
                String first_name = editTextFirstName.getText().toString();
                String last_name = editTextLastName.getText().toString();
                String nhi_number = editTextNhiNumber.getText().toString();
                String dob = editTextDob.getText().toString();
                String room = spinnerRoom.getSelectedItem().toString();
                Double wt = Double.parseDouble(editTextWeight.getText().toString());
                Patient patient = new Patient();
                patient._patient_first_name = first_name;
                patient._patient_last_name = last_name;
                patient._patient_nhi_number = nhi_number;
                patient._patient_dob = dob;
                patient._patient_weight = wt;
                if(checkedRadioBtnId == radioButton.getId()){
                    patient._patient_gender = "M";
                }
                else{
                    patient._patient_gender = "F";
                }
                patient._patient_room = room;
                patient._patient_status = 0;


                boolean res = db.addPatient(patient);

                if (res == true) {
                    Toast.makeText(getApplicationContext(), "Patient Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NewPatientActivity.this, MenuPatientActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Adding Patient. ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NewPatientActivity.this, MenuPatientActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    public void fillSpinnerRoom(){

            MyDBHandler db = new MyDBHandler(this);
            // Spinner Drop down elements
            List<String> rooms = db.getAllRooms();
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, rooms);
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerRoom.setAdapter(dataAdapter);
        }


}
