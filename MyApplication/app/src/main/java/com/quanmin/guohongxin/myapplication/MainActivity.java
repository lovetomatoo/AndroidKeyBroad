package com.quanmin.guohongxin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTvTest;
    private EditText mEtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        mTvTest = (TextView) findViewById(R.id.tv_test);
        mEtTest = (EditText) findViewById(R.id.et_test);

        new KeyBoardChangeListener(this).setKeyBoardListener(new KeyBoardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                Log.d("guohongxin", "isShow = [" + isShow + "], keyboardHeight = [" + keyboardHeight + "]");
            }
        });

    }
}
