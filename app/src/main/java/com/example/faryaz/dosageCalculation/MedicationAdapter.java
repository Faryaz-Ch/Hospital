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
import android.widget.TextView;

import com.example.faryaz.anglesea.R;

import java.util.List;


public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.ViewHolder> {

    private List<Medication> medicationList;
    private Context mContext;
    private RecyclerView mRecyclerView;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView patientName;
        public TextView drugName;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            patientName = (TextView) v.findViewById(R.id.patientName);
            drugName = (TextView) v.findViewById(R.id.drugName);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MedicationAdapter(List<Medication> myDataset, Context context, RecyclerView recyclerView) {
        medicationList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MedicationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_medication_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Medication medication = medicationList.get(position);
        String pid = Integer.toString(medication.get_patient_id());
        //holder.patientName.setText(pid);
        holder.patientName.setText(medication.get_patient_name());
        holder.drugName.setText(medication.get_drug_id());

       // Picasso.with(mContext).load(R.drawable.female).placeholder(R.mipmap.ic_launcher).into(holder.medicationImage);

        final int medicationId = medication.get_medication_id();

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ViewMedicationDetailActivity.class);
                i.putExtra("id", medicationId);
                mContext.startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        int count = medicationList.size();
        return count;
    }
}