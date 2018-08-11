package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import com.example.faryaz.anglesea.R;

public class NewRoomActivity extends AppCompatActivity {
    MyDBHandler db = new MyDBHandler(this);
    EditText editTextRoom;
    Button btnAddRoom;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);
        editTextRoom = (EditText) findViewById(R.id.roomNumber);
        btnAddRoom = (Button) findViewById(R.id.btnAddRoom);

        btnAddRoom.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String room_no = editTextRoom.getText().toString().trim();
                Room room = new Room();
                room.room_no = room_no;
                room.status = 1;
                boolean res = db.addRoom(room);
                if (res) {
                    Toast.makeText(getApplicationContext(), "Room added successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NewRoomActivity.this, MenuRoomActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), " Error in adding room.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NewRoomActivity.this, MenuRoomActivity.class);
                    startActivity(i);
                }
            }
        });

    }
}
