package com.zp.gossiptripe.http.gson;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import com.zp.gossiptripe.common.Response;
import com.zp.gossiptripe.http.ApiErrorCode;
import com.zp.gossiptripe.http.ApiException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;


import retrofit2.Converter;

/**
 * Created by EX_WLJR_ZHANGPENG on 2017/7/17.
 */
public class CustGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Gson mGson;
    private final TypeAdapter<T> adapter;

    public CustGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        mGson = gson;
        this.adapter = adapter;
    }
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
            Response<T> rs = mGson.fromJson(response, Response.class);
            if (TextUtils.equals(rs.getResponseCode(), ApiErrorCode.RESPONSE_CODE_0)) {
                MediaType mediaType = value.contentType();
                Charset charset = mediaType != null ? mediaType.charset(UTF_8) : UTF_8;
                ByteArrayInputStream bis = new ByteArrayInputStream(response.getBytes());
                InputStreamReader reader = new InputStreamReader(bis, charset);
                JsonReader jsonReader = mGson.newJsonReader(reader);
                value.close();
                return adapter.read(jsonReader);
            } else {
                value.close();
                throw new ApiException(rs.getResponseCode(), rs.getResponseMessage());
            }

    }
}
