package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;

import com.example.faryaz.anglesea.R;

public class NewDrugActivity  extends AppCompatActivity{
     MyDBHandler db = new MyDBHandler(this);
     EditText drug_name, drug_code, drug_mg, drug_ml;
     RadioGroup radioGroup;
     RadioButton radioButton;
     CheckBox chkBox;
     String drug_type, is_danger;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drug);
        drug_name = (EditText) findViewById(R.id.et_drugname);
        drug_code = (EditText) findViewById(R.id.et_Code);
        drug_mg = (EditText) findViewById(R.id.et_Milligram);
        drug_ml = (EditText) findViewById(R.id.et_Millitres);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioButton = (RadioButton) findViewById(R.id.rb_pediatrics);
        chkBox = (CheckBox) findViewById(R.id.chkBox);

        Button save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int checkedRadioBtnId = radioGroup.getCheckedRadioButtonId();
                //drug_type = (String) radioButton.getText();
                String name = drug_name.getText().toString();
                String code = drug_code.getText().toString();
                String mg = drug_mg.getText().toString();
                String ml = drug_ml.getText().toString();
                Drug drug = new Drug();
                drug.name = name;
                drug.code = code;
                drug.mg = mg;
                drug.ml = ml;
                if(checkedRadioBtnId == radioButton.getId()){
                    drug.type = "Pediatrics";
                }
                else{
                    drug.type = "Adult";
                }
                if(chkBox.isChecked()){
                  drug.is_danger = "1";
                }else{
                    drug.is_danger = "0";
                }


                boolean res = db.addDrug(drug);

                if (res == true) {
                    Toast.makeText(getApplicationContext(), "Drug Added Successfully", Toast.LENGTH_SHORT).show();
                     Intent i = new Intent(NewDrugActivity.this, MenuDrugActivity.class);
                     startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Adding Drug ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NewDrugActivity.this, MenuDrugActivity.class);
                    startActivity(i);
                }
            }
        });

    }

}
