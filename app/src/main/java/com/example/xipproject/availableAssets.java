package com.example.xipproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class availableAssets extends AppCompatActivity {

    private static final String TAG = availableAssets.class.getSimpleName();

    private TextView assetnames;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_assets);

        assetnames = (TextView) findViewById(R.id.names);

        myRef = initFirebaseDatabase();

    }
    @NonNull
    private DatabaseReference initFirebaseDatabase() {
        Log.d(TAG, "onCreate: initialising the database");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> dataSnapshotIterator = iterable.iterator();

                Log.d(TAG, "onDataChange: "+dataSnapshot.getRef());

                List<AssetFb> assets = new ArrayList<>();

                while (dataSnapshotIterator.hasNext()){
                    AssetFb asset = new AssetFb();
                    DataSnapshot snapshot = dataSnapshotIterator.next();

                    Log.d(TAG, "onDataChange: "+snapshot.getKey());
                    Log.d(TAG, "onDataChange: "+snapshot.getValue());

                    Log.d(TAG, "onDataChange: "+(String) snapshot.child("id").getValue());
                    Log.d(TAG, "onDataChange: "+(String) snapshot.child("name").getValue());
                    // Log.d(TAG, "onDataChange: "+(String) snapshot.child("status").getValue());

                    asset.setAssetId((String) snapshot.child("id").getValue());
                    asset.setAssetName((String) snapshot.child("name").getValue());
                    //    asset.setStatus(Boolean.parseBoolean((String) snapshot.child("status").getValue()));

                    assets.add(asset);
                }

                displayListOfAssets(assets);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });
    }

    private void displayListOfAssets(List<AssetFb> assets) {
        StringBuilder builder = new StringBuilder();

        for (AssetFb asset: assets){
            builder.append(asset.getAssetName());
            builder.append(",");
        }

        assetnames.setText(builder.toString());

    }
}
