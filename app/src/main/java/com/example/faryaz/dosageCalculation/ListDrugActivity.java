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

public class ListDrugActivity extends Activity {

    MyDBHandler db = new MyDBHandler(this);
    Cursor cursor;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private DrugAdapter adapter;
    private String filter = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_list);
        //initialize the variables
        mRecyclerView = (RecyclerView)findViewById(R.id.rvDrug);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //populate recyclerview
        populaterecyclerView(filter);
    }

    private void populaterecyclerView(String filter){
        db = new MyDBHandler(this);
        adapter = new DrugAdapter(db.drugList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

}
