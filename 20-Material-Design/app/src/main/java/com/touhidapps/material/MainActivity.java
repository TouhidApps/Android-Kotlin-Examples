package com.touhidapps.material;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCollapsingToolbar, buttonNavigationDrawer, buttonFloatingLabels, buttonFabAndSnakeBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

    }

    private void initUi() {
        buttonCollapsingToolbar = (Button) findViewById(R.id.buttonCollapsingToolbar);
        buttonCollapsingToolbar.setOnClickListener(this);
        buttonNavigationDrawer = (Button) findViewById(R.id.buttonNavigationDrawer);
        buttonNavigationDrawer.setOnClickListener(this);
        buttonFloatingLabels = (Button) findViewById(R.id.buttonFloatingLabels);
        buttonFloatingLabels.setOnClickListener(this);
        buttonFabAndSnakeBar = (Button) findViewById(R.id.buttonFabAndSnakeBar);
        buttonFabAndSnakeBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCollapsingToolbar:
                startActivity(new Intent(this, CollapsingToolbarActivity.class));
                break;
                case R.id.buttonNavigationDrawer:
                startActivity(new Intent(this, NavigationDrawerActivity.class));
                break;
                case R.id.buttonFloatingLabels:
                startActivity(new Intent(this, FloatingLabelEditTextActivity.class));
                break;
                case R.id.buttonFabAndSnakeBar:
                startActivity(new Intent(this, FabAndSnackBarActivity.class));
                break;
        }
    }


}
