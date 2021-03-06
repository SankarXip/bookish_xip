package com.example.xipproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class adminDashBoard extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);

        toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
    }


    public void total_assets(View view) {
        Intent intent=new Intent(adminDashBoard.this,totalAssetList.class);
        startActivity(intent);

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

                AlertDialog.Builder builder = new AlertDialog.Builder(adminDashBoard.this);
                builder.setMessage("Do you want to Log out from this app??").setCancelable(false)
                        .setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                startActivity(new Intent(adminDashBoard.this , LoginPage.class));
                                Toast.makeText(adminDashBoard.this,"You have Logged out successfully.",Toast.LENGTH_SHORT).show();

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


    public void Bookedassets(View view) {
        startActivity(new Intent(this , ListData.class));
    }

    public void update(View view) {
        startActivity(new Intent(this , updateAdminActivity.class));
    }

    public void delete(View view) {
        startActivity(new Intent(this , deleteAdmin.class));
    }


}
