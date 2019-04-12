package com.example.xipproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class adminDashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
    }


    public void total_assets(View view) {
        Intent intent=new Intent(adminDashBoard.this,totalAssetList.class);
        startActivity(intent);

    }


}
