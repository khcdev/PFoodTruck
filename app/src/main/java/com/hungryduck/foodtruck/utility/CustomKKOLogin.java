package com.hungryduck.foodtruck.utility;

import android.content.Context;
import android.util.AttributeSet;
import com.kakao.usermgmt.LoginButton;

/**
 * Created by ckj on 2017-05-29.
 */
public class CustomKKOLogin extends LoginButton {
    public CustomKKOLogin(Context context) {
        super(context);
    }

    public CustomKKOLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomKKOLogin(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        inflate(getContext(), com.kakao.usermgmt.R.layout.kakao_login_layout, this);
    }
}
