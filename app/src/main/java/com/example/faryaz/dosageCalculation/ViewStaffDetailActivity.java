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
 * Created by faryaz on 6/16/2018.
 */

public class ViewStaffDetailActivity extends AppCompatActivity {
    MyDBHandler db = new MyDBHandler(this);
    TextView textViewStaffRegistrationNumber, textViewStaffName, textViewStaffType, textViewStaffEmail;
    Button btnUpdate, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detail);

        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");
       // Toast.makeText(this, "staff id: " + id, Toast.LENGTH_SHORT ).show();
        textViewStaffName= (TextView) findViewById(R.id.textViewStaffName);
        textViewStaffRegistrationNumber = (TextView) findViewById(R.id.textViewStaffRegistrationNumber) ;
        textViewStaffEmail = (TextView) findViewById(R.id.textViewStaffEmail);
        textViewStaffType = (TextView) findViewById(R.id.textViewStaffType);

        btnDelete = (Button) findViewById(R.id.btnDelete);

        Cursor cursor = db.getSingleStaffCursor(id);

        if(cursor.moveToFirst())
        {
            String first_name = cursor.getString(cursor.getColumnIndex("first_name"));
            String last_name = cursor.getString(cursor.getColumnIndex("last_name"));
            String registration_number = cursor.getString(cursor.getColumnIndex("registration_number"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            textViewStaffName.setText("Name:" + first_name + " " + last_name);
            textViewStaffType.setText("Type :"+ type);
            textViewStaffEmail.setText("Email:" + email);
            textViewStaffRegistrationNumber.setText("Reg. No: "  + registration_number);
        }
        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
              Intent i = new Intent(ViewStaffDetailActivity.this,UpdateStaffActivity.class);
              i.putExtra("id", id);
              Toast.makeText(ViewStaffDetailActivity.this, "staff id: " + id, Toast.LENGTH_SHORT ).show();
              startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                confirmDialogDelete(id);
                //Intent i = new Intent(ViewStaffDetailActivity.this,DeleteStaffActivity.class);
                //i.putExtra("pid", id);
                //startActivity(i);
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
                boolean res = db.delete("staffs", id);
                if(res){
                    Toast.makeText(getApplicationContext(), "Staff deleted successfully.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Staff deleted successfully.", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(ViewStaffDetailActivity.this,ListStaffActivity.class);
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
