package com.zp.gossiptripe.http;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;

import java.io.IOException;
import java.util.Map;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Percy on 11-29 0029.
 */

public class ApiErrorHelper {
    public static void handleCommonError( Throwable e) {
        if (e instanceof HttpException) {
            ToastUtils.showShort("服务暂不可用");
        } else if (e instanceof IOException) {
            ToastUtils.showShort("连接失败");
        } else if (e instanceof ApiException) {

            ApiException apiException = (ApiException) e;
            String msg = apiException.getMessage();
            switch (apiException.getErrorCode()) {
                case ApiErrorCode.RESPONSE_CODE_1:
                case ApiErrorCode.RESPONSE_CODE_2:
                case ApiErrorCode.RESPONSE_CODE_3:
                case ApiErrorCode.RESPONSE_CODE_4:
                case ApiErrorCode.RESPONSE_CODE_5:
                    ToastUtils.showShort(msg);
                    break;
                default:
                    ToastUtils.showShort(msg);
                    break;
            }
        } else {
            handleFailResult(e.getMessage());
        }
    }

    public static void handleFailResult(String error) {

        if (TextUtils.isEmpty(error)) {
            ToastUtils.showShort("服务异常，请稍后再试");
            return;
        }
        if (error.contains("The network is not available")) {
            ToastUtils.showShort("网络不可用");
        } else if (error.contains("Request time out")) {
            ToastUtils.showShort("请求超时，请稍后再试");
        } else {
            ToastUtils.showShort(error);
        }
    }
}
