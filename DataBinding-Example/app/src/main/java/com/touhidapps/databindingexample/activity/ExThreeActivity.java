package com.touhidapps.databindingexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.touhidapps.databindingexample.R;
import com.touhidapps.databindingexample.databinding.ActivityExThreeBinding;

public class ExThreeActivity extends AppCompatActivity {

    private ActivityExThreeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ex_three);
        binding.setMyClickHandler(new MyEventHandler());



    } // onCreate


    public class MyEventHandler {

        public void myClickEventGeneral() {
            Toast.makeText(ExThreeActivity.this, "Click one", Toast.LENGTH_SHORT).show();
        }

        public void myClickEventWithView(View v) {
            Toast.makeText(ExThreeActivity.this, "Click Two " + v.getId(), Toast.LENGTH_SHORT).show();
        }

        public void myClickEventGetInputData(View v) {
            Toast.makeText(ExThreeActivity.this, "Click Three " + binding.editTextName.getText().toString(), Toast.LENGTH_SHORT).show();
        }

        public void myClickE(View v, String n) {
            Toast.makeText(ExThreeActivity.this, "Click kk "+n, Toast.LENGTH_SHORT).show();
        }

    }

}
