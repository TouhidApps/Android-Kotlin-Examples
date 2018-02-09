package com.touhidapps.androidsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextInputName;
    Button buttonSaveSP, buttonShowFromSP, buttonClearSP;
    TextView textViewShowText;

    private static final String MY_PREFERENCES = "MY_PREF";
    private static final String MY_PREF_NAME = "NAME";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        // shared preferences
//      sharedpreferences = getPreferences(MODE_PRIVATE); // for default pref
        sharedpreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE); // for custom pref

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSaveSP:
                saveDataToSharedPreferences();
                break;

            case R.id.buttonShowFromSP:
                showDataFromSharedPreferences();
                break;

            case R.id.buttonClearSP:
                clearDataFromSharedPreferences();
                break;
        }
    }

    private void initUI() {
        editTextInputName = (EditText) findViewById(R.id.editTextInputName);
        buttonSaveSP = (Button) findViewById(R.id.buttonSaveSP);
        buttonShowFromSP = (Button) findViewById(R.id.buttonShowFromSP);
        buttonClearSP = (Button) findViewById(R.id.buttonClearSP);
        textViewShowText = (TextView) findViewById(R.id.textViewShowText);

        buttonSaveSP.setOnClickListener(this);
        buttonShowFromSP.setOnClickListener(this);
        buttonClearSP.setOnClickListener(this);
    }

    private void saveDataToSharedPreferences() {
        sharedpreferences.edit().putString(MY_PREF_NAME, editTextInputName.getText().toString()).apply();
    }

    private void showDataFromSharedPreferences() {
        textViewShowText.setText(sharedpreferences.getString(MY_PREF_NAME, "NO DATA FOUND"));
    }

    private void clearDataFromSharedPreferences() {
        sharedpreferences.edit().clear().apply(); // remove all item
        // sharedpreferences.edit().remove(MY_PREF_NAME).apply(); // remove single item
    }

}
