package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.faryaz.anglesea.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    private List<Patient> patientList;
    private Context mContext;
    private RecyclerView mRecyclerView;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView personNameTxtV;
        public TextView personAgeTxtV;
        public TextView personOccupationTxtV;
        public ImageView personImageImgV;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            personNameTxtV = (TextView) v.findViewById(R.id.name);
            personAgeTxtV = (TextView) v.findViewById(R.id.age);
            personOccupationTxtV = (TextView) v.findViewById(R.id.occupation);
            personImageImgV = (ImageView) v.findViewById(R.id.image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PatientAdapter(List<Patient> myDataset, Context context, RecyclerView recyclerView) {
        patientList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_patient_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Patient person = patientList.get(position);
        String name = person.get_patient_first_name() + ' ' + person.get_patient_last_name();
        holder.personNameTxtV.setText(name);
        holder.personOccupationTxtV.setText(person.get_patient_nhi_number());
        holder.personAgeTxtV.setText(person.get_patient_dob());

        if(person.get_patient_gender() == "M"){
            Picasso.with(mContext).load(R.drawable.male).placeholder(R.mipmap.ic_launcher).into(holder.personImageImgV);
        }
        else {
            Picasso.with(mContext).load(R.drawable.female).placeholder(R.mipmap.ic_launcher).into(holder.personImageImgV);
        }
        final int patientId = person.get_id();

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ViewPatientDetailActivity.class);
                i.putExtra("pid", patientId);
                mContext.startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        int count = patientList.size();
        return count;
    }
}