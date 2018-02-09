package com.touhidapps.androidwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    ListView listView, listViewCustom;

    ArrayAdapter<String> arrayAdapter;
    String[] listItems = {"Facebook", "Youtube", "Instagram", "LinkedIn", "Google Plus", "Yahoo", "Bing"};

    // Custom
    ListViewCustomAdapter listViewCustomAdapter;
    List<MyDataModel> myDataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "" + arrayAdapter.getItem(i), Toast.LENGTH_SHORT).show();
            }
        });


        // custom list
        listViewCustom = (ListView) findViewById(R.id.listViewCustom);

        MyDataModel myDataModel1 = new MyDataModel();
        myDataModel1.setName("Image");
        myDataModel1.setImage(R.drawable.imgbtn);
        myDataModels.add(myDataModel1);

        MyDataModel myDataModel2 = new MyDataModel();
        myDataModel2.setName("Nature");
        myDataModel2.setImage(R.drawable.nature);
        myDataModels.add(myDataModel2);

        listViewCustomAdapter = new ListViewCustomAdapter(this, myDataModels);

        listViewCustom.setAdapter(listViewCustomAdapter);

        listViewCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = view.findViewById(R.id.textView);
                Toast.makeText(ListViewActivity.this, "" + textView.getTag().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
