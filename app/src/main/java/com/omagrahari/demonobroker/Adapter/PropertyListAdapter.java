package com.omagrahari.demonobroker.Adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omagrahari.demonobroker.Model.PropertyListBean;
import com.omagrahari.demonobroker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by omprakash on 29/06/18.
 */

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.MyViewHolder> {
    private Activity activity;
    private ArrayList mArraylist;

    public PropertyListAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setArraylist(ArrayList arraylist) {
        this.mArraylist = arraylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(activity).inflate(R.layout.my_listing_items, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PropertyListBean.Data bean = (PropertyListBean.Data) mArraylist.get(position);

        holder.mTitle.setText(bean.getTitle());
        holder.mDescription.setText(bean.getDescription());
        holder.mRent.setText(activity.getResources().getString(R.string.Rs) + " " + bean.getRent());
        holder.mPSize.setText(bean.getPropertySize() + " Sq. ft.");

        String imageUrl = "http://d3snwcirvb4r88.cloudfront.net/images/" + bean.getId() + "/";

        for (int i = 0; i < bean.getPhotos().size(); i++) {
            PropertyListBean.Data.Photos photos = bean.getPhotos().get(i);
            if (photos.isDisplayPic() == true) {
                imageUrl = imageUrl + photos.getImagesMap().getOriginal();
                break;
            }
        }

        Picasso.get().load(imageUrl).placeholder(ContextCompat.getDrawable(activity, R.drawable.default_image)).into(holder.mPImage);

        holder.mDetails.setText("" + bean.getFurnishing() + "\n" + bean.getBathroom() + " Bathrooms");

    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        private TextView mRent;
        private TextView mPSize;
        private ImageView mPImage;
        private TextView mDetails;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mDescription = itemView.findViewById(R.id.description);
            mRent = itemView.findViewById(R.id.rent);
            mPSize = itemView.findViewById(R.id.size);
            mPImage = itemView.findViewById(R.id.property_image);
            mDetails = itemView.findViewById(R.id.details);

        }
    }
}
