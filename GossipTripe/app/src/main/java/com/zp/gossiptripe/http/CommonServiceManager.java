package com.zp.gossiptripe.http;

import com.zp.gossiptripe.common.Response;
import com.zp.gossiptripe.main.personal.regist.model.UserBaseInfoBean;

import java.util.Map;

import rx.Observable;


/**
 * Created by Percy on 11-29 0029.
 */

public class CommonServiceManager {

    static CommonService mCommonService;
    static CommonServiceManager commonServiceManager;

    static {
        if (mCommonService == null) {
            mCommonService = RetrofitClient.getInstance().getService(CommonService.class);
        }
    }

    public static CommonServiceManager getCommonServiceManager(){
        if(commonServiceManager == null){
            commonServiceManager = new CommonServiceManager();
        }
        return commonServiceManager;
    }

    private CommonServiceManager() {
    }

    public Observable<Response<String>> getRegistParam(Map<String,String> map){
        return mCommonService.regist(map).compose(new BaseSchedulerTransformer<Response<String>>());
    }

    public Observable<Response<UserBaseInfoBean>> getLoginParam(Map<String,String> map){
        return mCommonService.login(map).compose(new BaseSchedulerTransformer<Response<UserBaseInfoBean>>());
    }

    public Observable<Response<UserBaseInfoBean>> getLoginParamBySession(Map<String,String> map){
        return mCommonService.sessionlogin(map).compose(new BaseSchedulerTransformer<Response<UserBaseInfoBean>>());
    }
}
