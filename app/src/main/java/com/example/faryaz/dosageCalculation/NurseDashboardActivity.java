package com.example.faryaz.anglesea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.faryaz.anglesea.R;

/**
 * Created by faryaz on 6/14/2018.
 */

public class NurseDashboardActivity extends AppCompatActivity implements View.OnClickListener{
    CardView drugCard, nurseCard, patientCard, roomCard, medicationCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_dashboard);
        CardView drugCard = (CardView) findViewById(R.id.drugCard);
        CardView nurseCard = (CardView) findViewById(R.id.nurseCard);
        CardView patientCard = (CardView) findViewById(R.id.patientCard);
        CardView roomCard = (CardView) findViewById(R.id.roomCard);
        CardView medicationCard = (CardView) findViewById(R.id.medicationCard);
        TextView txtWelcome = (TextView) findViewById(R.id.txtWelcome);

        SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "null");
        txtWelcome.setText("Welcome " + username);

        drugCard.setOnClickListener(this);
        nurseCard.setOnClickListener(this);
        patientCard.setOnClickListener(this);
        roomCard.setOnClickListener(this);
        medicationCard.setOnClickListener(this);
    }


    public void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.drugCard :
                i = new Intent(this, MenuDrugActivity.class);
                startActivity(i);
                break;
            case R.id.nurseCard :
                i = new Intent(this, MenuNurseActivity.class);
                startActivity(i);
                break;
            case R.id.patientCard:
                i = new Intent(this, MenuPatientActivity.class);
                startActivity(i);
                break;
            case R.id.roomCard:
                i = new Intent(this, MenuRoomActivity.class);
                startActivity(i);
                break;
            case R.id.medicationCard:
                i = new Intent(this, ListMedicationActivity.class);
                startActivity(i);
                break;
            default: break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Drugs) {
            Intent drugsmenu = new Intent(this,MenuDrugActivity.class);
            startActivity(drugsmenu);
        }
        else if (id == R.id.Logout) {
            SharedPreferences prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            String username = prefs.getString("username", "null");
            String reg_no = prefs.getString("reg_no", "null");
            int user_id = prefs.getInt("id", 0);
            editor.clear();
            editor.commit();
            Intent logout = new Intent(this,LoginActivity.class);
            startActivity(logout);

        }
        return super.onOptionsItemSelected(item);
    }
}
