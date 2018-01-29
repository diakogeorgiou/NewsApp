package uk.co.advmob.newsapp;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ArticlesActivity extends AppCompatActivity implements TrendingFragment.OnFragmentInteractionListener, CategoriesFragment.OnFragmentInteractionListener {

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
