package com.example.xipproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class empBookingActivity extends AppCompatActivity {


    private final String CHANNEL_ID="Personal Notification";
    public static final int NOTIFICATION_ID=001;
    private int[] image_id= {R.drawable.conference_1, R.drawable.conference_2, R.drawable.conference_3, R.drawable.conference_4, R.drawable.conference_5, R.drawable.projector_1,R.drawable.projector_2,R.drawable.projector_3};

    private String[] asset_Name;
    private List<Asset> Asset =new ArrayList<>();

    private RecyclerView recyclerView;
    private emp_recyclerAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_booking);

        asset_Name=getResources().getStringArray(R.array.asset_name);
        recyclerView=findViewById(R.id.mEmp_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getAsset();
        adaptor=new emp_recyclerAdaptor(Asset);
        recyclerView.setAdapter(adaptor);


    }

    private void getAsset()
    {
        int count=0;
        for(String assetText :asset_Name)
        {
            Asset asset=new Asset(image_id[count],assetText);
            //Asset asset =new Asset(assetText);
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
                adaptor.removedItem(item.getGroupId());
                give_notificatin();
                displayMessage("item is Booked...");

                return true;


            default:
                return super.onContextItemSelected(item);

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


    private void displayMessage(String message)
    {
        Snackbar.make(findViewById(R.id.root_emp_view),message,Snackbar.LENGTH_SHORT).show();

    }
}
