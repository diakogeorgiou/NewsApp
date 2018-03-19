package uk.co.advmob.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReadLaterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReadLaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadLaterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ListView lvArticles;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReadLaterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReadLaterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReadLaterFragment newInstance(String param1, String param2) {
        ReadLaterFragment fragment = new ReadLaterFragment();
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

    private void getReadLaterArticles() {
        new ApiConnection(getActivity()).getReadLaterArticles(SingleSignOn.getUser_id(), new ApiCallback() {
            @Override
            public void onSuccessResponse(JSONObject jsonObject) {
                try {
                    Type listType = new TypeToken<List<Article>>() {
                    }.getType();

                    //Convert dataObject to Article object
                    JSONArray jsonArray = jsonObject.getJSONArray("dataObject");
                    ArrayList<Article> articles = new Gson().fromJson(String.valueOf(jsonArray), listType);

                    ArticlesAdapter articlesAdapter = new ArticlesAdapter(getActivity(), articles);
                    lvArticles.setAdapter(articlesAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trending, container, false);

        lvArticles = rootView.findViewById(R.id.lvArticles);

        getReadLaterArticles();

        //List click
        lvArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) lvArticles.getItemAtPosition(position);

                //Show article
                Intent articleActivity = new Intent(ReadLaterFragment.this.getActivity(), ArticleActivity.class);
                articleActivity.putExtra("article", article);

                startActivity(articleActivity);
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
