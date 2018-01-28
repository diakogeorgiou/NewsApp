package uk.co.advmob.newsapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView txtQuote = findViewById(R.id.txtQuote);
        TextView txtAuthor = findViewById(R.id.txtAuthor);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Silent Reaction.ttf");
        txtQuote.setTypeface(typeface);
        txtAuthor.setTypeface(typeface);

        txtQuote.setText("But man is not made for defeat. A man can be destroyed but not defeated.");
        txtAuthor.setText("Ernest Hemingway");

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
