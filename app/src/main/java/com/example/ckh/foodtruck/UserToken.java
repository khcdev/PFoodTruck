package com.example.ckh.foodtruck;

/**
 * Created by ckj on 2017-05-26.
 * 앱의 이용자를 구분하고 유저에 대한 정보를 저장하기 위한 토큰 취급하여 사용할 싱글턴 클래스 입니다.
 *
 */
public class UserToken {
    private static UserToken userToken;

    private UserToken(){}

    public static UserToken getInstance(){
        if(userToken==null){
            userToken =  new UserToken();

        }
        return userToken;
    }
}