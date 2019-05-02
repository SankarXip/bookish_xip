package com.example.xipproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

public class ListData extends AppCompatActivity {
    private Toolbar toolbar;

    private static final String TAG = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;
    RecyclerView rv;
    String id;
    String name,assetName,date,startTime,endTime;
    ArrayList<GetterSetter> al=new ArrayList<>();
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);

        rv = (RecyclerView) findViewById(R.id.m_recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ListData.this);
        rv.setLayoutManager(layoutManager);

        mDatabaseHelper = new DatabaseHelper(this);



        cursor=mDatabaseHelper.getData();

        if (cursor.getCount()==0) {
            Toast.makeText(ListData.this , " No asset is booked till now !!" , Toast.LENGTH_LONG).show();

        }

        if (cursor.getCount()>0)
        {
            if(cursor.moveToFirst())
            {
                do {
                    id= cursor.getString(0);
                    name=cursor.getString(1);
                    assetName=cursor.getString(2);
                    date=cursor.getString(3);
                    startTime=cursor.getString(4);
                    endTime=cursor.getString(5);

                    GetterSetter gl=new GetterSetter(id,name,assetName,date,startTime,endTime);
                    al.add(gl);

                }while (cursor.moveToNext());
            }
        }

        recyclerAdaptor myRec=new recyclerAdaptor(ListData.this,al);
        rv.setAdapter(myRec);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_tools,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.logout_id:

                AlertDialog.Builder builder = new AlertDialog.Builder(ListData.this);
                builder.setMessage("Do you want to Log out from this app??").setCancelable(false)
                        .setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                startActivity(new Intent(ListData.this , LoginPage.class));
                                Toast.makeText(ListData.this,"You have Logged out successfully.",Toast.LENGTH_SHORT).show();

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

}
