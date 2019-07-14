package com.touhidapps.recyclerviewandvolleyrequests;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Touhid on 7/26/2017.
 */

public class MyRecyclerAdapterGrid extends RecyclerView.Adapter<MyRecyclerAdapterGrid.ViewHolder> {

    private ItemClickListener mClickListener;
    private Context context;
    private List<DemoDataModel> demoDataModels;

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // implements View.OnClickListener

        public ImageView imageViewRecyclerItem;
        public TextView textViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewRecyclerItem = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // Data is passed into the constructor
    public MyRecyclerAdapterGrid(Context context, List<DemoDataModel> demoDataModels) {
        this.context = context;
        this.demoDataModels = demoDataModels;
    }

    // Inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_demo_data_recycler_grid, parent, false);

        return new ViewHolder(itemView);
    }

    // binds the data to the textView in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DemoDataModel demoDataModel = demoDataModels.get(position);
        String imageFullUrl = demoDataModel.getBaseUrl() + demoDataModel.getFileName();
        holder.textViewTitle.setText(demoDataModel.getName());
        Glide.with(context).load(imageFullUrl).into(holder.imageViewRecyclerItem);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return demoDataModels.size();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
