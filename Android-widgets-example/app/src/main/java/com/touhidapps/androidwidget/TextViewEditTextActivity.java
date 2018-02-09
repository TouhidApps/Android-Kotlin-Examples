package com.touhidapps.androidwidget;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TextViewEditTextActivity extends AppCompatActivity {

    TextView textViewTitle;
    EditText editTextProfile, editTextPassword, editTextZip;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;
    AutoCompleteTextView autoCompleteTextView;
    Button button;

    String[] autoCompleteItems = {"Bangladesh", "Canada", "Malaysia", "Australia", "Japan", "Korea"};
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_edit_text);
        setTitle("Text View & Edit Text");

        initUI();

        textViewTitle.setText("Hello textView");

    }

    private void initUI() {
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);

        editTextProfile = (EditText) findViewById(R.id.editTextProfile);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextZip = (EditText) findViewById(R.id.editTextZip);

        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        textInputEditText = (TextInputEditText) findViewById(R.id.textInputEditText);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        // auto complete
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, autoCompleteItems);
        autoCompleteTextView.setThreshold(1); //number of characters to type before the drop down is shown
        autoCompleteTextView.setAdapter(arrayAdapter);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextProfile.getText().toString().isEmpty() || textInputEditText.getText().toString().isEmpty()) {
                    Toast.makeText(TextViewEditTextActivity.this, "Username and email required", Toast.LENGTH_SHORT).show();
                    textInputLayout.setError("Required!");
                } else {
                    textInputLayout.setError(null);
                    Toast.makeText(TextViewEditTextActivity.this, "Name: " + editTextProfile.getText().toString() + "\n" + "Pass: " + editTextPassword.getText().toString() + "\n" + "Email: " + textInputEditText.getText().toString() + "\n" + "Auto Complete: " + autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
