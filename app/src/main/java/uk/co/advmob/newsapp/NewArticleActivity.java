package uk.co.advmob.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewArticleActivity extends AppCompatActivity {
    ImageView imgArticleImage;
    TextView txtTitle;
    TextView txtContent;
    Spinner spCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);

        spCategories = findViewById(R.id.spCategories);

        //Get categories
        new ApiConnection(this).getCategories(new ApiCallback() {
            @Override
            public void onSuccessResponse(JSONObject jsonObject) {
                try {
                    Type listType = new TypeToken<List<Category>>() {
                    }.getType();

                    //Convert dataObject to Category object
                    JSONArray jsonArray = jsonObject.getJSONArray("dataObject");
                    ArrayList<Category> categories = new Gson().fromJson(String.valueOf(jsonArray), listType);

                    CategoryAdapter categoryAdapter = new CategoryAdapter(NewArticleActivity.this, categories);
                    spCategories.setAdapter(categoryAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        imgArticleImage = findViewById(R.id.imgArticleImage);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);

        Button btnUploadImage = findViewById(R.id.btnUploadImage);
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1002);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_new_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.menu_save_article) {
            //Save Article
            //Get bitmap from imageview
            BitmapDrawable drawable = (BitmapDrawable) imgArticleImage.getDrawable();
            Bitmap image = drawable.getBitmap();

            //Get journalist
            Journalist journalist = new Journalist();
            journalist.setId(SingleSignOn.getUser_id());
            journalist.setEmail(SingleSignOn.getEmail());

            //Get category from spinner
            Category category = (Category) spCategories.getSelectedItem();

            Article article = new Article();
            article.setTitle(txtTitle.getText().toString());
            //Convert bitmap to string
            article.setImage(ApiConnection.getImageString(image));
            article.setContent(txtContent.getText().toString());
            article.setCategory(category);
            article.setAuthor(journalist);

            new ApiConnection(NewArticleActivity.this).createArticle(article, new ApiCallback() {
                @Override
                public void onSuccessResponse(JSONObject jsonObject) {
                    //Convert json to response object
                    Response response = new Gson().fromJson(jsonObject.toString(), Response.class);

                    if (response.isError()) {
                        Toast.makeText(NewArticleActivity.this, response.getErrorMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        finish();
                        Toast.makeText(NewArticleActivity.this, "Article created", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1002 && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                ImageView imgArticleImage = findViewById(R.id.imgArticleImage);
                imgArticleImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
