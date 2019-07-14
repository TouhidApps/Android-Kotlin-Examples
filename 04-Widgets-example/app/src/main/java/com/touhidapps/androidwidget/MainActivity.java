package com.touhidapps.androidwidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonButton, buttonToast, buttonCheckBoxAndRadio, buttonAlertDialog, buttonSpinner,
            buttonTextViewEditText, buttonRatingBar, buttonDateTimePicker,
            buttonWebView, buttonListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

    }

    private void initUi() {
        buttonButton = (Button) findViewById(R.id.buttonButton);
        buttonToast = (Button) findViewById(R.id.buttonToast);
        buttonCheckBoxAndRadio = (Button) findViewById(R.id.buttonCheckBoxAndRadio);
        buttonAlertDialog = (Button) findViewById(R.id.buttonAlertDialog);
        buttonSpinner = (Button) findViewById(R.id.buttonSpinner);
        buttonTextViewEditText = (Button) findViewById(R.id.buttonTextViewEditText);
        buttonRatingBar = (Button) findViewById(R.id.buttonRatingBar);
        buttonDateTimePicker = (Button) findViewById(R.id.buttonDateTimePicker);
        buttonWebView = (Button) findViewById(R.id.buttonWebView);
        buttonListView = (Button) findViewById(R.id.buttonListView);
        // event listener
        buttonButton.setOnClickListener(this);
        buttonToast.setOnClickListener(this);
        buttonCheckBoxAndRadio.setOnClickListener(this);
        buttonAlertDialog.setOnClickListener(this);
        buttonSpinner.setOnClickListener(this);
        buttonTextViewEditText.setOnClickListener(this);
        buttonRatingBar.setOnClickListener(this);
        buttonDateTimePicker.setOnClickListener(this);
        buttonWebView.setOnClickListener(this);
        buttonListView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonButton:
                startActivity(new Intent(this, ButtonActivity.class));
                break;

            case R.id.buttonToast:
                startActivity(new Intent(this, ToastActivity.class));
                break;

            case R.id.buttonCheckBoxAndRadio:
                startActivity(new Intent(this, CheckBoxAndRadioActivity.class));
                break;

            case R.id.buttonAlertDialog:
                startActivity(new Intent(this, AlertDialogActivity.class));
                break;

            case R.id.buttonSpinner:
                startActivity(new Intent(this, SpinnerActivity.class));
                break;

            case R.id.buttonTextViewEditText:
                startActivity(new Intent(this, TextViewEditTextActivity.class));
                break;

            case R.id.buttonRatingBar:
                startActivity(new Intent(this, RatingBarActivity.class));
                break;

            case R.id.buttonDateTimePicker:
                startActivity(new Intent(this, DateTimePickerActivity.class));
                break;

            case R.id.buttonWebView:
                startActivity(new Intent(this, WebViewActivity.class));
                break;

            case R.id.buttonListView:
                startActivity(new Intent(this, ListViewActivity.class));
                break;


        }
    }
}
