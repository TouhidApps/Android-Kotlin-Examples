package com.touhidapps.recyclerviewandvolleyrequests;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewGridActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "RV_GridActivity";
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    String baseUrl = "http://touhidapps.com/api/demo/jsondemoapi.php";

    DemoDataModel demoDataModel;
    List<DemoDataModel> demoDataModels = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    MyRecyclerAdapterGrid myRecyclerAdapterGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_grid);
        setTitle("RecyclerView (Grid)");

        initUI();

    }

    private void initUI() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadDemoJsonArrayData();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        int numberOfColumns = 2;
        gridLayoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);
        myRecyclerAdapterGrid = new MyRecyclerAdapterGrid(this, demoDataModels);
        recyclerView.setAdapter(myRecyclerAdapterGrid);
        myRecyclerAdapterGrid.setClickListener(new MyRecyclerAdapterGrid.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tv = (TextView) view.findViewById(R.id.textViewTitle);
                Toast.makeText(RecyclerViewGridActivity.this, "" + tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public void onRefresh() {
        loadDemoJsonArrayData();
    }


    private void loadDemoJsonArrayData() {
        swipeRefreshLayout.setRefreshing(true);
        demoDataModels.clear();
        myRecyclerAdapterGrid.notifyDataSetChanged();
        // if post request then send parameters using hash map, here null because of GET request
//        HashMap<String, String> params = new HashMap<>();
//        params.put("option", "3");
        new MyNetworkRequest().makeJsonArrayRequest(this, Request.Method.GET, baseUrl + "?option=3", null, new NetworkRequestInterfaces.volleyJsonArrayRequest() {
                    @Override
                    public String jsonArrayResponse(JSONArray jsonArray) {
                        swipeRefreshLayout.setRefreshing(false);
                        Log.d(TAG, "jsonObjectResponse: " + jsonArray);
                        try {
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
                                recyclerView.setAdapter(myRecyclerAdapterGrid);
                                myRecyclerAdapterGrid.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            swipeRefreshLayout.setRefreshing(false);
                            e.printStackTrace();
                        }
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
