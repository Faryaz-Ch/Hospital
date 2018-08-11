package com.example.faryaz.anglesea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;

/**
 * Created by faryaz on 6/24/2018.
 */

public class VerifyDrugActivity extends AppCompatActivity {
    MyDBHandler db = new MyDBHandler(this);
    TextView txtViewMessage;
    EditText editTextUsername, editTextPassword;
    CheckBox chkBox;
    Button btnCheckCalculation, btnAddMedication;
    String username, password;
    String  drug, nurse_reg_no, nurse2_reg_no, total_medication, medication_type, interval;
    Integer pid;
    Double dosage,mg,ml;
    boolean isValid, isValidCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_drug);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        chkBox = (CheckBox) findViewById(R.id.chkBox);
        btnCheckCalculation = (Button) findViewById(R.id.btnCheckCalculation);
        btnAddMedication = (Button) findViewById(R.id.btnAddMedication);

        Bundle data = getIntent().getExtras();
        pid = data.getInt("pid");
        drug = data.getString("drug");
        nurse_reg_no = data.getString("reg_no");
        total_medication = data.getString("total_medication");
        dosage = data.getDouble("dosage");
        mg = data.getDouble("mg");
        ml = data.getDouble("ml");
        medication_type = data.getString("medication_type");
        interval = data.getString("interval");

       // txtViewMessage.setText(drug + ", " + nurse_reg_no + ", "+ dosage +  " ,Mg: "+ mg + " , Ml:"+ medication_type + ", " + interval);

        StringBuilder builder = new StringBuilder("Extras:\n");
        for (String key : data.keySet()) { //extras is the Bundle containing info
            Object value = data.get(key); //get the current object
            builder.append(key).append(": ").append(value).append("\n"); //add the key-value pair to the
        }
        Log.i("Extras",builder.toString()); //log the data or use it as needed.

        btnCheckCalculation.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
               String usrName = editTextUsername.getText().toString();
               String usrPassword = editTextPassword.getText().toString();
                boolean isValidUser = db.verifyLogin(usrName, usrPassword);
                if(isValidUser){
                    nurse2_reg_no = usrName;
                    isValidCalculation = checkMedicationCalculation(dosage, mg, ml, total_medication);
                    if(isValidCalculation){
                        Toast.makeText(getApplicationContext(), "Calculations is correct.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Calculation is not correct", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnAddMedication.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(!chkBox.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "Verify Medication Calculation by checking the Checkbox", Toast.LENGTH_LONG).show();
                }
                else{
                    //SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd hh:mm:s");
                    //Date date = new Date();
                    // final String time = df.format(date);
                    Long tsLong = System.currentTimeMillis()/1000;
                    String time = tsLong.toString();
                    String nurse2_reg_no = "";
                  boolean res = db.addMedication(pid, drug, nurse_reg_no, nurse2_reg_no, total_medication, medication_type, interval, time);
                    if (res == true) {
                        Toast.makeText(getApplicationContext(), "Medication Added for patient Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(VerifyDrugActivity.this, ListPatientActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error in Adding Medication. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }

    public boolean checkMedicationCalculation(Double dosage, Double mg, Double ml, String total_medication){
        double tot = Math.round((dosage / mg) * ml);
        String total =  String.format("%.2f", tot);
        if(total.equals(total_medication)){
            return true;
        }
        else{
            return false;
        }

    }
}
