package com.touhidapps.material;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FabAndSnackBarActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout relativeLayout;
    FloatingActionButton fab;
    Button buttonSimpleSnakeBar, buttonActionCallback, buttonCustomSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_and_snack_bar);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        buttonSimpleSnakeBar = (Button) findViewById(R.id.buttonSimpleSnakeBar);
        buttonActionCallback = (Button) findViewById(R.id.buttonActionCallback);
        buttonCustomSnackBar = (Button) findViewById(R.id.buttonCustomSnackBar);
        buttonSimpleSnakeBar.setOnClickListener(this);
        buttonActionCallback.setOnClickListener(this);
        buttonCustomSnackBar.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Toast.makeText(FabAndSnackBarActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonSimpleSnakeBar:
                Snackbar snackbar = Snackbar.make(relativeLayout, "Hello Snack Bar", Snackbar.LENGTH_LONG);
                snackbar.show();
                break;

            case R.id.buttonActionCallback:
                Snackbar s = Snackbar
                        .make(relativeLayout, "Message is deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(relativeLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                s.show();
                break;

            case R.id.buttonCustomSnackBar:
                Snackbar sb = Snackbar
                        .make(relativeLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(FabAndSnackBarActivity.this, "Snack Bar clicked", Toast.LENGTH_SHORT).show();
                            }
                        });

                // Changing message text color
                sb.setActionTextColor(Color.RED);
                sb.getView().setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));

                // Changing action button text color
                View sbView = sb.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                sb.show();
                break;

        }
    }
}
