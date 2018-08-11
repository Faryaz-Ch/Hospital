package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.faryaz.anglesea.R;

public class ListMedicationActivity extends Activity {
    MyDBHandler db = new MyDBHandler(this);
    Cursor cursor;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private MedicationAdapter adapter;
    private String filter = "";
    private String order_by = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_list);
        //initialize the variables
        mRecyclerView = (RecyclerView)findViewById(R.id.rvMedication);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //populate recyclerview
        populaterecyclerView(filter);
    }

    private void populaterecyclerView(String filter){
        db = new MyDBHandler(this);
        adapter = new MedicationAdapter(db.medicationList(filter, order_by), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }



}