package com.touhidapps.androidnotificationexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NAME = "name";
    public static final String INFO = "info";
    private static final String MY_ACTION = "my.action";
    public static final String NOTIF_ID = "notif_id";

    Button buttonGeneral, buttonInbox, buttonExpandable, buttonCustom;
    List<String> lines = new ArrayList<>();

    int numMessages = 0;
    Notification.Builder builder;
    Notification.InboxStyle notificationStyle;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGeneral = findViewById(R.id.buttonGeneral);
        buttonInbox = findViewById(R.id.buttonInbox);
        buttonExpandable = findViewById(R.id.buttonExpandable);
        buttonCustom = findViewById(R.id.buttonCustom);
        buttonGeneral.setOnClickListener(this);
        buttonInbox.setOnClickListener(this);
        buttonExpandable.setOnClickListener(this);
        buttonCustom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonGeneral:
                createNotificationOne();
                break;

            case R.id.buttonInbox:
                lines.add(numMessages + " Zero");
                createNotificationThree(lines);
                break;

            case R.id.buttonExpandable:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        createNotificationExpandable();
                    }
                }).start();

                break;

            case R.id.buttonCustom:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        createNotificationBigImage(new NotificationDataModel(
                                "Touhid",
                                "Good boy",
                                "This is a title expanded",
                                "My App Notification",
                                "Working fine",
                                "https://farm3.staticflickr.com/2845/11445430894_dd7910de7d_z.jpg"
                        ));
                    }
                }).start();

                break;
        }
    }

    public void createNotificationOne() {
//        // Prepare intent which is triggered if the
//        // notification is selected
//        Intent intent = new Intent(this, NotificationActivity.class);
//        intent.setAction(MY_ACTION);
//        intent.putExtra(NAME, "Touhid");
//        intent.putExtra(INFO, "Touhid Apps!");
//
//        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0); //PendingIntent.FLAG_UPDATE_CURRENT
//
//        // Build notification
//        // Actions are just fake
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager != null) {
//            String channelID = "My Channel ID"; // The id of the channel.
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = new NotificationChannel(channelID, "My_Name", importance);
//            // Create a notification and set the notification channel.
//            Notification notification = getNotificationBuilder(pIntent).setChannelId(channelID).build();
//            notificationManager.createNotificationChannel(mChannel);
//            notificationManager.notify(1, notification);
//        } else if (notificationManager != null) {
//            NotificationCompat.Builder notificationBuilder = getNotificationBuilder(pIntent);
//            notificationBuilder.setPriority(Notification.PRIORITY_MAX);
//            notificationManager.notify(1 /* ID of notification */, notificationBuilder.build());
//        }


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        RemoteViews notificationView = getNotificationView();

        if(notificationView == null)
            return;

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.flower);

        notificationBuilder.setContent(notificationView);

        notificationManager.notify(1000, notificationBuilder.build());



    }

    private RemoteViews getNotificationView(){
        RemoteViews notificationView = new RemoteViews(getPackageName(), R.layout.custom_notif_layout_two);

//        notificationView.setImageViewBitmap(R.id.icon, myDrawable);
        notificationView.setImageViewResource(R.id.icon, R.drawable.flower);
        notificationView.setTextViewText(R.id.the_title, "OMGWTFBBQOMGWTFBBQOMGWTFBBQOMGWTFBBQOMGWTFBBQOMGWTFBBQOMGWTFBBQOMGWTFBBQOMGWTFBBQ");
        notificationView.setTextViewText(R.id.some_text_left, "blablablabla, orange, apple");
        notificationView.setTextViewText(R.id.some_text_right, "50%");

        return notificationView;
    }


    private NotificationCompat.Builder getNotificationBuilder(PendingIntent pendingIntent) {
        return new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_action_call)
                .setContentTitle("dkjfhisd")
                .setContentText("sdos")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("skjsforisufpoeijfaojf"))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
