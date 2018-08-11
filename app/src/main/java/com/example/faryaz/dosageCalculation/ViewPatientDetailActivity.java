package com.example.faryaz.anglesea;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import java.util.Date;

import com.example.faryaz.anglesea.R;

/**
 * Created by faryaz on 6/14/2018.
 */

public class ViewPatientDetailActivity extends AppCompatActivity {
    MyDBHandler db = new MyDBHandler(this);
    TextView textViewPatientNhiNumber, textViewPatientName, textViewPatientDob, textViewPatientRoom, textViewPatientWeight;
    Button btnUpdate, btnDelete, btnCalculateDosage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        final String first_name;
        final String last_name;
        final String nhi_number;
        final String dob;
        final String room;
        final double weight;
        final int age;
        Bundle b = getIntent().getExtras();
        final int id = b.getInt("pid");
       // Toast.makeText(this, "patient id: " + id, Toast.LENGTH_SHORT ).show();

        textViewPatientNhiNumber = (TextView) findViewById(R.id.textViewPatientNhiNumber) ;
        textViewPatientName= (TextView) findViewById(R.id.textViewPatientName);
        textViewPatientDob = (TextView) findViewById(R.id.textViewPatientDob);
        textViewPatientWeight = (TextView) findViewById(R.id.textViewPatientWeight);
        textViewPatientRoom = (TextView) findViewById(R.id.textViewPatientRoom);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCalculateDosage = (Button) findViewById(R.id.btnCalculateDosage);
        Patient patient = db.getSinglePatient(id);

        /*SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        final String currentDate = df.format(date);
        Toast.makeText(this, currentDate, Toast.LENGTH_SHORT ).show();*/
        dob = patient._patient_dob;

        age = getAge(dob);
        //Toast.makeText(this, "age: " + age, Toast.LENGTH_SHORT).show();
        final String patientGroup;
        if(age <= 16){
            patientGroup = "Pediatrics";
        }
        else{
            patientGroup = "Adult";
        }


            first_name = patient._patient_first_name;
            last_name = patient._patient_last_name;
            nhi_number = patient._patient_nhi_number;


            room = patient._patient_room;
            String wt = new Double(patient._patient_weight).toString();

            textViewPatientNhiNumber.setText("NHI Number: "  + nhi_number);
            textViewPatientName.setText(first_name + " " + last_name);
            textViewPatientDob.setText(dob);
            textViewPatientWeight.setText(wt);
            textViewPatientRoom.setText(room);


        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              Intent i = new Intent(ViewPatientDetailActivity.this,UpdatePatientActivity.class);
              i.putExtra("pid", id);
              startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                confirmDialogDelete(id);
                //Intent i = new Intent(ViewPatientDetailActivity.this,DeletePatientActivity.class);
                //i.putExtra("pid", id);
                //startActivity(i);
            }
        });


        btnCalculateDosage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i;
                if(patientGroup == "Pediatrics") {
                     i = new Intent(ViewPatientDetailActivity.this, CalculatePatientDosagePediatricsActivity.class);
                }else{
                    i = new Intent(ViewPatientDetailActivity.this, CalculatePatientDosageAdultActivity.class);
                }
                i.putExtra("pid", id);
                i.putExtra("patientgroup", patientGroup);
                startActivity(i);
            }
        });



    }
    private void confirmDialogDelete(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete a record ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean res = db.delete("patients", id);
                if(res){
                    Toast.makeText(getApplicationContext(), "Patient deleted successfully.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Patient deleted successfully.", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(ViewPatientDetailActivity.this,ListPatientActivity.class);
                startActivity(i);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    public static int getAge(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int age = 0;
        try {
            Date date1 = dateFormat.parse(date);
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(date1);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            int month1 = now.get(Calendar.MONTH);
            int month2 = dob.get(Calendar.MONTH);
            if (month2 > month1) {
                age--;
            } else if (month1 == month2) {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = dob.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1) {
                    age--;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return age;
    }
}
