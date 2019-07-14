package com.touhidapps.androidwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerActivity extends AppCompatActivity {

    Spinner spinner, spinnerCustom;
    String[] brandNames = {"Apple", "Dell", "Asus", "HP"};
    ArrayAdapter arrayAdapter, arrayAdapterCustom;
    Button buttonCheckSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        setTitle("Spinner Example");

        spinner = (Spinner) findViewById(R.id.spinner);
        buttonCheckSpinner = (Button) findViewById(R.id.buttonCheckSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "" + arrayAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brandNames);
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, brandNames); // for no padding items
        spinner.setAdapter(arrayAdapter);

        buttonCheckSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SpinnerActivity.this, spinner.getSelectedItemId() + " "
                        + spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });



        // custom spinner with custom xml
        spinnerCustom = (Spinner) findViewById(R.id.spinnerCustom);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "" + arrayAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        arrayAdapterCustom = new ArrayAdapter(this, R.layout.spinner_custom, brandNames);
        spinnerCustom.setAdapter(arrayAdapterCustom);
    }



}
