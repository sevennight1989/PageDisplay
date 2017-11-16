package com.zp.gossiptripe.main.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.product.AddProductActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ZhangPeng on 10-9-0009.
 */
public abstract class BaseListAdapter extends RecyclerView.Adapter<BaseListAdapter.MyViewHolder> {

    private List<GalleryBean> mList;
    private Context mContext;

    public BaseListAdapter(Context context, List<GalleryBean> list) {
        mList = list;
        mContext = context;
    }

    public abstract MyViewHolder getViewHolder(ViewGroup parent);

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent);
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(mContext).load(mList.get(0).getHeadPicSrc())
        .placeholder(R.mipmap.touxiang).into(holder.headPic);
        Picasso.with(mContext).load(mList.get(0).getProductPreviewSrc())
        .placeholder(R.mipmap.zuoping).into(holder.product_preview);
        holder.location.setText(mList.get(0).getLocation());
        holder.name.setText(mList.get(0).getName());
        String age = mContext.getResources().getString(R.string.createAge);
        age = String.format(age, mList.get(0).getAge());
        holder.age.setText(age);
        holder.organization.setText(mList.get(0).getOrganization());
        holder.date.setText(mList.get(0).getData());
        holder.number.setText(mContext.getResources().getString(R.string.number, mList.get(0).getNumber()));
        holder.product_name.setText(mList.get(0).getProductName());
        holder.product_content.setText(mList.get(0).getProductContent());
        holder.product_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView headPic;
        TextView name;
        TextView age;
        TextView location;
        TextView organization;
        TextView date;
        ImageView product_preview;
        TextView product_name;
        TextView product_content;
        TextView number;
        ImageView share;
        ImageView praise;

        public MyViewHolder(View itemView) {
            super(itemView);
            headPic = (CircleImageView) itemView.findViewById(R.id.headpic);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
            location = (TextView) itemView.findViewById(R.id.location);
            organization = (TextView) itemView.findViewById(R.id.organization);
            date = (TextView) itemView.findViewById(R.id.date);
            product_preview = (ImageView) itemView.findViewById(R.id.productpreview);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            product_content = (TextView) itemView.findViewById(R.id.product_content);
            number = (TextView) itemView.findViewById(R.id.number);
            share = (ImageView) itemView.findViewById(R.id.share);
            praise = (ImageView) itemView.findViewById(R.id.praise);
        }
    }
}
