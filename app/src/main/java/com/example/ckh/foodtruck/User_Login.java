package com.example.ckh.foodtruck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;

import android.widget.Toast;
import com.facebook.*;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import org.json.JSONObject;

import java.util.Arrays;


/**
 * Created by Ckh on 2016-09-08.
 * 메인에서 User로 접근했을 경우 나타나게 되는 Activity
 * 비회원 로그인,카카오톡 로그인,페이스북 로그인 가능
 *
 * Tech
 * ->kakao,facebook API이용
 */
public class User_Login extends Activity{
    private SessionCallback mKakaocallback;
    private CallbackManager callbackManager;
    UserData UserData;

    @Override
    public void onCreate(Bundle b ){
        super.onCreate(b);
        setContentView(R.layout.user_main);

        ImageView imgbtn_NonAcc=(ImageView) findViewById(R.id.usermain_btn_NonAccLogin);
        ImageView imgbtn_kkoLogin=(ImageView) findViewById(R.id.usermain_btn_kkoLogin);
        ImageView imgbtn_fbLogin=(ImageView) findViewById(R.id.usermain_btn_fbLogin);


        class btnClickListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.usermain_btn_NonAccLogin :

                        break;
                    case R.id.usermain_btn_kkoLogin :
                        UserData = new UserData();
                        isKakaoLogin();
                        Toast.makeText(User_Login.this,UserData.getuser_id(),Toast.LENGTH_LONG).show();
                        break;
                    case R.id.usermain_btn_fbLogin :
                        UserData = new UserData();
                        isLoginFacebook();
                        break;
                }
            }
        }

        View.OnClickListener listener=new btnClickListener();
        imgbtn_NonAcc.setOnClickListener(listener);
        imgbtn_kkoLogin.setOnClickListener(listener);
        imgbtn_fbLogin.setOnClickListener(listener);


    }
    private void isKakaoLogin() {
        // 카카오 세션을 오픈한다
        mKakaocallback = new SessionCallback();
        com.kakao.auth.Session.getCurrentSession().addCallback(mKakaocallback);
        com.kakao.auth.Session.getCurrentSession().checkAndImplicitOpen();
        com.kakao.auth.Session.getCurrentSession().open(AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN, User_Login.this);
    }
    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            Log.d("TAG" , "세션 오픈됨");
            // 사용자 정보를 가져옴, 회원가입 미가입시 자동가입 시킴
            KakaorequestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Log.d("TAG" , exception.getMessage());
            }
        }
    }
    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void KakaorequestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                int ErrorCode = errorResult.getErrorCode();
                int ClientErrorCode = -777;

                if (ErrorCode == ClientErrorCode) {
                    Toast.makeText(getApplicationContext(), "카카오톡 서버의 네트워크가 불안정합니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG" , "오류로 카카오로그인 실패 ");
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("TAG" , "오류로 카카오로그인 실패 ");
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                UserData.setuser_id(String.valueOf(userProfile.getId()));
                UserData.setUser_name(userProfile.getNickname());
                //setLayoutText();
            }

            @Override
            public void onNotSignedUp() {
                // 자동가입이 아닐경우 동의창
            }
        });
    }
//fragment layout의 도입
    /*private void setLayoutText(){
        tv_user_id.setText(userId);
        tv_user_name.setText(userName);

        Picasso.with(this)
                .load(profileUrl)
                .fit()
                .into(iv_user_profile);
    }*/
    private void isLoginFacebook(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        CallbackManager callbackManager = CallbackManager.Factory.create();
        Toast.makeText(this, "access!", Toast.LENGTH_LONG).show();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request;
                request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject user, GraphResponse response) {
                        Log.d("TAG","페이스북 로그인 결과"+response.toString());
                        try{
                            UserData.setuser_id(user.getString("email"));
                            UserData.setUser_name(user.getString("name"));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
                Log.d("TAG","페이스북 토큰->"+loginResult.getAccessToken().getToken());
                Log.d("TAG","페이스북 UserID->"+loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                Log.d("TAG","취소됨");
            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
