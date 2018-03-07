/*
 * Copyright (c) 2015. Diakogeorgiou Kostas
 * All rights reserved.
 */

package uk.co.advmob.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    private ArrayList<Category> categories;
    Context context;

    private LayoutInflater mInflater;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.categories = categories;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return categories.size();
    }

    public Object getItem(int position) {
        return categories.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.categories_row_view, null);
            holder = new ViewHolder();

            holder.txtCategory = (TextView) convertView.findViewById(R.id.txtCategory);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Label values
        holder.txtCategory.setText(categories.get(position).getDescription());

        return convertView;
    }

    static class ViewHolder {
        TextView txtCategory;
    }
}
