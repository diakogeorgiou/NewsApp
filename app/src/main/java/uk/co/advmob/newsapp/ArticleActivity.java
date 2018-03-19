package uk.co.advmob.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ArticleActivity extends AppCompatActivity {
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        article = (Article) getIntent().getSerializableExtra("article");

        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtDate = findViewById(R.id.txtDate);
        TextView txtAuthor = findViewById(R.id.txtAuthor);
        TextView txtBody = findViewById(R.id.txtBody);
        ImageView imgArticleImage = findViewById(R.id.imgArticleImage);

        txtTitle.setText(article.getTitle());
        txtDate.setText(article.getDate());
        txtAuthor.setText(article.getAuthor().getFullName());
        txtBody.setText(article.getContent());
        Picasso.with(this)
                .load("http://newsapi.dkode.co.uk/uploads/" + article.getImage())
                .into(imgArticleImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_single_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Fake article
        if (id==R.id.menu_report_fake) {
            new ApiConnection(ArticleActivity.this).reportFake(article.getId(), SingleSignOn.getUser_id(), new ApiCallback() {
                @Override
                public void onSuccessResponse(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getBoolean("error")) {
                            Toast.makeText(ArticleActivity.this, jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ArticleActivity.this, "Article reported as fake.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //Like article
        if (id==R.id.menu_like) {
            new ApiConnection(ArticleActivity.this).likeArticle(article.getId(), SingleSignOn.getUser_id(), new ApiCallback() {
                @Override
                public void onSuccessResponse(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getBoolean("error")) {
                            Toast.makeText(ArticleActivity.this, jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ArticleActivity.this, "Article is marked as liked", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }
}
