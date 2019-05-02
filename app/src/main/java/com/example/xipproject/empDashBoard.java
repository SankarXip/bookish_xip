package com.example.xipproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

public class empDashBoard extends AppCompatActivity {
    private Toolbar toolbar;

    private final String CHANNEL_ID="Personal Notification";
    public static final int NOTIFICATION_ID=001;
    private static final String TAG = "MainActivity";
    static   DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    EditText Ename;
    TextView selectDate;
  TextView startTime, endTime;
    Spinner astName;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_dash_board);

        startTime = (TextView) findViewById(R.id.editTextStTime);
        endTime = (TextView) findViewById(R.id.editTextEndTime);
        selectDate = (TextView) findViewById(R.id.editTextDate);
        btnAdd = (Button) findViewById(R.id.go_available_asset);

        Ename = (EditText) findViewById(R.id.editTextName);
        astName=(Spinner)findViewById(R.id.editTextAssetName);
        mDatabaseHelper = new DatabaseHelper(this);
        toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(empDashBoard.this , android.R.style.Holo_Light_ButtonBar ,
                        mDateSetListener , year , month , day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePickerDialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view , int year , int month , int dayOfMonth) {
                month = month + 1;
                Log.d(TAG , "Date set DD/MM/YYYY :" + dayOfMonth + "/" + month + "/" + year);

                String date = month + "/" + dayOfMonth + "/" + year;
                selectDate.setText(date);

            }
        };


        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(empDashBoard.this , new TimePickerDialog.OnTimeSetListener() {

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

                TimePickerDialog timePickerDialog = new TimePickerDialog(empDashBoard.this , new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker , int hourOfDay , int minutes) {

                        endTime.setText(hourOfDay + "." + minutes);

                    }

                } , 0 , 0 , false);
                timePickerDialog.show();
            }
        });

    }

    //logic to call the insertData of MYDB class
    public void saveData(View view) {

        Intent intent = getIntent();
        final  String Ename = intent.getExtras().getString("key_email");


       /* if (Ename.length()==0){
            Ename.setError( "Enter your Name" );
            Toast.makeText( empDashBoard.this,  " Fields cannot be empty", Toast.LENGTH_SHORT ).show();
        }else */if (selectDate.length()==0){
            selectDate.setError( "choose a date" );
        }else if (startTime.length()==0) {
            startTime.setError( "choose a starting time of booking" );
        }else if (endTime.length()==0) {
            endTime.setError( "choose a ending time" ); }

        else{

        boolean isInserted = mDatabaseHelper.insertData(Ename,
                astName.getSelectedItem().toString(),
                selectDate.getText().toString(),startTime.getText().toString(),endTime.getText().toString() );
        if(isInserted == true){
           // startActivity(new Intent(empDashBoard.this , empDashBoard.class));
            Toast.makeText(empDashBoard.this," Your Asset Booked Successfully",Toast.LENGTH_SHORT).show();

            give_notificatin();
           // Ename.setText("");
            selectDate.setText("");
            startTime.setText("");
            endTime.setText("");
            astName.setSelection(1);


        }
        else
            Toast.makeText(empDashBoard.this,"Choose different date or time",Toast.LENGTH_SHORT).show();





             //   Toast.makeText(empDashBoard.this , assetname + " Booked Successfully!!" , Toast.LENGTH_LONG).show();
              //  give_notificatin();



        }
    }

    public void give_notificatin()
    {
        createNotificationChannel();
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_black_24dp);
        builder.setContentTitle("Conformation of Booking ");
        builder.setContentText("You have successfully  booked an asset.");
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());


    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Personal Notification";
            String description="Include all personal notifications";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel= new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

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

                AlertDialog.Builder builder = new AlertDialog.Builder(empDashBoard.this);
                builder.setMessage("Do you want to Log out from this app??").setCancelable(false)
                        .setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog , int which) {
                                startActivity(new Intent(empDashBoard.this , LoginPage.class));
                                Toast.makeText(empDashBoard.this,"You have Logged out successfully.",Toast.LENGTH_SHORT).show();

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
