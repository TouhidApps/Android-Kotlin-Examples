package com.touhidapps.recyclerviewandvolleyrequests;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Touhid on 7/26/2017.
 */

public class NetworkRequestInterfaces {

    interface volleyStringRequest {
        String stringResponse(String response);
        VolleyError stringError(VolleyError error);
    }

    interface volleyJsonObjectRequest {
        String jsonObjectResponse(JSONObject jsonObject);
        VolleyError jsonObjectError(VolleyError volleyError);
    }

    interface volleyJsonArrayRequest {
        String jsonArrayResponse(JSONArray jsonArray);
        VolleyError jsonArrayError(VolleyError volleyError);
    }

}
