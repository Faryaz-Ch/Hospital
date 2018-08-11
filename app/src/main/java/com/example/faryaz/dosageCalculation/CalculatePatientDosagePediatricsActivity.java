package com.example.faryaz.anglesea;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;

import java.util.List;

/**
 * Created by faryaz on 6/14/2018.
 */

public class CalculatePatientDosagePediatricsActivity extends AppCompatActivity{

    MyDBHandler db = new MyDBHandler(this);
    EditText editTextStandingOrder, editTextPatientWeight, editTextDrugMg, editTextDrugMl, editTextDosage, editTextInterval, editTextTotalMedication;
    Button btnCalculate;
    Spinner spinnerPatient, spinnerDrug, spinnerNurse, spinnerMedicationType;
    private List<String> drugs;
    private int id;
    private String patientGroup;
    String drug, totalMedication, reg_no, interval, medication_type;
    Double dosage, drugMg, drugMl;
    boolean isMorphineElixer = false;
    boolean isDanger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        id = b.getInt("pid");
        patientGroup = b.getString("patientgroup");
        Patient patient = db.getSinglePatient(id);
        String wt = new Double(patient._patient_weight).toString();
        setContentView(R.layout.activity_calculate_dosage_pediatrics);

        editTextStandingOrder = (EditText) findViewById(R.id.editTextStandingOrder);
        editTextPatientWeight = (EditText) findViewById(R.id.editTextPatientWeight);
        editTextDrugMg = (EditText) findViewById(R.id.editTextDrugMg);
        editTextDrugMl = (EditText) findViewById(R.id.editTextDrugMl);
        editTextDosage = (EditText) findViewById(R.id.editTextDosage);
        editTextInterval = (EditText) findViewById(R.id.editTextInterval);
        editTextTotalMedication = (EditText) findViewById(R.id.editTextTotalMedication);

        editTextDosage.setText("0");
        editTextDrugMg.setText("0");
        editTextDrugMl.setText("0");

        drugMg = 0.0;
        drugMl = 0.0;
        dosage = 0.0;

        spinnerPatient = (Spinner) findViewById(R.id.spinnerPatient);
        spinnerDrug = (Spinner) findViewById(R.id.spinnerDrug);
        spinnerNurse = (Spinner) findViewById(R.id.spinnerNurse);
        spinnerMedicationType = (Spinner) findViewById(R.id.spinnerMedicationType);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);


        //on changing standing order by nurse
        editTextStandingOrder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // call the required function
                double stOrder = 0;
                if(count>0){
                    stOrder = Double.parseDouble(editTextStandingOrder.getText().toString().trim());
                }
                 double weight = Double.parseDouble(editTextPatientWeight.getText().toString());
                calculate_dosage(stOrder, weight);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //on changing standing order by nurse
        editTextDosage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // call the required function
                if(count>0) {
                    dosage = Double.parseDouble(editTextDosage.getText().toString().trim());
                }
                if(dosage > 0) {
                    calculate_total_medication(dosage, drugMg, drugMl);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        spinnerDrug.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sel_drug = spinnerDrug.getSelectedItem().toString();

               if(drugs!= null){
                   drug = drugs.get(position).toString();
                   if(position > 0) {
                       Drug dg = db.getSingleDrugByName(sel_drug);
                       editTextStandingOrder.setVisibility(View.VISIBLE);
                       editTextPatientWeight.setVisibility(View.VISIBLE);
                       onDrugSelected(position, drug);
                       String is_danger = dg.get_is_danger().toString();
                       if(is_danger.equals("1")){
                           isDanger = true;
                       }
                       else{
                           isDanger = false;
                       }

                       Toast.makeText(getApplicationContext(), "selected drug: " + sel_drug, Toast.LENGTH_SHORT).show();
                       Toast.makeText(getApplicationContext(), "selected drug: " + dg.get_is_danger().toString(), Toast.LENGTH_SHORT).show();
                       dosage = Double.parseDouble(editTextDosage.getText().toString().trim());
                       drugMg = Double.parseDouble(editTextDrugMg.getText().toString().trim());
                       drugMl = Double.parseDouble(editTextDrugMl.getText().toString());

                   /*if((position != 4) && (drugMg > 0)){
                       editTextStandingOrder.setVisibility(View.VISIBLE);
                       editTextPatientWeight.setVisibility(View.VISIBLE);
                       editTextDosage.setText("0");
                       editTextDosage.setHint("Standing Order * Weight[kg]");
                       isMorphineElixer = true;
                       calculate_total_medication(dosage, drugMg, drugMl);
                   }
                   if(position == 4 && drugMg > 0){
                       editTextStandingOrder.setVisibility(View.GONE);
                       editTextPatientWeight.setVisibility(View.GONE);
                       editTextDosage.setHint("Dr. Prescribed Dosage");
                       isMorphineElixer = false;
                       calculate_total_medication(dosage, drugMg, drugMl);
                   }
                   */
                       if (!isDanger && ( drugMg > 0)) {
                           editTextStandingOrder.setVisibility(View.VISIBLE);
                           editTextPatientWeight.setVisibility(View.VISIBLE);
                           editTextDosage.setText("0");
                           editTextDosage.setHint("Standing Order * Weight[kg]");
                           isMorphineElixer = true;
                           calculate_total_medication(dosage, drugMg, drugMl);
                       } else {
                           editTextStandingOrder.setVisibility(View.GONE);
                           editTextPatientWeight.setVisibility(View.GONE);
                           editTextDosage.setHint("Dr. Prescribed Dosage");
                           isMorphineElixer = false;
                           editTextDosage.setText("0");
                           calculate_total_medication(dosage, drugMg, drugMl);
                       }
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

        editTextPatientWeight.setText(wt);

        btnCalculate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                totalMedication = editTextTotalMedication.getText().toString();
                interval = editTextInterval.getText().toString();
                if(totalMedication != "") {
                    Intent i = new Intent(CalculatePatientDosagePediatricsActivity.this, VerifyDrugActivity.class);
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
        });

    }

   // put the value of drug mg and ml on changing drug
    public void onDrugSelected(int atPotision, String drugsName){
        if(atPotision!=0){
            Drug selectedDrug = db.getSingleDrug(drugsName, "Pediatrics");
            editTextDrugMg.setText(selectedDrug.get_mg());
            editTextDrugMl.setText(selectedDrug.get_ml());
            calculate_total_medication(dosage, drugMg, drugMl);
        }
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

    public void calculate_dosage(double so, double wt){
        double res = so * wt;
        String res_string = new Double(res).toString();
        editTextDosage.setText(res_string);
    }

    public void calculate_total_medication(double dos, double mg, double ml){
        double total_medication = Math.round((dos / mg) * ml);
       // String total_medication_string = new Double(total_medication).toString();
        String final_medication_string = String.format("%.2f", total_medication);
        editTextTotalMedication.setText(final_medication_string);
    }


}

