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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;

import java.util.List;

/**
 * Created by faryaz on 6/14/2018.
 */

public class UpdateStaffActivity extends AppCompatActivity{

    MyDBHandler db = new MyDBHandler(this);
    EditText editTextFirstName, editTextLastName, editTextRegistrationNumber, editTextMobile, editTextEmail, editTextType;
    Spinner spinnerType;
    Button updateStaff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_staff);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName= (EditText) findViewById(R.id.editTextLastName);
        editTextRegistrationNumber = (EditText) findViewById(R.id.editTextRegistrationNumber);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);

        spinnerType = (Spinner) findViewById(R.id.spinnerStaffType);

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        final int id = b.getInt("id");
        Toast.makeText(this, "staff id: " + id, Toast.LENGTH_SHORT ).show();

        Cursor cursor = db.getSingleStaffCursor(id);
        if(cursor.moveToFirst()) {
            editTextFirstName.setText(cursor.getString(cursor.getColumnIndex("first_name")));
            editTextLastName.setText(cursor.getString(cursor.getColumnIndex("last_name")));
            editTextRegistrationNumber.setText(cursor.getString(cursor.getColumnIndex("registration_number")));
            fillSpinnerType(cursor.getString(cursor.getColumnIndex("type")));
            editTextEmail.setText(cursor.getString(cursor.getColumnIndex("email")));
            editTextMobile.setText(cursor.getString(cursor.getColumnIndex("mobile")));
        }

        Button updateStaff = (Button) findViewById(R.id.btnUpdateStaff);
        updateStaff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String first_name = editTextFirstName.getText().toString();
                String last_name = editTextLastName.getText().toString();
                String registration_number = editTextRegistrationNumber.getText().toString();
                String type = spinnerType.getSelectedItem().toString();
                String email = editTextEmail.getText().toString();
                String mobile = editTextMobile.getText().toString();

                boolean res = db.updateStaff(first_name, last_name, registration_number, type, email, mobile);

                if (res == true) {
                    Toast.makeText(getApplicationContext(), "Staff updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateStaffActivity.this, ListStaffActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Updating Staff. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void fillSpinnerType(String t){

        MyDBHandler db = new MyDBHandler(this);
        // Spinner Drop down elements
        List<String> types = db.getAllStaffType();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerType.setAdapter(dataAdapter);
        int i = types.indexOf(t);
        spinnerType.setSelection(i);
    }

}
