package com.example.xipproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class updateAdminActivity extends AppCompatActivity {
    private Toolbar toolbar;

    DatabaseHelper mDatabaseHelper;
    Button update;
    EditText name;
    EditText Id;
    TextView selectDate,startTime,endTime;
    Spinner assetName;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);

        toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);

        startTime = (TextView) findViewById(R.id.editTextStTime);
        endTime = (TextView) findViewById(R.id.editTextEndTime);
        selectDate = (TextView) findViewById(R.id.editTextDate);
        update = (Button) findViewById(R.id.go_available_asset);
        Id=(EditText)findViewById(R.id.editTextId);
        name = (EditText) findViewById(R.id.editTextName);
        assetName = (Spinner) findViewById(R.id.editTextAssetName);
        mDatabaseHelper = new DatabaseHelper(this);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(updateAdminActivity.this , android.R.style.Holo_Light_ButtonBar ,
                        mDateSetListener , year , month , day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePickerDialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view , int year , int month , int dayOfMonth) {
                month = month + 1;
               // Log.d(TAG , "Date set DD/MM/YYYY :" + dayOfMonth + "/" + month + "/" + year);

                String date = month + "/" + dayOfMonth + "/" + year;
                selectDate.setText(date);

            }
        };


        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(updateAdminActivity.this , new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker , int hourOfDay , int minutes) {

                        startTime.setText(hourOfDay + "." + minutes);

                    }

                } , 0 , 0 , false);
                timePickerDialog.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(updateAdminActivity.this , new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker , int hourOfDay , int minutes) {

                        endTime.setText(hourOfDay + "." + minutes);

                    }

                } , 0 , 0 , false);
                timePickerDialog.show();
            }
        });


        UpdateData();
    }

        public void UpdateData () {
            update.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isUpdate =  mDatabaseHelper.updateData(Id.getText().toString(),
                                    name.getText().toString(),
                                    assetName.getSelectedItem().toString() ,
                                    selectDate.getText().toString() ,
                                    startTime.getText().toString() , endTime.getText().toString());
                            if (isUpdate == true)
                                Toast.makeText(updateAdminActivity.this , "Data Updated" , Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(updateAdminActivity.this , "Data not Updated" , Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(updateAdminActivity.this);
                builder.setMessage("Do you want to Log out from this app??").setCancelable(false)
                        .setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                startActivity(new Intent(updateAdminActivity.this , LoginPage.class));
                                Toast.makeText(updateAdminActivity.this,"You have Logged out successfully.",Toast.LENGTH_SHORT).show();

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

