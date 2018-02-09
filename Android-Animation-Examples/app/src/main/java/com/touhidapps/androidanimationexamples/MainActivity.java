package com.touhidapps.androidanimationexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonOne, buttonTwo, buttonThree;
    LinearLayout linearLayoutOne, linearLayoutTwo;
    ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

    }

    private void initUI() {
        linearLayoutOne = (LinearLayout) findViewById(R.id.linearLayoutOne);
        linearLayoutTwo = (LinearLayout) findViewById(R.id.linearLayoutTwo);
        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonThree = (Button) findViewById(R.id.buttonThree);
        imageViewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        // starting animation
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        linearLayoutOne.startAnimation(shake);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOne:
                startActivity(new Intent(this, SecondActivity.class));
                break;

            case R.id.buttonTwo:
                TransitionManager.beginDelayedTransition(linearLayoutOne);
                if (buttonOne.getVisibility() == View.VISIBLE) {
                    buttonOne.setVisibility(View.GONE);
                } else {
                    buttonOne.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.buttonThree:
                TransitionManager.beginDelayedTransition(linearLayoutTwo, new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeImageTransform()));
                if (imageViewLogo.getScaleType()==ImageView.ScaleType.FIT_CENTER) {
                    imageViewLogo.setScaleType(ImageView.ScaleType.CENTER);
                } else {
                    imageViewLogo.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
                break;
        }
    }


}
