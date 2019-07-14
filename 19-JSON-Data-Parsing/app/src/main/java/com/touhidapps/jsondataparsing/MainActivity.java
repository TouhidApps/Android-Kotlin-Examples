package com.touhidapps.jsondataparsing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.touhidapps.jsondataparsing.adapter.CategoryAdapter;
import com.touhidapps.jsondataparsing.model.Category;
import com.touhidapps.jsondataparsing.model.MyJsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    List<Category> categories = new ArrayList<>();
    CategoryAdapter categoryArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        categoryArrayAdapter = new CategoryAdapter(this, categories);
        listView.setAdapter(categoryArrayAdapter);

        listView.setOnItemClickListener(this);

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
        categories.addAll(myJsonObject.getCategory());


        categoryArrayAdapter.notifyDataSetChanged();
        Log.d("ttt", "onCreate: "+myJsonObject.getCategory().get(0).getId());




    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("ID", categories.get(i).getId());
        intent.putExtra("TITLE", categories.get(i).getTitle());
        startActivity(intent);
    }
}
