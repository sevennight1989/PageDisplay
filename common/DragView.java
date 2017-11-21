package com.android.ap;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class DragView extends View {
    private int lastX;
    private int lastY;
    private int left;
    private int right;
    private int height;
    private float mParentHeight;
    int cTop = 0;
    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        mWm.getDefaultDisplay().getRealSize(point);
        int screenHeight = point.y;
        mParentHeight = screenHeight - getStatusBarHeight();

    }

    private int getStatusBarHeight() {
        int statusBarHeight = 0;
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        left = l;
        right = r;
        height = b - t;
    }

    public boolean onTouchEvent(MotionEvent event) {
        //获取到手指处的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offY = y - lastY;
                //调用layout方法来重新放置它的位置
                cTop = getTop() + offY;

                if (cTop <= 0) {
                    cTop = 0;
                }
                if (cTop >= mParentHeight - height) {
                    cTop = (int) (mParentHeight - height);
                }
                int cBottom = cTop + height;
                layout(left, cTop, right, cBottom);
                break;

            case MotionEvent.ACTION_UP:
                int upY = cTop ;
                float precent = upY / (mParentHeight-height);
                break;
        }

        return true;
    }
}