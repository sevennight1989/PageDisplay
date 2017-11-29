package com.zp.gossiptripe.http;

import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;
import com.zp.gossiptripe.BuildConfig;
import com.zp.gossiptripe.MainApplication;
import com.zp.gossiptripe.http.gson.CustGsonConverterFactory;
import com.zp.gossiptripe.utils.PengLogger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Percy on 11-29 0029.
 */

public class RetrofitClient {
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    private static final long CACHE_AGE_SEC = 0;
    private String URL = " http://192.168.1.166:8080/PDServer/";

    //需要过滤到的接口
    private static String filters[] = {};
    private static volatile OkHttpClient sOkHttpClient;
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (NetworkUtils.isConnected()) {
                request = request.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, max-age=" + CACHE_AGE_SEC).build();
                Response response = chain.proceed(request);
                return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control", "public, max-age=" + CACHE_AGE_SEC).build();
            } else {
                request = request.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control")
                .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC).build();
                Response response = chain.proceed(request);
                return response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control")
                .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC).build();
            }
        }
    };

    //打印返回的json数据拦截器
    private static Interceptor mLoggingInterceptor = new Interceptor() {
        @Override public okhttp3.Response intercept(Chain chain) throws IOException {
            //默认不过滤请求接口
            boolean filter = false;
            okhttp3.Request request = chain.request();
            okhttp3.Request.Builder requestBuilder = request.newBuilder();
            requestBuilder.addHeader("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
            request = requestBuilder.build();
            String requestUrl =  request.url().toString();

            for (String s : filters) {
                if (requestUrl.contains(s)) {
                    filter = true;
                    break;
                }
            }
            if (filter) {
                PengLogger.v("请求网址: " + requestUrl);
            } else {
                PengLogger.d("请求网址: " + requestUrl);
            }
            if (BuildConfig.DEBUG) {
                String method = request.method();
                if ("POST".equals(method)) {
                    if (filter) {
                        PengLogger.v("------------开始打印请求参数------------");
                    } else {
                        PengLogger.d("------------开始打印请求参数------------");
                    }
                    StringBuilder sb = new StringBuilder();
                    if (request.body() instanceof FormBody) {
                        FormBody body = (FormBody) request.body();
                        for (int i = 0; i < body.size(); i++) {
                            sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                            if (filter) {
                                PengLogger.v(body.encodedName(i) + " = " + body.encodedValue(i));
                            } else {
                                PengLogger.d(body.encodedName(i) + " = " + body.encodedValue(i));
                            }
                        }
                        if (filter) {
                            PengLogger.v("------------结束打印请求参数------------");
                        } else {
                            PengLogger.d("------------结束打印请求参数------------");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                    }
                }
            }

            final okhttp3.Response response = chain.proceed(request);

            final ResponseBody responseBody = response.body();
            final long contentLength = responseBody.contentLength();

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    PengLogger.d("");
                    PengLogger.d("Couldn't decode the response body; charset is likely malformed.");
                    return response;
                }
            }

            if (contentLength != 0) {
                if (filter) {
                    PengLogger.v("------------开始打印返回数据------------");
                    PengLogger.v(buffer.clone().readString(charset));
                    PengLogger.v("------------结束打印返回数据------------");
                } else {
                    PengLogger.d("------------开始打印返回数据------------");
                    PengLogger.d(buffer.clone().readString(charset));
                    PengLogger.d("------------结束打印返回数据------------");
                }
            }
            return response;
        }
    };

    // 配置OkHttpClient
    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitClient.class) {
                if (sOkHttpClient == null) {
                    // OkHttpClient配置是一样的,静态创建一次即可
                    // 指定缓存路径,缓存大小100Mb
                    try {
                        Cache cache = new Cache(new File(MainApplication.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                        OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        builder.addInterceptor(mLoggingInterceptor);
                        builder.addNetworkInterceptor(mRewriteCacheControlInterceptor);
                        builder.readTimeout(20, TimeUnit.SECONDS);
                        builder.connectTimeout(15, TimeUnit.SECONDS);
                        builder.cache(cache);
                        sOkHttpClient = builder.build();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return sOkHttpClient;
    }

    private static RetrofitClient instance = null;

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
            return instance;
        } else {
            return instance;
        }
    }

    private Retrofit retrofit;
    private RetrofitClient() {
        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(CustGsonConverterFactory.create(new Gson()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(getOkHttpClient()).build();
    }

    public <E> E getService(Class<E> e){
        return retrofit.create(e);
    }

}
