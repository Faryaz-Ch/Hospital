package com.example.faryaz.anglesea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.faryaz.anglesea.R;

/**
 * Created by faryaz on 6/14/2018.
 */

public class MenuNurseActivity extends AppCompatActivity implements View.OnClickListener{
    CardView viewCard, addCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_menu);
        CardView viewCard = (CardView) findViewById(R.id.viewCard);
        CardView addCard = (CardView) findViewById(R.id.addCard);

        viewCard.setOnClickListener(this);
        addCard.setOnClickListener(this);

    }

    public void onClick(View v){
     Intent i;

        switch (v.getId()){
            case R.id.viewCard :
                i = new Intent(this, ListStaffActivity.class);
                startActivity(i);
                break;
            case R.id.addCard :
                i = new Intent(this, NewStaffActivity.class);
                startActivity(i);
                break;
            default: break;
        }

    }
}
