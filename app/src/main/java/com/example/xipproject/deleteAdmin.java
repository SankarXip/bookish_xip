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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deleteAdmin extends AppCompatActivity {

    private Toolbar toolbar;

    EditText Id;
    DatabaseHelper mDatabaseHelper;
    Button deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_admin);
       Id=(EditText)findViewById(R.id.delet_id);
       deleteBtn=(Button)findViewById(R.id.deleteBtn);
        mDatabaseHelper = new DatabaseHelper(this);
        toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);

        DeleteData();
    }
    public void DeleteData() {
        deleteBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mDatabaseHelper.deleteData(Id.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(deleteAdmin.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(deleteAdmin.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
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

                AlertDialog.Builder builder = new AlertDialog.Builder(deleteAdmin.this);
                builder.setMessage("Do you want to Log out from this app??").setCancelable(false)
                        .setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                startActivity(new Intent(deleteAdmin.this , LoginPage.class));
                                Toast.makeText(deleteAdmin.this,"You have Logged out successfully.",Toast.LENGTH_SHORT).show();

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
