package com.zp.gossiptripe.main.gallery.choice;


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


public class ChoiceFragment extends Fragment implements IViewHandle {

    View root;
    RecyclerView mChoiceList;
    SwipeRefreshLayout mSwipe;
    ChoiceListAdapter mAdapter;
    Retrofit retrofit;
    GalleryNetRequest mRequest;

    public ChoiceFragment() {
    }

    public static ChoiceFragment newInstance() {
        return new ChoiceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_choice, container, false);
        initViews();
        return root;
    }

    private void initViews() {
        mChoiceList = (RecyclerView) root.findViewById(R.id.choiceList);
        initRequest();
        boolean isOnLine = getResources().getBoolean(R.bool.onlineMode);
        if (isOnLine) {
            doRequest();
        } else {
            loadOfflineData();
        }
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
        mAdapter = new ChoiceListAdapter(list);
        mChoiceList.setLayoutManager(new LinearLayoutManager(getContext()));
        mChoiceList.addItemDecoration(new RecycleViewDivider(getContext(),
        LinearLayoutManager.VERTICAL, 30, getResources().getColor(android.R.color.white)));
        mChoiceList.setAdapter(mAdapter);
        mSwipe = (SwipeRefreshLayout) root.findViewById(R.id.swipe);
    }


    class ChoiceListAdapter extends BaseListAdapter {

        public ChoiceListAdapter(List<GalleryBean> list) {
            super(getContext(), list);
        }

        @Override
        public MyViewHolder getViewHolder(ViewGroup parent) {
            View rootView = LayoutInflater.from(getContext()).inflate(R.layout
            .fragment_choice_item, parent, false);
            return new MyViewHolder(rootView);
        }

    }

}
