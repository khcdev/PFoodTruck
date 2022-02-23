package com.hungryduck.foodtruck.utility;

import android.app.Activity;
import android.app.Application;
import com.hungryduck.viewmodel.TruckItemDTO;
import com.hungryduck.viewmodel.FavorTruckDTO;
import com.kakao.auth.KakaoSDK;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ckh on 2016-09-16.
 * Application class의 상속
 * AndroidManifest에 등록 해주어야함.
 */
public class GlobalApplication extends Application {

    //volatile 키워드 -> 단어 뜻은 '변덕스러운' 이라는 뜻을 가지고 있다.
    //프로그래밍 언어에서의 정의는 '자주 변할수 있으니 잘 가져다 쓰라'
    //1)특정 최적화에 주의 한다. 2) 멀티 쓰레드 환경에서 주의한다.
    private static volatile GlobalApplication instance = null;
    private static volatile Activity currentActivity = null;
    public static String dbName = "Db_ver10.db";
    public static String User_info_name=null;
    public static HashMap<String,String> truckintro=null;
    public static HashMap<String,String> trucknoti=null;
    //TODO : 유저정보 리팩토링 필요함, 싱글턴 객체로 선언하여 사용할 예정
    public static boolean favor_101=false;
    public static boolean favor_102=false;
    public static boolean favor_103=false;
    public static boolean flag_truckinfolist=true;
    public static boolean openStore= false;
    public static boolean kkouser=false;
    public static boolean fbuser=false;
    public static boolean seller=false;
    public static ArrayList<TruckItemDTO> dataList = new ArrayList<>();
    public static ArrayList<FavorTruckDTO> favortruckList= new ArrayList<>();


    public static Activity getCurrentActivity() {
        return currentActivity;
    }
    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.

    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }
    public static GlobalApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }
    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
