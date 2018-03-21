package uk.co.advmob.newsapp;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity implements TrendingFragment.OnFragmentInteractionListener,
        CategoriesFragment.OnFragmentInteractionListener, ReadLaterFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_trending:
                    trendingFragment();
                    return true;
                case R.id.navigation_categories:
                    categoriesFragment();
                    return true;
                case R.id.navigation_read_later:
                    readLaterFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.articles_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_user_profile) {
            Intent profileActivity = new Intent(ArticlesActivity.this, ProfileActivity.class);
            startActivity(profileActivity);
            return true;
        }

        if (id == R.id.mn_search) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Article Search");

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            builder.setView(input);

            builder.setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    new ApiConnection(ArticlesActivity.this).searchArticles(input.getText().toString(), new ApiCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject jsonObject) {
                            try {
                                if (!jsonObject.getBoolean("error")) {
                                    Intent searchResults = new Intent(ArticlesActivity.this, SearchArticleActivity.class);
                                    searchResults.putExtra("json", jsonObject.toString());
                                    startActivity(searchResults);
                                } else {
                                    Toast.makeText(ArticlesActivity.this, jsonObject.getString("errorMessage"), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } catch (Exception e) {

                            }
                        }
                    });
                }
            });
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        trendingFragment();
    }

    private void trendingFragment() {
        Fragment trendingFragment = new TrendingFragment();
        getFragmentManager().beginTransaction().replace(
                R.id.fragment, trendingFragment)
                .commit();
    }

    private void categoriesFragment() {
        Fragment categoriesFragment = new CategoriesFragment();
        getFragmentManager().beginTransaction().replace(
                R.id.fragment, categoriesFragment)
                .commit();
    }

    private void readLaterFragment() {
        Fragment readLaterFragment = new ReadLaterFragment();
        getFragmentManager().beginTransaction().replace(
                R.id.fragment, readLaterFragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
