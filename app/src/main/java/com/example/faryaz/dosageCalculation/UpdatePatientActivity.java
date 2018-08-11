package com.example.faryaz.anglesea;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;

import java.util.List;

/**
 * Created by faryaz on 6/14/2018.
 */

public class UpdatePatientActivity  extends AppCompatActivity{

    MyDBHandler db = new MyDBHandler(this);
    EditText editTextFirstName, editTextLastName, editTextNhiNumber, editTextDob, editTextWeight;
    Spinner spinnerRoom;
    Button updatePatient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName= (EditText) findViewById(R.id.editTextLastName);
        editTextNhiNumber = (EditText) findViewById(R.id.editTextNhiNumber);
        editTextDob = (EditText) findViewById(R.id.editTextDOB);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);

        spinnerRoom = (Spinner) findViewById(R.id.spinnerRoom);
        //fillSpinnerRoom();

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
        Bundle b = getIntent().getExtras();
        final int id = b.getInt("pid");
        //Toast.makeText(this, "patient id: " + id, Toast.LENGTH_SHORT ).show();

        Cursor cursor = db.getSinglePatientCursor(id);
        if(cursor.moveToFirst()) {
            editTextFirstName.setText(cursor.getString(cursor.getColumnIndex("first_name")));
            editTextLastName.setText(cursor.getString(cursor.getColumnIndex("last_name")));
            editTextNhiNumber.setText(cursor.getString(cursor.getColumnIndex("nhi_number")));
            editTextDob.setText(cursor.getString(cursor.getColumnIndex("dob")));;
            editTextWeight.setText(cursor.getString(cursor.getColumnIndex("weight")));
            fillSpinnerRoom(cursor.getString(cursor.getColumnIndex("room")));
        }

        Button updatePatient = (Button) findViewById(R.id.btnAddPatient);
        updatePatient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String first_name = editTextFirstName.getText().toString();
                String last_name = editTextLastName.getText().toString();
                String nhi_number = editTextNhiNumber.getText().toString();
                String dob = editTextDob.getText().toString();
                String room = spinnerRoom.getSelectedItem().toString();
                String wt = editTextWeight.getText().toString();
                Double weight = Double.parseDouble(wt);

                boolean res = db.updatePatient(first_name, last_name, nhi_number, dob, weight, room);

                if (res == true) {
                    Toast.makeText(getApplicationContext(), "Patient updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdatePatientActivity.this, ListPatientActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Updating Patient. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void fillSpinnerRoom(String rm){

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
        int i = rooms.indexOf(rm);
        spinnerRoom.setSelection(i);
    }

}
