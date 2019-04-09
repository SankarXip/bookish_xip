package com.example.xipproject;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class totalAssetList extends AppCompatActivity {

    private int[] image_id= {R.drawable.conference_1, R.drawable.conference_2, R.drawable.conference_3, R.drawable.conference_4, R.drawable.conference_5, R.drawable.projector_1,R.drawable.projector_2,R.drawable.projector_3};

    private String[] asset_Name;
    private List<Asset> Asset =new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_asset_list);

        asset_Name=getResources().getStringArray(R.array.asset_name);
        recyclerView=findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getAsset();
        adaptor=new RecyclerAdaptor(Asset);
        recyclerView.setAdapter(adaptor);

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
