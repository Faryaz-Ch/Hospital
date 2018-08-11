package com.example.faryaz.anglesea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class CalculatePatientDosageAdultActivity extends AppCompatActivity{

    MyDBHandler db = new MyDBHandler(this);
    EditText editTextDrugMg, editTextDrugMl, editTextDosage, editTextInterval, editTextTotalMedication;
    Button btnCalculate;
    Spinner spinnerPatient, spinnerDrug, spinnerNurse, spinnerMedicationType;
    private List<String> drugs;
    private int id;
    private String patientGroup;
    String drug, totalMedication, reg_no, interval, medication_type;
    Double dosage, drugMg, drugMl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        id = b.getInt("pid");
        patientGroup = b.getString("patientgroup");
        Patient patient = db.getSinglePatient(id);
        setContentView(R.layout.activity_calculate_dosage_adult);

        editTextDrugMg = (EditText) findViewById(R.id.editTextDrugMg);
        editTextDrugMl = (EditText) findViewById(R.id.editTextDrugMl);
        editTextDosage = (EditText) findViewById(R.id.editTextDosage);
        editTextInterval = (EditText) findViewById(R.id.editTextInterval);
        editTextTotalMedication = (EditText) findViewById(R.id.editTextTotalMedication);

        editTextDosage.setText("0");
        editTextDrugMg.setText("0");
        editTextDrugMl.setText("0");

        spinnerPatient = (Spinner) findViewById(R.id.spinnerPatient);
        spinnerDrug = (Spinner) findViewById(R.id.spinnerDrug);
        spinnerNurse = (Spinner) findViewById(R.id.spinnerNurse);
        spinnerMedicationType = (Spinner) findViewById(R.id.spinnerMedicationType);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "null");
        reg_no = prefs.getString("reg_no", "null");
        fillSpinnerDrug(patientGroup);
        fillSpinnerNurse(reg_no);
        fillSpinnerPatient(id);
        medication_type = spinnerMedicationType.getSelectedItem().toString();
        interval = editTextInterval.getText().toString();
        Toast.makeText(this, interval, Toast.LENGTH_SHORT).show();


        spinnerPatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        spinnerDrug.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(drugs!= null){
                    String drug = drugs.get(position);
                    onDrugSelected(position,drug);
                    drugMg = 0.0;
                    drugMl = 0.0;
                    dosage = 0.0;
                    dosage = Double.parseDouble(editTextDosage.getText().toString().trim());
                    drugMg = Double.parseDouble(editTextDrugMg.getText().toString().trim());
                    drugMl = Double.parseDouble(editTextDrugMl.getText().toString().trim());
                    if( drugMg  > 0) {
                        calculate_total_medication(dosage, drugMg, drugMl);
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });


        spinnerNurse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        spinnerMedicationType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(medication_type.equalsIgnoreCase("IV")){
                    btnCalculate.setText("Verify Dosage");
                }
                else{
                    btnCalculate.setText("Add Medication");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String type;
                totalMedication = editTextTotalMedication.getText().toString();
                interval = editTextInterval.getText().toString();
                type = spinnerMedicationType.getSelectedItem().toString();
                if(type.equalsIgnoreCase("IV")){
                    if(totalMedication != "") {
                        Intent i = new Intent(CalculatePatientDosageAdultActivity.this, VerifyDrugActivity.class);
                        i.putExtra("pid", id);
                        i.putExtra("drug", drug);
                        i.putExtra("total_medication", totalMedication);
                        i.putExtra("reg_no",reg_no);
                        i.putExtra("dosage", dosage);
                        i.putExtra("mg", drugMg);
                        i.putExtra("ml", drugMl);
                        i.putExtra("medication_type", medication_type);
                        i.putExtra("interval", interval);
                        startActivity(i);
                    }
                }
                else{
                    if(totalMedication != "") {
                        //SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd hh:mm:s");
                        //Date date = new Date();
                       // final String time = df.format(date);
                        Long tsLong = System.currentTimeMillis()/1000;
                        String time = tsLong.toString();
                        String nurse2_reg_no = "";
                        boolean res = db.addMedication(id, drug, reg_no, nurse2_reg_no, totalMedication, medication_type, interval, time);
                        if (res == true) {
                            Toast.makeText(getApplicationContext(), "Medication Added for patient Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(CalculatePatientDosageAdultActivity.this, ListPatientActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error in Adding Medication. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }



            }
        });

    }

    // put the value of drug mg and ml on changing drug
    public void onDrugSelected(int atPotision, String drugsName){
        if(atPotision!=0){
            Drug selectedDrug = db.getSingleDrug(drugsName, "Adult");
            editTextDrugMg.setText(selectedDrug.get_mg());
            editTextDrugMl.setText(selectedDrug.get_ml());
        }
    }

    //fill the patient spinner with patient data
    public void fillSpinnerPatient(){

        MyDBHandler db = new MyDBHandler(this);
        // Spinner Drop down elements
        List<String> patients = db.getAllPatient();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, patients);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerPatient.setAdapter(dataAdapter);
        //int i = patients.indexOf(t);
        //spinnerPatient.setSelection(i);
    }

    //fill the patient spinner with patient data
    public void fillSpinnerPatient(Integer t){

        MyDBHandler db = new MyDBHandler(this);
        // Spinner Drop down elements
        List<String> patients = db.getSinglePatient(t);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, patients);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerPatient.setAdapter(dataAdapter);
    }

    //fill drug spinner with drug data
    public void fillSpinnerDrug(String type){
        MyDBHandler db = new MyDBHandler(this);
        // Spinner Drop down elements
        drugs = db.getAllDrugByType(type);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, drugs);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerDrug.setAdapter(dataAdapter);
        //int i = patients.indexOf(t);
        //spinnerPatient.setSelection(i);
    }


    //fill spinner nurse with nurse data
    public void fillSpinnerNurse(String reg_no){
        MyDBHandler db = new MyDBHandler(this);
        // Spinner Drop down elements
        List<String> nurses = db.getSingleNurse(reg_no);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, nurses);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerNurse.setAdapter(dataAdapter);
        //int i = nurses.indexOf(t);
        // spinnerNurse.setSelection(i);
    }

    public void calculate_total_medication(double dos, double mg, double ml){
        double total_medication = Math.round((dos / mg) * ml);
        // String total_medication_string = new Double(total_medication).toString();
        String final_medication_string = String.format("%.2f", total_medication);
        editTextTotalMedication.setText(final_medication_string);
    }


}

