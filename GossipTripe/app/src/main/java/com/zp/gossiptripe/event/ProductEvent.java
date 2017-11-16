package com.zp.gossiptripe.event;

/**
 * Created by ZhangPeng on 10-16-0016.
 */
public class ProductEvent {

    private String mMsg;
    private String tag;


    public ProductEvent(String mMsg, String tag) {
        this.mMsg = mMsg;
        this.tag = tag;
    }

    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
