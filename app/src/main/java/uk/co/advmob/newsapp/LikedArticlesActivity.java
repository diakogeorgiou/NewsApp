package uk.co.advmob.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LikedArticlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_articles);

        final ListView lvArticles = findViewById(R.id.lvArticles);

        //List click
        lvArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) lvArticles.getItemAtPosition(position);

                //Show article
                Intent articleActivity = new Intent(LikedArticlesActivity.this, ArticleActivity.class);
                articleActivity.putExtra("article", article);

                startActivity(articleActivity);
            }
        });

        new ApiConnection(LikedArticlesActivity.this).getLikedArticles(SingleSignOn.getUser_id(), new ApiCallback() {
            @Override
            public void onSuccessResponse(JSONObject jsonObject) {
                try {
                    if (jsonObject.getBoolean("error")) {
                        Toast.makeText(LikedArticlesActivity.this, jsonObject.getString("errormessage"), Toast.LENGTH_SHORT).show();
                    } else {
                        Type listType = new TypeToken<List<Article>>() {
                        }.getType();

                        //Convert dataObject to Article object
                        JSONArray jsonArray = jsonObject.getJSONArray("dataObject");
                        ArrayList<Article> articles = new Gson().fromJson(String.valueOf(jsonArray), listType);

                        if (articles.isEmpty()) {
                            Toast.makeText(LikedArticlesActivity.this, "No liked articles", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                        ArticlesAdapter articlesAdapter = new ArticlesAdapter(LikedArticlesActivity.this, articles);
                        lvArticles.setAdapter(articlesAdapter);
                    }
                } catch (Exception e) {
                    Toast.makeText(LikedArticlesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
