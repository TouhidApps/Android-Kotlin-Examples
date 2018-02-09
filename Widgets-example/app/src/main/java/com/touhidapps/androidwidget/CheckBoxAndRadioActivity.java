package com.touhidapps.androidwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class CheckBoxAndRadioActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox checkboxAndroid, checkboxIos;
    Button buttonResultCheck, buttonRadio;
    RadioButton radioButtonWindows, radioButtonMac;
    RadioGroup radioGroup;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_and_radio);
        setTitle("Check box, Radio & Switch");

        // check box
        checkboxAndroid = (CheckBox) findViewById(R.id.checkboxAndroid);
        checkboxIos = (CheckBox) findViewById(R.id.checkboxIos);

        // radio
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButtonWindows = (RadioButton) findViewById(R.id.radioButtonWindows);
        radioButtonMac = (RadioButton) findViewById(R.id.radioButtonMac);

        // switch
        switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    Toast.makeText(CheckBoxAndRadioActivity.this, "Status On", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckBoxAndRadioActivity.this, "Status Off", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // buttons
        buttonResultCheck = (Button) findViewById(R.id.buttonResultCheck);
        buttonRadio = (Button) findViewById(R.id.buttonRadio);
        buttonResultCheck.setOnClickListener(this);
        buttonRadio.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonResultCheck:
                if (checkboxAndroid.isChecked() && checkboxIos.isChecked()) {
                    Toast.makeText(this, "Android + iOS", Toast.LENGTH_SHORT).show();
                } else if (checkboxAndroid.isChecked()) {
                    Toast.makeText(this, "Android", Toast.LENGTH_SHORT).show();
                } else if (checkboxIos.isChecked()) {
                    Toast.makeText(this, "iOS", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.buttonRadio:

                if (radioButtonWindows.isChecked()) {
                    Toast.makeText(this, "Windows", Toast.LENGTH_SHORT).show();
                } else if (radioButtonMac.isChecked()) {
                    Toast.makeText(this, "Mac", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
