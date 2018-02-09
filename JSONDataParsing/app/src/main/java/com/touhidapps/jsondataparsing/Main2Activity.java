package com.touhidapps.jsondataparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.touhidapps.jsondataparsing.model.Category;
import com.touhidapps.jsondataparsing.model.Collection;
import com.touhidapps.jsondataparsing.model.MyJsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    WebView webView;
    TextView textView;
    List<Collection> collections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        webView = findViewById(R.id.webView);
        textView = findViewById(R.id.textView);
        webView.getSettings().setJavaScriptEnabled(true);

        String id = getIntent().getStringExtra("ID");
        String title = getIntent().getStringExtra("TITLE");

        // =========================

        String json = null;
        try {
            InputStream is = getAssets().open("json/myjson.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (json == null) {
            return;
        }

        MyJsonObject myJsonObject = new Gson().fromJson(json, MyJsonObject.class);
        collections.addAll(myJsonObject.getCollection());


        // =============================

        for (Collection c : collections) {
            if (c.getCategoryId().trim().equalsIgnoreCase(id.trim())) {
                textView.setText(title);
                webView.loadData(c.getDescr(), "text/html", "UTF-8");
                break;
            }

        }


    }
}
