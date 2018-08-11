package com.example.faryaz.anglesea;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;


/**
 * Created by faryaz on 6/14/2018.
 */

public class UpdateDrugActivity extends AppCompatActivity{

    MyDBHandler db = new MyDBHandler(this);
    EditText editTextDrugName, editTextDrugCode, editTextDrugMg, editTextDrugMl;
    RadioGroup radioGroup;
    RadioButton radioButton, radioButtonPediatrics, radioButtonAdult;
    String drug_type;
    CheckBox chkBox;
    Button updateDrug;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_drug);
        editTextDrugName = (EditText) findViewById(R.id.editTextDrugName);
        editTextDrugCode = (EditText) findViewById(R.id.editTextDrugCode);
        editTextDrugMg = (EditText) findViewById(R.id.editTextDrugMg);
        editTextDrugMl = (EditText) findViewById(R.id.editTextDrugMl);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioButton = (RadioButton) findViewById(R.id.rb_pediatrics);
        radioButtonPediatrics = (RadioButton) findViewById(R.id.rb_pediatrics);
        radioButtonAdult = (RadioButton) findViewById(R.id.rb_adult);
        chkBox = (CheckBox) findViewById(R.id.chkBox);

        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");
        //Toast.makeText(this, "drug id: " + id, Toast.LENGTH_SHORT ).show();

        Cursor cursor = db.getSingleDrugCursor(id);
        if(cursor.moveToFirst()) {
            editTextDrugName.setText(cursor.getString(cursor.getColumnIndex("name")));
            editTextDrugCode.setText(cursor.getString(cursor.getColumnIndex("code")));
            editTextDrugMg.setText(cursor.getString(cursor.getColumnIndex("mg")));
            editTextDrugMl.setText(cursor.getString(cursor.getColumnIndex("ml")));
            String val = cursor.getString(cursor.getColumnIndex("type"));
            if (val.equals("Pediatrics"))
            {
                radioButtonPediatrics.setChecked(true);
                radioButtonAdult.setChecked(false);
            }
            else  {
                radioButtonPediatrics.setChecked(false);
                radioButtonAdult.setChecked(true);
            }
            String isDanger = cursor.getString(cursor.getColumnIndex("is_dangerous"));
            if(isDanger.equals("1")){
                chkBox.setChecked(true);
            }
            else{
                chkBox.setChecked(false);
            }
        }

        updateDrug = (Button) findViewById(R.id.btnUpdate);
        updateDrug.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int checkedRadioBtnId = radioGroup.getCheckedRadioButtonId();
                String name = editTextDrugName.getText().toString();
                String code = editTextDrugCode.getText().toString();
                String mg = editTextDrugMg.getText().toString();
                String ml = editTextDrugMl.getText().toString();
                String type = "";
                String drug_id = Integer.toString(id);
                if(checkedRadioBtnId == radioButton.getId()){
                    type = "Pediatrics";
                }
                else{
                    type = "Adult";
                }
                String danger;
                if(chkBox.isChecked()){
                    danger = "1";
                }else{
                    danger = "0";
                }


                boolean res = db.updateDrug(drug_id, name, code, mg, ml, type, danger );

                if (res == true) {
                    Toast.makeText(getApplicationContext(), "Drug updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateDrugActivity.this, ListDrugActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Error in Updating Drug. ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateDrugActivity.this, ListDrugActivity.class);
                    startActivity(i);
                }
            }
        });

    }



}
