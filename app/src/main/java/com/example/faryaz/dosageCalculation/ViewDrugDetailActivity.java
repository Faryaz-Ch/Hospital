package com.example.faryaz.anglesea;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;

/**
 * Created by faryaz on 6/14/2018.
 */

public class ViewDrugDetailActivity extends AppCompatActivity {
    MyDBHandler db = new MyDBHandler(this);
    TextView textViewDrugName, textViewDrugCode, textViewDrugMg, textViewDrugMl, textViewDrugType;
    Button btnUpdate, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);

        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");
       // Toast.makeText(this, "staff id: " + id, Toast.LENGTH_SHORT ).show();
        textViewDrugName= (TextView) findViewById(R.id.textViewDrugName);
        textViewDrugCode = (TextView) findViewById(R.id.textViewDrugCode) ;
        textViewDrugMg = (TextView) findViewById(R.id.textViewDrugMg);
        textViewDrugMl = (TextView) findViewById(R.id.textViewDrugMl);
        textViewDrugType = (TextView) findViewById(R.id.textViewDrugType);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Cursor cursor = db.getSingleDrugCursor(id);

        if(cursor.moveToFirst())
        {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String code = cursor.getString(cursor.getColumnIndex("code"));
            String mg = cursor.getString(cursor.getColumnIndex("mg"));
            String ml = cursor.getString(cursor.getColumnIndex("ml"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            textViewDrugName.setText("Name: " + name);
            textViewDrugCode.setText("Code: " + code);
            textViewDrugMg.setText("Milligram: " + mg + " mg");
            textViewDrugMl.setText("Milliliter:" + ml + " ml");
            textViewDrugType.setText("Type: "+ type);
        }
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              Intent i = new Intent(ViewDrugDetailActivity.this,UpdateDrugActivity.class);
              i.putExtra("id", id);
              //Toast.makeText(ViewStaffDetailActivity.this, "staff id: " + id, Toast.LENGTH_SHORT ).show();
              startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                confirmDialogDelete(id);
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
                boolean res = db.delete("drugs", id);
                if(res){
                    Toast.makeText(getApplicationContext(), "Drug deleted successfully.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Drug deleted successfully.", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(ViewDrugDetailActivity.this,ListDrugActivity.class);
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
}
