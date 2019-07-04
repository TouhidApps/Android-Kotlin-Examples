package com.touhidapps.databindingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.touhidapps.databindingexample.activity.AdapterBindingActivity;
import com.touhidapps.databindingexample.activity.BindingAdapterActivity;
import com.touhidapps.databindingexample.activity.ExOneActivity;
import com.touhidapps.databindingexample.activity.ExThreeActivity;
import com.touhidapps.databindingexample.activity.ExTwoActivity;
import com.touhidapps.databindingexample.activity.ExpressionLanguageActivity;
import com.touhidapps.databindingexample.activity.MyObservableActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exOne(View view) {
        startActivity(new Intent(this, ExOneActivity.class));
    }


    public void exTwo(View view) {
        startActivity(new Intent(this, ExTwoActivity.class));
    }


    public void exThree(View view) {
        startActivity(new Intent(this, ExThreeActivity.class));
    }


    public void exExpression(View view) {
        startActivity(new Intent(this, ExpressionLanguageActivity.class));
    }

    public void exRecyclerAdapter(View view) {
        startActivity(new Intent(this, AdapterBindingActivity.class));
    }

    public void exBindingAdapter(View view) {
        startActivity(new Intent(this, BindingAdapterActivity.class));
    }

    public void exObservable(View view) {
        startActivity(new Intent(this, MyObservableActivity.class));
    }

}
