package uk.co.advmob.newsapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Kostas on 23/02/2018.
 */

public class ApiConnection {
    Context context;

    public ApiConnection(Context context) {
        this.context = context;
    }

    public void getArticles(final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, "http://newsapi.dkode.co.uk/articles", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error message
                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

    }
}
