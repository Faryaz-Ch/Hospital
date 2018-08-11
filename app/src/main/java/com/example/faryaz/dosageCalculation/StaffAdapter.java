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


public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {
    private List<Staff> staffList;
    private Context mContext;
    private RecyclerView mRecyclerView;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView staffName;
        public TextView staffRegistrationNumber;
        public TextView staffEmail;
        public ImageView staffImage;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            staffName = (TextView) v.findViewById(R.id.staffName);
            staffRegistrationNumber = (TextView) v.findViewById(R.id.staffRegistrationNumber);
            staffEmail = (TextView) v.findViewById(R.id.staffEmail);
            staffImage = (ImageView) v.findViewById(R.id.image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StaffAdapter(List<Staff> myDataset, Context context, RecyclerView recyclerView) {
        staffList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StaffAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.activity_staff_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Staff staff = staffList.get(position);
        String name = staff.get_first_name() + ' ' + staff.get_last_name();
        holder.staffName.setText(name);
        holder.staffRegistrationNumber.setText(staff.get_registration_number());
        holder.staffEmail.setText(staff.get_email());
        Picasso.with(mContext).load(R.drawable.female).placeholder(R.mipmap.ic_launcher).into(holder.staffImage);

        final int staffId = staff.get_id();

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ViewStaffDetailActivity.class);
                i.putExtra("id", staffId);
                mContext.startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        int count = staffList.size();
        return count;
    }
}