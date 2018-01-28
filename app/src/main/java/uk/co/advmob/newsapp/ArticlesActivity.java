package uk.co.advmob.newsapp;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class ArticlesActivity extends AppCompatActivity implements TrendingFragment.OnFragmentInteractionListener {

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
                    return true;
                case R.id.navigation_read_later:
                    return true;
            }
            return false;
        }
    };

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
        Fragment newFragment = new TrendingFragment();
        getFragmentManager().beginTransaction().replace(
                R.id.fragment, newFragment)
                .commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
