package uk.co.advmob.newsapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final TextView txtQuote = findViewById(R.id.txtQuote);
        final TextView txtAuthor = findViewById(R.id.txtAuthor);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Silent Reaction.ttf");
        txtQuote.setTypeface(typeface);
        txtAuthor.setTypeface(typeface);

        //Get quote
        new ApiConnection(WelcomeActivity.this).getQuote(new ApiCallback() {
            @Override
            public void onSuccessResponse(JSONObject jsonObject) {
                try {
                    JSONObject jsonQuote = jsonObject.getJSONObject("dataObject");

                    txtQuote.setText(jsonQuote.getString("quote"));
                    txtAuthor.setText(jsonQuote.getString("author"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //Continue
        Button btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent articlesActivity = new Intent(WelcomeActivity.this, ArticlesActivity.class);
                startActivity(articlesActivity);
            }
        });
    }
}
