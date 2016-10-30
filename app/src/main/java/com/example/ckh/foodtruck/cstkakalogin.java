package com.example.ckh.foodtruck;

import android.content.Context;
import android.util.AttributeSet;
import com.kakao.usermgmt.LoginButton;

/**
 * Created by Ckh on 2016-10-30.
 */
public class cstkakalogin extends LoginButton {

    public cstkakalogin(Context context) {
        super(context);
    }

    public cstkakalogin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public cstkakalogin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        inflate(getContext(), com.kakao.usermgmt.R.layout.kakao_login_layout, this);
    }
}
