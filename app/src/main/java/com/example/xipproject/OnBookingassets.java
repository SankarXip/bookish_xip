package com.example.xipproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class OnBookingassets extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener
{

    Button pick;
    TextView result;
    int day,month,year,hour,minute;
    int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bookingassets);

        pick = (Button) findViewById(R.id.pick_button_id);
        result = (TextView) findViewById(R.id.text_result_id);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                year= cal.get(Calendar.YEAR);
                month= cal.get(Calendar.MONTH);
                day= cal.get(Calendar.DATE);

                DatePickerDialog datePickerDialog=new DatePickerDialog(OnBookingassets.this,OnBookingassets.this
                        ,year,month,day);
                datePickerDialog.show();

            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal=year;
        monthFinal=month+1;
        dayFinal=dayOfMonth;

        Calendar cal=Calendar.getInstance();
        hour=cal.get(Calendar.HOUR_OF_DAY);
        minute=cal.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog= new TimePickerDialog(OnBookingassets.this,
                OnBookingassets.this,hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view , int hourOfDay , int minute) {
        hourFinal=hourOfDay;
        minuteFinal=minute;

        result.setText("year:"+year+" "+"month:"+month+" "+"day:"+day+" \n"+"hour:"+hour+" "+"minute:"+minute+" ");

    }

    public void avilAssets(View view) {
        Intent intent=new Intent(OnBookingassets.this,empBookingActivity.class);
        startActivity(intent);

    }
}


