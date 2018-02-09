package com.touhidapps.androidwidget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAlertDialog, buttonAlertDialogCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        setTitle("Alert Dialog");

        buttonAlertDialog = (Button) findViewById(R.id.buttonAlertDialog);
        buttonAlertDialogCustom = (Button) findViewById(R.id.buttonAlertDialogCustom);

        buttonAlertDialog.setOnClickListener(this);
        buttonAlertDialogCustom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAlertDialog:

                AlertDialog.Builder builder = new AlertDialog.Builder(this); //AlertDialogActivity.this
                builder.setTitle("This is a title");
                builder.setIcon(R.drawable.ic_action_alert);
                builder.setMessage("This is alert message");
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlertDialogActivity.this, "Yes clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();

                break;

            case R.id.buttonAlertDialogCustom:

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.alert_dialog_custom);

                TextView textView = (TextView) dialog.findViewById(R.id.textViewAlertMessage);
                textView.setText("Here is some message");
                ImageView imageView = (ImageView) dialog.findViewById(R.id.imageViewDialog);
                imageView.setImageResource(R.drawable.nature);
                Button buttonAlertNo = (Button) dialog.findViewById(R.id.buttonAlertNo);
                Button buttonAlertYes = (Button) dialog.findViewById(R.id.buttonAlertYes);
                buttonAlertNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                buttonAlertYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AlertDialogActivity.this, "Yes clicked", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();

                break;
        }
    }
}
