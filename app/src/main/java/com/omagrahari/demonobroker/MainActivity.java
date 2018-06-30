package com.omagrahari.demonobroker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.omagrahari.demonobroker.Adapter.PropertyListAdapter;
import com.omagrahari.demonobroker.Model.PropertyListBean;
import com.omagrahari.demonobroker.Network.AsyncHttpRequest;
import com.omagrahari.demonobroker.Network.RetrofitService;
import com.omagrahari.demonobroker.Utilities.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.omagrahari.demonobroker.Utilities.Constants.filter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String lat_lng = "12.9279232,77.627107";
    public String rent = "0,500000";
    public String travelTime = "30";
    public int pageNo = 1;
    public int applyFilter = 0;
    public LinearLayoutManager layoutManager;
    public ArrayList<PropertyListBean.Data> propertyList = new ArrayList<>();
    private int visibleThreshold = 3;
    private boolean isLoading;
    private int lastVisibleItem, totalItemCount;

    Utils utils;
    public RecyclerView mPListView;
    public PropertyListAdapter mPlistAdapter;
    public FloatingActionButton mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Properties Near Me");

        //Show Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setViews();
        setVariables();

        getPropertyList(pageNo, applyFilter);
    }

    /**
     * Initialize Variables
     */
    private void setVariables() {
        mPlistAdapter = new PropertyListAdapter(this);
    }

    /**
     * Initialize Views and set up listeners
     */
    private void setViews() {
        try {
            mFilter = findViewById(R.id.filter);
            mPListView = findViewById(R.id.property_list_view);
            layoutManager = new LinearLayoutManager(this);
            mPListView.setLayoutManager(layoutManager);

            mPListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = layoutManager.getItemCount();
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        pageNo = pageNo + 1;
                        getPropertyList(pageNo, applyFilter);
                        isLoading = true;
                    }
                }
            });

            mFilter.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Property Listing
     * @param pageNo
     * @param applyFilter
     */
    private void getPropertyList(int pageNo, int applyFilter) {
        try {
            utils = new Utils();
            utils.showDialog(this);
            Retrofit retrofit = AsyncHttpRequest.newRetrofitRequest(this);

            RetrofitService retrofitService = retrofit.create(RetrofitService.class);

            retrofitService.getPropertyList(lat_lng, rent, travelTime, pageNo).enqueue(new getPropertListHandler(applyFilter));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            int id = v.getId();

            switch (id) {
                case R.id.filter:
                    Intent intent = new Intent(this, FilterActivity.class);
                    startActivityForResult(intent, 001);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Property Listing Callback show listing
     */
    public class getPropertListHandler implements Callback<PropertyListBean> {
        int applyFilter;

        public getPropertListHandler(int applyFilter) {
            this.applyFilter = applyFilter;
        }

        @Override
        public void onResponse(Call<PropertyListBean> call, Response<PropertyListBean> response) {
            isLoading = false;
            utils.closeDialog();
            if (response.code() == 200) {
                PropertyListBean propertyListBean = response.body();
                if (applyFilter == 0) {
                    for (int i = 0; i < propertyListBean.getData().size(); i++) {
                        propertyList.add(propertyListBean.getData().get(i));
                    }
                    //   propertyList.addAll(propertyListBean.getData());
                    mPlistAdapter.setArraylist(propertyList);
                    if (pageNo == 1) {
                        mPListView.setAdapter(mPlistAdapter);
                    }
                    mPlistAdapter.notifyDataSetChanged();
                } else {
                    showFilteredList(propertyListBean.getData());
                }
            }
        }

        @Override
        public void onFailure(Call<PropertyListBean> call, Throwable t) {

        }
    }

    /**
     * Show listing when filter is applied
     * @param data
     */
    private void showFilteredList(ArrayList<PropertyListBean.Data> data) {
        try {
            for (int i = 0; i < data.size(); i++) {
                int flag = 0;
                if ((filter.get("2BHK") == 1 && data.get(i).getType().equalsIgnoreCase("BHK2"))
                        || (filter.get("3BHK") == 1 && data.get(i).getType().equalsIgnoreCase("BHK3"))
                        || (filter.get("4BHK") == 1 && data.get(i).getType().equalsIgnoreCase("BHK4"))
                        || (filter.get("AP") == 1 && data.get(i).getBuildingType().equalsIgnoreCase("AP"))
                        || (filter.get("IF") == 1 && data.get(i).getBuildingType().equalsIgnoreCase("IF"))
                        || (filter.get("IH") == 1 && data.get(i).getBuildingType().equalsIgnoreCase("IH"))
                        || (filter.get("FF") == 1 && data.get(i).getFurnishing().equalsIgnoreCase("FULLY_FURNISHED"))
                        || (filter.get("SF") == 1 && data.get(i).getFurnishing().equalsIgnoreCase("SEMI_FURNISHED"))) {
                    flag = 1;
                }
                if (flag == 1) {
                    propertyList.add(data.get(i));
                }
            }
            mPlistAdapter.setArraylist(propertyList);
            if (pageNo == 1) {
                mPListView.setAdapter(mPlistAdapter);
            }
            mPlistAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Filter Activity Result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 001:
                if (resultCode == Activity.RESULT_OK) {
                    propertyList = new ArrayList<>();
                    String result = data.getStringExtra("result");
                    if (result.equalsIgnoreCase("1")) {
                        applyFilter = 1;
                        pageNo = 1;
                        getPropertyList(pageNo, applyFilter);
                    } else if (result.equalsIgnoreCase("2")) {
                        pageNo = 1;
                        applyFilter = 0;
                        getPropertyList(pageNo, applyFilter);
                    }
                }
                break;
        }
    }

}
