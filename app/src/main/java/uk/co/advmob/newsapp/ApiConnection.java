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
                        progressDialog.dismiss();
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error message
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Report fake article
    public void reportFake(int article_id, int user_id, final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        //Create JSON Object
        JSONObject request = new JSONObject();
        try {
            request.put("user_id", user_id);
            request.put("article_id", article_id);
        } catch (Exception e) {

        }

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, "http://newsapi.dkode.co.uk/fake_articles", request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error message
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Like an article
    public void likeArticle(int article_id, int user_id, final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        //Create JSON Object
        JSONObject request = new JSONObject();
        try {
            request.put("user_id", user_id);
            request.put("article_id", article_id);
        } catch (Exception e) {

        }

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, "http://newsapi.dkode.co.uk/like_articles", request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error message
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Read article later save
    public void readLater(int article_id, int user_id, final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        //Create JSON Object
        JSONObject request = new JSONObject();
        try {
            request.put("user_id", user_id);
            request.put("article_id", article_id);
        } catch (Exception e) {

        }

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, "http://newsapi.dkode.co.uk/read_later", request,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error message
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
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
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        progressDialog.dismiss();
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                //Error message
                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Get articles
    public void getReadLaterArticles(int user_id, final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, "http://newsapi.dkode.co.uk/read_later/" + String.valueOf(user_id), null,
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
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                //Error message
                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Get random quote of the day
    public void getQuote(final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, "http://newsapi.dkode.co.uk/quotes", null,
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
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                //Error message
                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Get categories
    public void getCategories(final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, "http://newsapi.dkode.co.uk/categories", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        apiCallback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error message
                progressDialog.dismiss();
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        });

        mRequestQueue.add(mJsonObjectRequest);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    //Update User Info
    public void update_user(String email, String password, String newEmail, String newPassword, String newFullName, final ApiCallback apiCallback) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        //Create JSON Object
        JSONObject request = new JSONObject();
        try {
            request.put("email", email);
            request.put("password", password);
            request.put("newEmail", newEmail);
            request.put("newPassword", newPassword);
            request.put("newFullName", newFullName);


        } catch (Exception e) {
            Log.e("newsapp", "Error during update_user" + e.getMessage());
        }

        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT, "http://newsapi.dkode.co.uk/update_user", request,
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

    public static String getImageString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] bt = stream.toByteArray();
        String strImage = Base64.encodeToString(bt, Base64.DEFAULT);

        return strImage;
    }
}
