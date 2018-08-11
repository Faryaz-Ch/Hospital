package com.example.faryaz.anglesea;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import com.example.faryaz.anglesea.R;


/**
 * Created by faryaz on 6/14/2018.
 */

public class LoginActivity extends AppCompatActivity {
    MyDBHandler db = new MyDBHandler(this);
    EditText username, password;
    Button btnLogin;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String USERNAME = "username";
    public static final String USERREGISTRATIONNUMBER = "reg_no";
    public static final String USERID = "user_id";
    private String fname, lname, name, reg_no;
    private int id;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent;
                String usrName = username.getText().toString();
                String usrPassword = password.getText().toString();
                if(usrName.equals("admin")){
                    if(usrName.equals("admin") && usrPassword.equals("123456")){
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("username", usrName);
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Access Denied.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(LoginActivity.this, LoginActivity.class);
                    }

                }
                else {
                    boolean isValidUser = db.verifyLogin(usrName, usrPassword);
                    if (isValidUser) {
                        //Toast.makeText(this, isValidUser, Toast.LENGTH_SHORT).show();
                        //saving user data using shared preferences
                        Cursor cursor = db.getSingleStaffCursor(usrName);
                        if (cursor.moveToFirst()) {
                            id = cursor.getInt(cursor.getColumnIndex("id"));
                            fname = cursor.getString(cursor.getColumnIndex("first_name"));
                            lname = cursor.getString(cursor.getColumnIndex("last_name"));
                            name = fname + " " + lname;
                            reg_no = cursor.getString(cursor.getColumnIndex("registration_number"));
                        }
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("username", name);
                        editor.putString("reg_no", reg_no);
                        editor.putInt("user_id", id);
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(LoginActivity.this, NurseDashboardActivity.class);
                    } else {
                        Toast.makeText(LoginActivity.this, "Access Denied.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(LoginActivity.this, LoginActivity.class);
                    }

                }
                startActivity(intent);
                finish();
            }
        });
    }


}
