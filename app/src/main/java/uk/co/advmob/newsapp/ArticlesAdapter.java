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

public class ArticlesAdapter extends BaseAdapter {
    private ArrayList<Article> articles;
    Context context;

    private LayoutInflater mInflater;

    public ArticlesAdapter(Context context, ArrayList<Article> articles) {
        this.articles = articles;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return articles.size();
    }

    public Object getItem(int position) {
        return articles.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.article_row_view, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            holder.txtAuthor = (TextView) convertView.findViewById(R.id.txtAuthor);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Label values
        holder.txtTitle.setText(articles.get(position).getTitle());
        holder.txtDate.setText(articles.get(position).getDate());
        holder.txtAuthor.setText(articles.get(position).getAuthor().getFullName());

        return convertView;
    }

    static class ViewHolder {
        TextView txtTitle;
        TextView txtDate;
        TextView txtAuthor;
    }
}
