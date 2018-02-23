package uk.co.advmob.newsapp;

import org.json.JSONObject;

/**
 * Created by Kostas on 23/02/2018.
 */

public interface ApiCallback {
    void onSuccessResponse(JSONObject jsonObject);
}
