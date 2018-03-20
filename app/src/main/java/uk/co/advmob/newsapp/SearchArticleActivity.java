package uk.co.advmob.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_article);

        final ListView lvArticles = findViewById(R.id.lvArticles);

        //List click
        lvArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) lvArticles.getItemAtPosition(position);

                //Show article
                Intent articleActivity = new Intent(SearchArticleActivity.this, ArticleActivity.class);
                articleActivity.putExtra("article", article);

                startActivity(articleActivity);
            }
        });

        String json = getIntent().getStringExtra("json");

        try {
            JSONObject jsonObject = new JSONObject(json);

            Type listType = new TypeToken<List<Article>>() {
            }.getType();

            //Convert dataObject to Article object
            JSONArray jsonArray = jsonObject.getJSONArray("dataObject");
            ArrayList<Article> articles = new Gson().fromJson(String.valueOf(jsonArray), listType);

            ArticlesAdapter articlesAdapter = new ArticlesAdapter(SearchArticleActivity.this, articles);
            lvArticles.setAdapter(articlesAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
