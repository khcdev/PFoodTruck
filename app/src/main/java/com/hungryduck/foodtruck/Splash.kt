package com.hungryduck.foodtruck

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.hungryduck.foodtruck.api.client.MobileNotiApiClient
import com.hungryduck.foodtruck.api.model.NotiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Splash : Activity() {
    public override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        val appVer = BuildConfig.VERSION_CODE
        val osVer = android.os.Build.VERSION.SDK_INT

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val mobileNotiApiClient = retrofit.create(MobileNotiApiClient::class.java)
        mobileNotiApiClient.fetchMobileNoti("and", osVer, appVer).enqueue(
            object : Callback<NotiResponse> {
                override fun onResponse(
                    call: Call<NotiResponse>,
                    response: Response<NotiResponse>
                ) {
                    val res = response.body()
                }

                override fun onFailure(call: Call<NotiResponse>, t: Throwable) {
                    Log.e("FetchError", "Fail to get noti", t)
                    Toast.makeText(this@Splash, "Error Alert", Toast.LENGTH_SHORT).show()
                }
            })
    }
}