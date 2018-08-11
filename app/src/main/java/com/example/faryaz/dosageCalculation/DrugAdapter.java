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


public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ViewHolder> {
    private List<Drug> drugList;
    private Context mContext;
    private RecyclerView mRecyclerView;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView drugName, drugCode, drugMg, drugMl, drugType;
        //public ImageView drugImage;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            drugName = (TextView) v.findViewById(R.id.drugName);
            //drugCode = (TextView) v.findViewById(R.id.drugCode);
            //drugMg = (TextView) v.findViewById(R.id.drugMg);
            //drugMl = (TextView) v.findViewById(R.id.drugMl);
            drugType = (TextView) v.findViewById(R.id.drugType);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DrugAdapter(List<Drug> myDataset, Context context, RecyclerView recyclerView) {
        drugList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DrugAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_drug_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Drug drug = drugList.get(position);
        String name = drug.get_name();
        holder.drugName.setText(name);
        //holder.drugCode.setText(drug.get_code());
       // holder.drugMg.setText(drug.get_mg() + "mg");
        //holder.drugMl.setText( drug.get_ml() + "ml");
        holder.drugType.setText(drug.get_type() +  " " +  drug.get_mg() + "mg" + " " + drug.get_ml() + "ml");
        //Picasso.with(mContext).load(R.drawable.female).placeholder(R.mipmap.ic_launcher).into(holder.drugImage);
        final int drugId = drug.get_id();

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ViewDrugDetailActivity.class);
                i.putExtra("id", drugId);
                mContext.startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        int count = drugList.size();
        return count;
    }
}