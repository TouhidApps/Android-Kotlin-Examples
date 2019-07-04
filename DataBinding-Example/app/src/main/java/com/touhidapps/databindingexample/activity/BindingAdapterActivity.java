package com.touhidapps.databindingexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.touhidapps.databindingexample.R;
import com.touhidapps.databindingexample.databinding.ActivityBindingAdapterBinding;

public class BindingAdapterActivity extends AppCompatActivity {

    private ActivityBindingAdapterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_binding_adapter);


        binding.setMyImgUrl("https://processing.org/tutorials/pixels/imgs/tint1.jpg");


    } // onCreate


    @BindingAdapter("setMyImageUrl") // setMyImageUrl is a custom property in imageView
    public static void setImage(ImageView v, String url) {
        Toast.makeText(v.getContext(), "Loading image", Toast.LENGTH_SHORT).show();
        Glide.with(v.getContext()).load(url).into(v);
    } // setImage

}
