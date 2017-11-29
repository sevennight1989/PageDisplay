package com.zp.gossiptripe.main.gallery.latest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.zp.gossiptripe.R;
import com.zp.gossiptripe.main.gallery.BaseListAdapter;
import com.zp.gossiptripe.main.gallery.GalleryBean;
import com.zp.gossiptripe.main.gallery.GalleryNetRequest;
import com.zp.gossiptripe.main.gallery.IViewHandle;
import com.zp.gossiptripe.test.GalleryData;
import com.zp.gossiptripe.viewutils.RecycleViewDivider;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestUpdateFragment extends Fragment implements IViewHandle {
    View root;
    RecyclerView mLastUpdateList;
    SwipeRefreshLayout mSwipe;
    LatestUpdateListAdapter mAdapter;
    Retrofit retrofit;
    GalleryNetRequest mRequest;
    private static final String BASE_URL = "http://192.168.31.155/";

    public static LatestUpdateFragment newInstance() {
        return new LatestUpdateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_latest_update, container, false);
        initViews();
        return root;
    }

    private void initViews() {
        mLastUpdateList = (RecyclerView) root.findViewById(R.id.lastupdateList);
        initRequest();
//        if (isOnLine) {
//            doRequest();
//        } else {
            loadOfflineData();
//        }

    }

    private void initRequest() {
        retrofit = new Retrofit.Builder()
        .baseUrl("http://192.168.31.155/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
        mRequest = retrofit.create(GalleryNetRequest.class);
    }

    private void doRequest() {
        mRequest.getLastUpdateInfo()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<GalleryBean>>() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("Throwable " + e.toString());
            }

            @Override
            public void onNext(List<GalleryBean> list) {
                setViewData(list);
            }
        });
    }

    private void loadOfflineData() {
        setViewData(GalleryData.getGalleryData());
    }

    @Override
    public void setViewData(List<GalleryBean> list) {
        mAdapter = new LatestUpdateListAdapter(list);
        mLastUpdateList.setLayoutManager(new LinearLayoutManager(getContext()));
        mLastUpdateList.addItemDecoration(new RecycleViewDivider(getContext(),
        LinearLayoutManager.VERTICAL, 30, getResources().getColor(android.R.color.white)));
        mLastUpdateList.setAdapter(mAdapter);
        mSwipe = (SwipeRefreshLayout) root.findViewById(R.id.swipe);
    }

    class LatestUpdateListAdapter extends BaseListAdapter {

        public LatestUpdateListAdapter(List<GalleryBean> list) {
            super(getContext(), list);
        }

        @Override
        public MyViewHolder getViewHolder(ViewGroup parent) {
            View rootView = LayoutInflater.from(getContext()).inflate(R.layout
            .fragment_latestupdate_item, parent, false);
            return new MyViewHolder(rootView);
        }

    }

}
