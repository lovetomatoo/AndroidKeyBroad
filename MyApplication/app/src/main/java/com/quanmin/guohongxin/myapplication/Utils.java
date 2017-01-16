package com.quanmin.guohongxin.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

/**
 * Created by guo_hx on 2017/1/16.
 */

public class Utils {

    public static int dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static void ToastShow(String s, Context context) {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
