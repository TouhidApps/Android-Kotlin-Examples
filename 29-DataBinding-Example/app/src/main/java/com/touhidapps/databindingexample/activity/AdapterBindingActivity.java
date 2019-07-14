package com.touhidapps.databindingexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.touhidapps.databindingexample.R;
import com.touhidapps.databindingexample.adapter.MyRecyclerAdapter;
import com.touhidapps.databindingexample.databinding.ActivityAdapterBindingBinding;
import com.touhidapps.databindingexample.model.Person;

import java.util.ArrayList;
import java.util.List;

public class AdapterBindingActivity extends AppCompatActivity {

    private ActivityAdapterBindingBinding binding;
    private List<Person> personList = new ArrayList<>();
    private MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_adapter_binding);

        myRecyclerAdapter = new MyRecyclerAdapter(personList);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.recyclerView.setAdapter(myRecyclerAdapter);

        // load data
        personList.add(new Person("Touhidul", "Islam", "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        personList.add(new Person("Touhidul", "Islam", "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        personList.add(new Person("Touhidul", "Islam", "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        personList.add(new Person("Touhidul", "Islam", "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        personList.add(new Person("Touhidul", "Islam", "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        personList.add(new Person("Touhidul", "Islam", "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));

        binding.setPersonList(personList);


    } // onCreate




}
