package com.touhidapps.jsondataparsing.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.touhidapps.jsondataparsing.R;
import com.touhidapps.jsondataparsing.model.Category;

import java.util.List;


/**
 * Created by touhid on 1/4/18.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {

    private List<Category> categories;
    Context mContext;

    public CategoryAdapter(@NonNull Context context, List<Category> categories) {
        super(context, R.layout.row_category, categories);

        this.categories = categories;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_category, parent, false);
        TextView textView = rowView.findViewById(R.id.textView);
        textView.setText(categories.get(position).getTitle());
        textView.setTag(categories.get(position).getId());
        return rowView;
    }
}
