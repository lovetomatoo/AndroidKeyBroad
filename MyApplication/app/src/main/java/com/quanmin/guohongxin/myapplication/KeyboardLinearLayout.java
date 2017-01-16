package com.quanmin.guohongxin.myapplication;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by guo_hx on 2017/1/16.
 */

public class KeyboardLinearLayout extends LinearLayout {

    private String TAG = getClass().getSimpleName();

    private int minKeyboradHeight;

    private Handler mHandler=new Handler();
    private OnStatusListener mListener;
    private boolean mShow;

    public KeyboardLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public KeyboardLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeyboardLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        /*
        if (context instanceof Activity){
            Activity activity= (Activity) context;
            minKeyboradHeight= DensityUtil.getScreenHeight(activity)/5;
            LogUtil.d("min_height:"+minKeyboradHeight);
        }
        */
        minKeyboradHeight= Utils.dip2px(33*5+37);
        Log.i(TAG, "minKeyboradHeight == "+minKeyboradHeight);

    }

    @Override
    protected void onSizeChanged(int w, final int h, int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /*
        if (mSelected){
            mSelected=false;
            return;
        }
        */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "oldh - h == " + (oldh - h));
                if (oldh-h>minKeyboradHeight){
                    mShow=true;
                    Log.i(TAG, "show");
                    if (mListener!=null){
                        mListener.statusChange(true);
                    }
                }else if (h-oldh>minKeyboradHeight){
                    mShow=false;
                    Log.i(TAG, "hide");
                    if (mListener!=null){
                        mListener.statusChange(false);
                    }
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mShow && ev.getAction()==MotionEvent.ACTION_DOWN){

        }
        return super.dispatchTouchEvent(ev);
    }

    /*
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mShow && ev.getAction()==MotionEvent.ACTION_DOWN){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){

        }
        return super.onTouchEvent(event);
    }
    */

    public interface OnStatusListener{
        void statusChange(boolean show);
    }

    public void setStatusListener(OnStatusListener listener){
        mListener=listener;
    }


}
