package com.omagrahari.demonobroker;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.omagrahari.demonobroker.Utilities.Constants.filter;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mAp2, mAp3, mAp4, mPtAp, mPtIf, mPtIh, mFf, mSf;
    private Button mApplyFilter;

    private ImageView mRefresh, mClose;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setViews();
        setFilter();
    }

    /**
     * Initialize Filter and show filtered view if already applied
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setFilter() {
        try {
            if (filter.size() == 0) {
                filter.put("2BHK", 0);
                filter.put("3BHK", 0);
                filter.put("4BHK", 0);
                filter.put("AP", 0);
                filter.put("IH", 0);
                filter.put("IF", 0);
                filter.put("FF", 0);
                filter.put("SF", 0);
            } else {
                if (filter.get("2BHK") == 1) {
                    setSelected(mAp2);
                }
                if (filter.get("3BHK") == 1) {
                    setSelected(mAp3);
                }
                if (filter.get("4BHK") == 1) {
                    setSelected(mAp4);
                }
                if (filter.get("AP") == 1) {
                    setSelected(mPtAp);
                }
                if (filter.get("IF") == 1) {
                    setSelected(mPtIf);
                }
                if (filter.get("IH") == 1) {
                    setSelected(mPtIh);
                }
                if (filter.get("FF") == 1) {
                    setSelected(mFf);
                }
                if (filter.get("SF") == 1) {
                    setSelected(mSf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize Views
     */
    private void setViews() {
        try {
            mRefresh = findViewById(R.id.refresh);
            mClose = findViewById(R.id.close);
            mApplyFilter = findViewById(R.id.apply_filter);
            mAp2 = findViewById(R.id.ap_type_2);
            mAp3 = findViewById(R.id.ap_type_3);
            mAp4 = findViewById(R.id.ap_type_4);
            mPtAp = findViewById(R.id.pt_ap);
            mPtIf = findViewById(R.id.pt_if);
            mPtIh = findViewById(R.id.pt_ih);
            mFf = findViewById(R.id.f_ff);
            mSf = findViewById(R.id.f_sf);

            mRefresh.setOnClickListener(this);
            mClose.setOnClickListener(this);
            mApplyFilter.setOnClickListener(this);
            mAp2.setOnClickListener(this);
            mAp3.setOnClickListener(this);
            mAp4.setOnClickListener(this);
            mPtAp.setOnClickListener(this);
            mPtIf.setOnClickListener(this);
            mPtIh.setOnClickListener(this);
            mFf.setOnClickListener(this);
            mSf.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        try {
            int id = v.getId();
            switch (id) {
                case R.id.ap_type_2:
                    if (filter.get("2BHK") == 0) {
                        filter.put("2BHK", 1);
                        setSelected(v);
                    } else {
                        filter.put("2BHK", 0);
                        setUnSelected(v);
                    }
                    break;
                case R.id.ap_type_3:
                    if (filter.get("3BHK") == 0) {
                        filter.put("3BHK", 1);
                        setSelected(v);
                    } else {
                        filter.put("3BHK", 0);
                        setUnSelected(v);
                    }
                    break;
                case R.id.ap_type_4:
                    if (filter.get("4BHK") == 0) {
                        filter.put("4BHK", 1);
                        setSelected(v);
                    } else {
                        filter.put("4BHK", 0);
                        setUnSelected(v);
                    }
                    break;
                case R.id.pt_ap:
                    if (filter.get("AP") == 0) {
                        filter.put("AP", 1);
                        setSelected(v);
                    } else {
                        filter.put("AP", 0);
                        setUnSelected(v);
                    }
                    break;
                case R.id.pt_if:
                    if (filter.get("IF") == 0) {
                        filter.put("IF", 1);
                        setSelected(v);
                    } else {
                        filter.put("IF", 0);
                        setUnSelected(v);
                    }
                    break;
                case R.id.pt_ih:
                    if (filter.get("IH") == 0) {
                        filter.put("IH", 1);
                        setSelected(v);
                    } else {
                        filter.put("IH", 0);
                        setUnSelected(v);
                    }
                    break;
                case R.id.f_ff:
                    if (filter.get("FF") == 0) {
                        filter.put("FF", 1);
                        setSelected(v);
                    } else {
                        filter.put("FF", 0);
                        setUnSelected(v);
                    }
                    break;
                case R.id.f_sf:
                    if (filter.get("SF") == 0) {
                        filter.put("SF", 1);
                        setSelected(v);
                    } else {
                        filter.put("SF", 0);
                        setUnSelected(v);
                    }
                    break;
                case R.id.apply_filter:
                    Intent returnIntent = new Intent();
                    if (filter.get("SF") == 1 || filter.get("FF") == 1 || filter.get("IH") == 1 || filter.get("IF") == 1 || filter.get("AP") == 1 || filter.get("2BHK") == 1 || filter.get("3BHK") == 1 || filter.get("4BHK") == 1) {
                        returnIntent.putExtra("result", "1");
                    } else {
                        returnIntent.putExtra("result", "0");
                    }
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                    break;
                case R.id.close:
                    finish();
                    break;
                case R.id.refresh:
                    filter.clear();
                    Intent returnIntent2 = new Intent();
                    returnIntent2.putExtra("result", "2");
                    setResult(Activity.RESULT_OK, returnIntent2);
                    finish();
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Change filter view to unselected
     * @param v
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setUnSelected(View v) {
        try {
            v.setBackground(ContextCompat.getDrawable(this, R.drawable.filter_unselected_back));
            ((TextView) v).setTextColor(ContextCompat.getColor(this, R.color.text_color));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Select filter
     * @param v
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setSelected(View v) {
        try {
            v.setBackground(ContextCompat.getDrawable(this, R.drawable.filter_selected));
            ((TextView) v).setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
