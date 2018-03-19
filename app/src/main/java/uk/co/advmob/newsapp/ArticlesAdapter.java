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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

            holder.txtTitle = convertView.findViewById(R.id.txtTitle);
            holder.txtDate = convertView.findViewById(R.id.txtDate);
            holder.txtAuthor = convertView.findViewById(R.id.txtAuthor);
            holder.txtCategory = convertView.findViewById(R.id.txtCategory);
            holder.imgArticleImage = convertView.findViewById(R.id.imgArticleImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Label values and image
        holder.txtTitle.setText(articles.get(position).getTitle());
        holder.txtDate.setText(articles.get(position).getDate());
        holder.txtAuthor.setText(articles.get(position).getAuthor().getFullName());
        holder.txtCategory.setText(articles.get(position).getCategory().getDescription());
        Picasso.with(context)
                .load("http://newsapi.dkode.co.uk/uploads/" + articles.get(position).getImage())
                .into(holder.imgArticleImage);

        return convertView;
    }

    static class ViewHolder {
        TextView txtTitle;
        TextView txtDate;
        TextView txtAuthor;
        TextView txtCategory;
        ImageView imgArticleImage;
    }
}
