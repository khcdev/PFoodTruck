/**
 * Copyright 2014 Daum Kakao Corp.
 *
 * Redistribution and modification in source or binary forms are not permitted without specific prior written permission. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hungryduck.foodtruck.utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hungryduck.foodtruck.user.UserLogin;
import com.hungryduck.foodtruck.user.UserTabFragment;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;

import com.kakao.util.helper.log.Logger;

/**
 * 유효한 세션이 있다는 검증 후
 * me를 호출하여 가입 여부에 따라 가입 페이지를 그리던지 Main 페이지로 이동 시킨다.
 */
public class SampleSignupActivity extends Activity {
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
                Log.i("ckh","접속실패");
                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
                Log.i("ckh","onSessionClosed");
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Logger.d("UserProfile : " + userProfile);
                GlobalApplication.User_info_name=userProfile.getNickname();
                redirectMainActivity();
                Log.i("ckh","접속성공!");
            }

            @Override
            public void onNotSignedUp() {
                Log.i("ckh","onNotsignedUp");
            }
        });
    }

    private void redirectMainActivity() {
        Intent intent = new Intent(SampleSignupActivity.this,UserTabFragment.class);
        startActivity(intent);
        Toast.makeText(SampleSignupActivity.this, GlobalApplication.User_info_name+" 님 환영합니다.", Toast.LENGTH_SHORT).show();
        GlobalApplication.kkouser=true;
        GlobalApplication.fbuser=false;
        Log.i("ckh","다음 액티비티 접근");
        finish();
    }
    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, UserLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
