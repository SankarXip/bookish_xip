package com.example.xipproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class empDashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_dash_board);
    }

    public void onBookingAssets(View view)
    {

        Intent intent=new Intent(empDashBoard.this,OnBookingassets.class);
        startActivity(intent);
    }
}
