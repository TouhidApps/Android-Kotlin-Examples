package com.touhidapps.recyclerviewandvolleyrequests;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainActivity";
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    Button buttonShowGrid;
    String baseUrl = "http://touhidapps.com/api/demo/jsondemoapi.php";

    DemoDataModel demoDataModel;
    List<DemoDataModel> demoDataModels = new ArrayList<>();

    RecyclerView.LayoutManager mLayoutManager;
    MyRecyclerAdapterList myRecyclerAdapterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("RecyclerView List");

        initUI();

    }

    private void initUI() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
//                  loadDemoStringData();
                loadDemoJsonObjectData();
//                loadDemoJsonArrayData();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        buttonShowGrid = (Button) findViewById(R.id.buttonShowGrid);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        myRecyclerAdapterList = new MyRecyclerAdapterList(this, demoDataModels);
        recyclerView.setAdapter(myRecyclerAdapterList);
        myRecyclerAdapterList.setClickListener(new MyRecyclerAdapterList.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tv = (TextView) view.findViewById(R.id.textViewTitle);
                Toast.makeText(MainActivity.this, "" + tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        buttonShowGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // grid activity
                startActivity(new Intent(getApplicationContext(), RecyclerViewGridActivity.class));
            }
        });

    }

    @Override
    public void onRefresh() {
//         loadDemoStringData();
        loadDemoJsonObjectData();
//        loadDemoJsonArrayData();
    }

    private void loadDemoStringData() {
        swipeRefreshLayout.setRefreshing(true);
        new MyNetworkRequest().makeStringRequest(this, Request.Method.GET, baseUrl + "?option=1", null, new NetworkRequestInterfaces.volleyStringRequest() {
            @Override
            public String stringResponse(String response) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("ttt", "stringResponse: " + response);
                return null;
            }

            @Override
            public VolleyError stringError(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                return null;
            }
        });
    }

    private void loadDemoJsonObjectData() {
        swipeRefreshLayout.setRefreshing(true);
        demoDataModels.clear();
        myRecyclerAdapterList.notifyDataSetChanged();
        // if post request then send parameters using hash map, here null because of GET request
//        HashMap<String, String> params = new HashMap<>();
//        params.put("option", "2");
        new MyNetworkRequest().makeJsonObjectRequest(this, Request.Method.GET, baseUrl + "?option=2", null, new NetworkRequestInterfaces.volleyJsonObjectRequest() {
                    @Override
                    public String jsonObjectResponse(JSONObject jsonObject) {
                        swipeRefreshLayout.setRefreshing(false);
                        Log.d(TAG, "jsonObjectResponse: " + jsonObject);
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("MyJsonObject");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                demoDataModel = new DemoDataModel(
                                        jo.getInt("id"),
                                        jo.getString("name"),
                                        jo.getString("details"),
                                        jo.getString("fileName"),
                                        jo.getString("baseUrl")
                                );

                                demoDataModels.add(demoDataModel);
                                recyclerView.setAdapter(myRecyclerAdapterList);
                                myRecyclerAdapterList.notifyDataSetChanged();
                            }


                        } catch (JSONException e) {
                            swipeRefreshLayout.setRefreshing(false);
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    public VolleyError jsonObjectError(VolleyError volleyError) {
                        swipeRefreshLayout.setRefreshing(false);
                        Log.d(TAG, "jsonObjectError: " + volleyError);
                        return null;
                    }
                }

        );


    }

    private void loadDemoJsonArrayData() {
        swipeRefreshLayout.setRefreshing(true);
        // if post request then send parameters using hash map, here null because of GET request
        HashMap<String, String> params = new HashMap<>();
        params.put("option", "3");
        new MyNetworkRequest().makeJsonArrayRequest(this, Request.Method.GET, baseUrl + "?option=3", params, new NetworkRequestInterfaces.volleyJsonArrayRequest() {
                    @Override
                    public String jsonArrayResponse(JSONArray jsonArray) {
                        swipeRefreshLayout.setRefreshing(false);
                        Log.d(TAG, "jsonArrayResponse: " + jsonArray);
                        return null;
                    }

                    @Override
                    public VolleyError jsonArrayError(VolleyError volleyError) {
                        swipeRefreshLayout.setRefreshing(false);
                        Log.d(TAG, "jsonArrayError: " + volleyError);
                        return null;
                    }
                }
        );


    }

}
