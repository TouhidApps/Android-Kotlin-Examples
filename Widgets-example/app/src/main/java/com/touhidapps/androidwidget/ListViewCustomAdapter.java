package com.touhidapps.androidwidget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by touhid on 8/18/17.
 */

public class ListViewCustomAdapter extends ArrayAdapter<MyDataModel>{

    private List<MyDataModel> myDataModels;
    Context mContext;

    public ListViewCustomAdapter(Context context, List<MyDataModel> dataModels) {
        super(context, R.layout.row_list_items, dataModels);

        this.mContext = context;
        this.myDataModels = dataModels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_list_items, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        textView.setText(myDataModels.get(position).getName());
        imageView.setImageResource(myDataModels.get(position).getImage());

        textView.setTag(myDataModels.get(position).getName()); // use to send a hidden id or other value which is not visible to users but need after click event

        return rowView;
    }


}
