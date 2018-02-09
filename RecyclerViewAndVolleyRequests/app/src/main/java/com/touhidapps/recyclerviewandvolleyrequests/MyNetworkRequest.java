package com.touhidapps.recyclerviewandvolleyrequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Touhid on 7/26/2017.
 */

public class MyNetworkRequest {

    private static final String TAG = "MyNetworkRequest";

    public void makeStringRequest(Context mContext, int method, String mUrl, final HashMap<String, String> params, final NetworkRequestInterfaces.volleyStringRequest volleyStringRequest) {
        Log.d(TAG, "makeStringRequest: " + mUrl);
        final StringRequest stringRequest = new StringRequest(method, mUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyStringRequest.stringResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        volleyStringRequest.stringError(error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        Volley.newRequestQueue(mContext).add(stringRequest);
    }

    public void makeJsonObjectRequest(Context mContext, int method, String mUrl, final HashMap<String, String> params, final NetworkRequestInterfaces.volleyJsonObjectRequest jsonObjectRequest) {
        Log.d(TAG, "makeJsonObjectRequest: " + mUrl);
        JsonObjectRequest request = new JsonObjectRequest(method, mUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                jsonObjectRequest.jsonObjectResponse(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                jsonObjectRequest.jsonObjectError(volleyError);
                volleyError.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        Volley.newRequestQueue(mContext).add(request);
    }

    public void makeJsonArrayRequest(Context mContext, int method, String mUrl, final HashMap<String, String> params, final NetworkRequestInterfaces.volleyJsonArrayRequest volleyJsonArrayRequest) {
        Log.d(TAG, "makeJsonArrayRequest: " + mUrl);
        JsonArrayRequest request = new JsonArrayRequest(method, mUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                volleyJsonArrayRequest.jsonArrayResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyJsonArrayRequest.jsonArrayError(volleyError);
                volleyError.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        Volley.newRequestQueue(mContext).add(request);
    }

}
