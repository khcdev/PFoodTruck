package com.example.ckh.foodtruck.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ckh.foodtruck.R;
import com.example.ckh.foodtruck.utility.GlobalApplication;
import com.example.ckh.foodtruck.utility.SampleSignupActivity;
import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Ckh on 2016-09-08.
 * 메인에서 User로 접근했을 경우 나타나게 되는 Activity
 * 비회원 로그인,카카오톡 로그인,페이스북 로그인 가능
 * Tech
 * ->kakao,facebook API이용
 */


//메서드 명 수정 할 것
public class UserLogin extends Activity {

    private SessionCallback callback;       //kko
    private CallbackManager callbackManager; //fb

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(com.example.ckh.foodtruck.R.layout.user_main);
        GlobalApplication.seller=false;
        ImageView imgbtn_NonAcc = (ImageView) findViewById(R.id.usermain_btn_NonAccLogin);
        ImageView imgbtn_fblogin = (ImageView) findViewById(R.id.usermain_btn_fbLogin);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        com.kakao.usermgmt.LoginButton btn = (LoginButton) findViewById(R.id.com_kakao_login);
        btn.setAlpha((float)0.0);
        class btnClickListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.usermain_btn_NonAccLogin :
                        Intent intent = new Intent(UserLogin.this,UserTabFragment.class);
                        GlobalApplication.fbuser=false;
                        GlobalApplication.kkouser=false;
                        GlobalApplication.User_info_name=null;
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.usermain_btn_fbLogin:
                        isLoginfacebook();
                        break;
                }
            }
        }

        View.OnClickListener listener=new btnClickListener();

        imgbtn_NonAcc.setOnClickListener(listener);
        imgbtn_fblogin.setOnClickListener(listener);
    }


    private void isLoginfacebook(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        //페이스북에서 권한을 가져오는것것
       LoginManager.getInstance().logInWithReadPermissions(UserLogin.this,
                Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult result) {

                GraphRequest request;
                request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject user, GraphResponse response) {
                        if (response.getError() != null) {

                        } else {
                            Log.i("TAG", "user: " + user.toString());

                            Log.i("TAG", "AccessToken: " + result.getAccessToken().getToken());
                            try{

                                GlobalApplication.User_info_name=user.getString("name");
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            setResult(RESULT_OK);
                            Toast.makeText(UserLogin.this, GlobalApplication.User_info_name+" 님 환영합니다.", Toast.LENGTH_SHORT).show();
                            GlobalApplication.fbuser=true;
                            GlobalApplication.kkouser=false;
                            Intent intent = new Intent(UserLogin.this,UserTabFragment.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("test", "Error: " + error);
                error.printStackTrace();
                finish();
            }

            @Override
            public void onCancel() {
                finish();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
         callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.user_main);
        }
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, SampleSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}
