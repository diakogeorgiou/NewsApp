package uk.co.advmob.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrendingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TrendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrendingFragment.
     */
    // TODO: Rename and change types and number of parameters

    private ListView lvArticles;

    public static TrendingFragment newInstance(String param1, String param2) {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void getArticles(){
        ArrayList<Article> articles = new ArrayList<>();

        Article article = new Article();
        article.setTitle("Title 1");
        article.setDate("01/01/2017");
        article.setAuthor("Kostas");
        articles.add(article);

        article = new Article();
        article.setTitle("Title 2");
        article.setDate("01/01/2017");
        article.setAuthor("JJ");
        articles.add(article);

        article = new Article();
        article.setTitle("Title 3");
        article.setDate("01/01/2017");
        article.setAuthor("Ildiko");
        articles.add(article);

        article = new Article();
        article.setTitle("Title 4");
        article.setDate("01/01/2017");
        article.setAuthor("JJ");
        articles.add(article);

        article = new Article();
        article.setTitle("Title 5");
        article.setDate("01/01/2017");
        article.setAuthor("Kostas");
        articles.add(article);

        article = new Article();
        article.setTitle("Title 6");
        article.setDate("01/01/2017");
        article.setAuthor("Author 1");
        articles.add(article);

        article = new Article();
        article.setTitle("Title 7");
        article.setDate("01/01/2017");
        article.setAuthor("Author 2");
        articles.add(article);

        article = new Article();
        article.setTitle("Title 8");
        article.setDate("01/01/2017");
        article.setAuthor("Author 3");
        articles.add(article);

        ArticlesAdapter articlesAdapter = new ArticlesAdapter(getActivity(), articles);
        lvArticles.setAdapter(articlesAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trending, container, false);

        lvArticles = rootView.findViewById(R.id.lvArticles);

        getArticles();

        //List click
        lvArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) lvArticles.getItemAtPosition(position);

                //Show article
                Intent articleActivity = new Intent(TrendingFragment.this.getActivity(), ArticleActivity.class);
                articleActivity.putExtra("article", article);

                startActivity(articleActivity);
            }
        });

        //Floating action button
        FloatingActionButton fab = rootView.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Launch new track activity
                Intent newArticle = new Intent(TrendingFragment.this.getContext(), NewArticleActivity.class);
                startActivity(newArticle);
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
