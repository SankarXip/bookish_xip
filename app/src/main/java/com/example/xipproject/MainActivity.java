package com.example.xipproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void total_assets(View view) {
        Intent intent=new Intent(MainActivity.this,totalAssetList.class);
        startActivity(intent);

    }

    public void onBookingAssets(View view) {
        Intent intent=new Intent(MainActivity.this,OnBookingassets.class);
        startActivity(intent);
    }
}
