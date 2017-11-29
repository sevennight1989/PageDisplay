package com.zp.gossiptripe.http;

import com.blankj.utilcode.util.NetworkUtils;

import rx.Subscriber;

/**
 * Created by Percy on 11-29 0029.
 */

public class BaseSubscriber<T> extends Subscriber<T> {



    @Override
    public void onStart() {
        if (!NetworkUtils.isConnected()) {
            this.onError(new ApiException(ApiErrorCode.ERROR_NO_INTERNET, "network interrupt"));
        }
    }

    @Override
    public void onCompleted(){

    }

    @Override
    public void onError(Throwable e) {
        ApiErrorHelper.handleCommonError( e);
    }

    @Override
    public void onNext(T t) {

    }
}
