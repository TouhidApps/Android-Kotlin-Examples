package com.touhidapps.chatapptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * This MainActivity has firebase analytics
 * ChatActivity has chat part
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAnalytics mFirebaseAnalytics;
    Button buttonOne, buttonTwo, buttonThree, buttonChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        initUI();

    }

    private void initUI() {
        buttonOne = findViewById(R.id.buttonOne);
        buttonTwo = findViewById(R.id.buttonTwo);
        buttonThree = findViewById(R.id.buttonThree);
        buttonChat = findViewById(R.id.buttonChat);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Analytics for all click event
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        // send my data
        makeFirebaseAnalytics(String.valueOf(v.getId()), buttonText, "Button");

        switch (v.getId()) {
            case R.id.buttonChat:
                startActivity(new Intent(this, ChatRoomActivity.class));
                break;
        }
    }

    // Analytics
    public void makeFirebaseAnalytics(String itemId, String itemName, String contentType){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemId);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }


}
