package com.example.faryaz.anglesea;

/**
 * Created by john on 6/9/2018.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.faryaz.anglesea.R;

import java.util.ArrayList;
import java.util.List;

public class ListRoomActivity extends AppCompatActivity {

    ListView roomList;
    List<Room> theRoomList;
    MyDBHandler db = new MyDBHandler(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        roomList = findViewById(R.id.roomList);
        Cursor allRooms = db.getAllRoomCursor();

        ArrayList<String> theList = new ArrayList<String>();

        if (allRooms.getCount()==0)
        {
            String msg  ="No Rooms Found";
            theList.add(msg);
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
            roomList.setAdapter(listAdapter);
        }
        else
        {
            while (allRooms.moveToNext()) {
                theList.add(allRooms.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                roomList.setAdapter(listAdapter);
                roomList.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String data = String.valueOf(adapterView.getItemAtPosition(i));
                                Intent intent = new Intent(ListRoomActivity.this,ViewRoom.class);
                                final  String room = data;
                                intent.putExtra("room",room);
                                startActivity(intent);
                            }
                        }
                );
            }
        }


    }
}
