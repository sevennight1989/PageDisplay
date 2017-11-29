package com.zp.gossiptripe.http;

import com.zp.gossiptripe.common.Response;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Percy on 11-29 0029.
 */

public interface CommonService {

    @FormUrlEncoded
    @POST("common/regist.do")
    Observable<Response<String>> regist(@FieldMap Map<String, String> map);
}
