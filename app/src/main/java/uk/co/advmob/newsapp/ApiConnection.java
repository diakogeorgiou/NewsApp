package uk.co.advmob.newsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kostas on 23/02/2018.
 */

public class ApiConnection {
    ProgressDialog progressDialog;

    Context context;

    public ApiConnection(Context context) {
        this.context = context;
    }

    //Login
    public void login(String email, String password, final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        //Create JSON Object
        JSONObject request = new JSONObject();
        try {
            request.put("email", email);
            request.put("password", password);
        } catch (Exception e) {

        }

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, "http://newsapi.dkode.co.uk/login", request,
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

    //Register
    public void register(String email, String password, String fullName, String userType, final Bitmap profilePicture, final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        //Create JSON Object
        JSONObject request = new JSONObject();
        try {
            request.put("email", email);
            request.put("password", password);
            request.put("fullName", fullName);
            request.put("userType", userType);
            request.put("profilePicture", getImageString(profilePicture));
        } catch (Exception e) {

        }

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, "http://newsapi.dkode.co.uk/register", request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                //Error message
                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Create article
    public void createArticle(Article article, final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

//        //Create JSON Object
//        JSONObject request = new JSONObject();
//        try {
//            request.put("title", title);
//            request.put("image", getImageString(image));
//            request.put("content", content);
//            request.put("author", author);
//            request.put("category", new Gson().toJson(category).toString());
//        } catch (Exception e) {
//
//        }

        JSONObject request = null;
        try {
            request = new JSONObject(new Gson().toJson(article));
        } catch (Exception e) {

        }


        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, "http://newsapi.dkode.co.uk/articles", request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                //Error message
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Get articles
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

//    public void uploadUserImage(final Bitmap bitmap) {
//        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://newsapi.dkode.co.uk/upload", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.i("Myresponse", "" + response);
//                Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("Mysmart", "" + error);
//                Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = new HashMap<>();
//
//                String images = getImageString(bitmap);
//                Log.i("Mynewsam", "" + images);
//                param.put("image", images);
//                return param;
//            }
//        };
//
//        mRequestQueue.add(stringRequest);
//    }

    public static String getImageString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] bt = stream.toByteArray();
        String strImage = Base64.encodeToString(bt, Base64.DEFAULT);

        return strImage;
    }
}
