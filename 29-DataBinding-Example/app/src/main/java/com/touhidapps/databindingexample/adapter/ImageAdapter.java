package com.touhidapps.databindingexample.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ImageAdapter {

    @BindingAdapter({"mImageUrl", "mPlaceholder"}) // setMyImageUrl is a custom property in imageView
    public static void setImage(ImageView v, String url, Drawable placeholderImage) {
        Toast.makeText(v.getContext(), "Loading image", Toast.LENGTH_SHORT).show();
        Glide.with(v.getContext()).load(url).placeholder(placeholderImage).into(v);
    } // setImage

}