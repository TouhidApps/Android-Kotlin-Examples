package com.touhidapps.databindingexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.touhidapps.databindingexample.R;
import com.touhidapps.databindingexample.databinding.RowItemBinding;
import com.touhidapps.databindingexample.model.Person;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<Person> personList;

    public MyRecyclerAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowItemBinding rowItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_item,
                parent,
                false);
        return new MyViewHolder(rowItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RowItemBinding binding = DataBindingUtil.bind(holder.itemView);
        binding.setPerson(personList.get(position));
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return this.personList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }



}


