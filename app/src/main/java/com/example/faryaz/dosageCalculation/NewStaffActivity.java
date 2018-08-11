package com.example.faryaz.anglesea;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;

/**
 * Created by faryaz on 6/14/2018.
 */

public class NewStaffActivity  extends AppCompatActivity{

    MyDBHandler db = new MyDBHandler(this);
    EditText editTextFirstName, editTextLastName, editTextRnNumber, editTextEmail, editTextMobile, editTextPassword;
    Button addStaff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_staff);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName= (EditText) findViewById(R.id.editTextLastName);
        editTextRnNumber = (EditText) findViewById(R.id.editTextRnNumber);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        final Spinner spinnerStaffType = (Spinner) findViewById(R.id.spinnerStaffType);

        Button addStaff = (Button) findViewById(R.id.btnAddStaff);
        addStaff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String first_name = editTextFirstName.getText().toString();
                String last_name = editTextLastName.getText().toString();
                String registration_number = editTextRnNumber.getText().toString();
                String email = editTextEmail.getText().toString();
                String mobile = editTextMobile.getText().toString();
                String password = editTextPassword.getText().toString();
                String staff_type = spinnerStaffType.getSelectedItem().toString();
                Staff staff = new Staff();
                staff.first_name = first_name;
                staff.last_name = last_name;
                staff.registration_number = registration_number;
                staff.email = email;
                staff.mobile = mobile;
                staff.password = password;
                staff.type = staff_type;

                boolean res = db.addStaff(staff);

                if (res == true) {
                    Toast.makeText(getApplicationContext(), "Staff Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NewStaffActivity.this, MenuNurseActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Adding Staff. ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NewStaffActivity.this, MenuNurseActivity.class);
                    startActivity(i);

                }
            }
        });

    }

}
