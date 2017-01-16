package com.quanmin.guohongxin.myapplication;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by guo_hx on 2017/1/16.
 */

public class KeyBoardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {

    private String TAG = getClass().getSimpleName();

    private View mContentView;
    private int mPreHeight;
    private int mOriginHeight;
    private KeyBoardChangeListener.KeyBoardListener mKeyBoardListen;

    public KeyBoardChangeListener(Activity activity) {

        if (activity == null || !(activity instanceof Activity)) {
            throw new NullPointerException("activity is null");
        }

        mContentView = activity.findViewById(android.R.id.content);
        if (mContentView != null) {
            mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

    }

    @Override
    public void onGlobalLayout() {
        int currHeight = mContentView.getHeight();
        if (currHeight == 0) {
            return;
        }
        boolean hasChange = false;
        if (mPreHeight == 0) {
            mPreHeight = currHeight;
            mOriginHeight = currHeight;
        } else {
            if (mPreHeight != currHeight) {
                hasChange = true;
                mPreHeight = currHeight;
            } else {
                hasChange = false;
            }
        }
        if (hasChange) {
            boolean isShow;
            int keyboardHeight = 0;
            if (mOriginHeight == currHeight) {
                //hidden
                isShow = false;
            } else {
                //show
                keyboardHeight = mOriginHeight - currHeight;
                isShow = true;
            }

            if (mKeyBoardListen != null) {
                mKeyBoardListen.onKeyboardChange(isShow, keyboardHeight);
            }
        }
    }

    public interface KeyBoardListener {

        void onKeyboardChange(boolean isShow, int keyboardHeight);
    }

    public void setKeyBoardListener(KeyBoardChangeListener.KeyBoardListener keyBoardListen) {
        mKeyBoardListen = keyBoardListen;
    }

    //when Activity finish, must call this methed
    public void removeListener() {
        if (mContentView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }
}
