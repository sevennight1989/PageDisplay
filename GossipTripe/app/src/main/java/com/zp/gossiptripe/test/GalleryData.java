package com.zp.gossiptripe.test;

import com.zp.gossiptripe.main.gallery.GalleryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uiprj on 11/21/16.
 */

public class GalleryData {
//emulate test data
    public static List<GalleryBean> getGalleryData() {
        List<GalleryBean> _list = new ArrayList<>();
        GalleryBean bean = new GalleryBean();
        bean.setName("乔乔妞妞");
        bean.setAge(9);
        bean.setNumber(11205);
        bean.setLocation("上海");
        bean.setOrganization("浦东新区少年宫");
        bean.setHeadPicSrc("http://img5q.duitang.com/uploads/item/201503/14/20150314142802_hvCvY.jpeg");
        bean.setProductContent("冰人和火人被困在一个恐怖的地方,他们齐心协力一起找到终点，回到他们的家");
        bean.setProductPreviewSrc("http://www.234.cn/uploadfile/image/admin_150810179_h1.jpg");
        bean.setProductName("《小蜗牛上树》");
        bean.setPraiseTag(1);
        _list.add(bean);
        return _list;
    }
}
