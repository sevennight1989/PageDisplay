package com.zp.gossiptripe.http;

import com.zp.gossiptripe.common.Response;

import java.util.Map;

import rx.Observable;


/**
 * Created by Percy on 11-29 0029.
 */

public class CommonServiceManager {

    CommonService mCommonService;
    public CommonServiceManager() {
        mCommonService = RetrofitClient.getInstance().getService(CommonService.class);
    }

    public Observable<Response<String>> getRegistParam(Map<String,String> map){
        return mCommonService.regist(map).compose(new BaseSchedulerTransformer<Response<String>>());
    }
}
