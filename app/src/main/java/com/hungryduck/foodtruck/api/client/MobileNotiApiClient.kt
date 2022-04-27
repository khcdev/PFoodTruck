package com.hungryduck.foodtruck.api.client

import com.hungryduck.foodtruck.api.model.NotiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MobileNotiApiClient {
    @GET("/foodtruck/notification")
    fun fetchMobileNoti(
        @Query("os") os: String,
        @Query("osVer") osVer: Int,
        @Query("appVer") appVer: Int
    ): Call<NotiResponse>
}