package com.zp.gossiptripe;

import android.app.Application;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.xutils.x;


/**
 * Created by uiprj on 9/30/16.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Logger.d(deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Logger.d(s + "   " + s1);
            }
        });
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
