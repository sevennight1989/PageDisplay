package com.zp.gossiptripe.main.gallery;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ZhangPeng on 10-9-0009.
 */
public interface GalleryNetRequest {
    @GET("/repos/{owner}/{repo}/info")
    Observable<List<GalleryBean>> getChoiceInfo(@Path("owner") String owner,@Path("repo") String repo);

    @GET("Login/choiceinfo.php")
    Observable<List<GalleryBean>> getLastUpdateInfo();
}