//                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
    }


    public void createNotificationThree(List<String> lines) {
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setAction(MY_ACTION);
        intent.putExtra(NAME, "Touhid");
        intent.putExtra(INFO, "Touhid Apps!");

        PendingIntent piResult = PendingIntent.getActivity(this, 0, intent, 0);

        builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_active)
                .setContentTitle("contentTitle")
                .setContentText("contentText")
                .setAutoCancel(true) // hide the notification after its selected
                .setContentIntent(piResult);

        notificationStyle = new Notification.InboxStyle(builder);
        for (int i = 0; i < lines.size(); i++) {
            notificationStyle.addLine(lines.get(i));
        }
        notificationStyle.setBigContentTitle("Here Your Messages");

        numMessages++;
        if (lines.size() > 7) {
            int count = lines.size() - 7;
            notificationStyle.setSummaryText("+" + count + " more");
        }

        notification = notificationStyle.build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(121, notification);

    }


    public void createNotificationExpandable(String... stringData) {

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setAction(MY_ACTION);
        intent.putExtra(NAME, "Touhid");
        intent.putExtra(INFO, "Touhid Apps!");

        PendingIntent piResult = PendingIntent.getActivity(this, 0, intent, 0);


        // Create the style object with BigPictureStyle subclass.
        Notification.BigPictureStyle notiStyle = new Notification.BigPictureStyle();
        notiStyle.setBigContentTitle("Big Picture Expanded");
        notiStyle.setSummaryText("Nice big picture.");


        Bitmap remote_picture = null;
        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL("https://farm3.staticflickr.com/2845/11445430894_dd7910de7d_z.jpg").getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the big picture to the style.
        notiStyle.bigPicture(remote_picture);

//        // Creates an explicit intent for an ResultActivity to receive.
//        Intent resultIntent = new Intent(this, NotificationActivity.class);
//
//        // This ensures that the back button follows the recommended
//        // convention for the back key.
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//
//        // Adds the back stack for the Intent (but not the Intent itself).
//        stackBuilder.addParentStack(NotificationActivity.class);
//
//        // Adds the Intent that starts the Activity to the top of the stack.
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification myNotification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_active)
                .setAutoCancel(true)
                .setLargeIcon(remote_picture)
                .setContentIntent(piResult)
                .setContentTitle("Big Picture Normal")
                .setContentText("This is an example of a Big Picture Style.")
                .setStyle(notiStyle).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(12, myNotification);

    }


    /**
     * Data will be:
     * name and info to show result activity (NotificationActivity.java)
     *
     * @param notificationDataModel should be 5 item
     *                              name
     *                              info
     *                              title text expanded view
     *                              title small view
     *                              detail small view
     */
    public void createNotificationBigImage(NotificationDataModel notificationDataModel) {
        int notId = 11;

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setAction(MY_ACTION);
        intent.putExtra(NAME, notificationDataModel.getName());
        intent.putExtra(INFO, notificationDataModel.getInfo());
        intent.putExtra(NOTIF_ID, notId + "");

        PendingIntent piResult = PendingIntent.getActivity(this, 0, intent, 0);

        // Create remote view and set bigContentView.
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.notification_custom);
        remoteViews.setTextViewText(R.id.textViewTitle, notificationDataModel.getTitleExpanded());

        Bitmap remote_picture = null;
        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL(notificationDataModel.getImageUrl()).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            remoteViews.setImageViewBitmap(R.id.imageViewSmallImage, remote_picture);
            remoteViews.setImageViewBitmap(R.id.imageViewBigImage, remote_picture);
        } catch (Exception e) {
            e.printStackTrace();
        }

        remoteViews.setOnClickPendingIntent(R.id.buttonSave, piResult);

//        Notification myNotification = new Notification.Builder(this)
//                .setSmallIcon(R.drawable.ic_stat_active)
//                .setAutoCancel(true)
//                .setContentIntent(piResult)
//                .setContentTitle(notificationDataModel.getTitleSmall())
//                .setContentText(notificationDataModel.getDetailSmall())
//                .build();
//        myNotification.bigContentView = remoteViews;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager != null) {
            String channelID = "My Channel ID"; // The id of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelID, "My_Name", importance);
            // Create a notification and set the notification channel.
            Notification notification = getNotificationBuilder(piResult).setChannelId(channelID).build();
            notification.bigContentView = remoteViews;
            notificationManager.createNotificationChannel(mChannel);
            notificationManager.notify(1, notification);

        } else if (notificationManager != null) {
            NotificationCompat.Builder notificationBuilder = getNotificationBuilder(piResult);
            notificationBuilder.setCustomBigContentView(remoteViews);
            notificationManager.notify(1 /* ID of notification */, notificationBuilder.build());
        }

    }


}
