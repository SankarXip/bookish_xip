package com.example.xipproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class totalAssetList extends AppCompatActivity {


    private Toolbar toolbar;
    private int[] image_id= {R.drawable.conference_1, R.drawable.conference_2, R.drawable.conference_3, R.drawable.conference_4, R.drawable.conference_5, R.drawable.projector_1,R.drawable.projector_2,R.drawable.projector_3};

    private String[] asset_Name;
    private List<Asset> Asset =new ArrayList<>();

    private RecyclerView recyclerView;
    private AdminRecyclerAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_asset_list);

        toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        asset_Name=getResources().getStringArray(R.array.asset_name);
        recyclerView=findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getAsset();
        adaptor=new AdminRecyclerAdaptor(Asset);
        recyclerView.setAdapter(adaptor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_tools,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout_id:

                AlertDialog.Builder builder = new AlertDialog.Builder(totalAssetList.this);
                builder.setMessage("Do you want to Log out from this app??").setCancelable(false)
                        .setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                startActivity(new Intent(totalAssetList.this , LoginPage.class));
                                Toast.makeText(totalAssetList.this,"You have Logged out successfully.",Toast.LENGTH_SHORT).show();

                            }
                        }).setNegativeButton("No" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog , int which) {
                        dialog.cancel();
                    }
                });



                AlertDialog dialog = builder.create();
                builder.setTitle("Log Out!!!");
                dialog.show();


                return true;


            default:

                return super.onOptionsItemSelected(item);
        }
    }

    private void getAsset()
    {
        int count=0;
        for(String assetText :asset_Name)
        {
            Asset asset=new Asset(image_id[count],assetText);
            Asset.add(asset);
            count++;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {


            case 121:
                adaptor.removeItem(item.getGroupId());
                displayMessage("item deleted...");
                return true;

            case 122:
                displayMessage("item added to maintenance...");
                return true;

            default:
                return super.onContextItemSelected(item);

        }



    }
    private void displayMessage(String message)
    {
        Snackbar.make(findViewById(R.id.root_view),message,Snackbar.LENGTH_SHORT).show();

    }

}
