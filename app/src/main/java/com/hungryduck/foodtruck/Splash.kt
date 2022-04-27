package com.hungryduck.foodtruck

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.hungryduck.foodtruck.NotiApiStatusAlert.ERROR
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
        setContentView(R.layout.activity_splash)

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

                    NotiApiStatusAlert.getStatus(res!!.status)
                        .getAlertDialog(this@Splash, res)
                        .show()
                }

                override fun onFailure(call: Call<NotiResponse>, t: Throwable) {
                    Log.e("FetchError", "Fail to get noti", t)

                    ERROR.getAlertDialog(
                        this@Splash,
                        NotiResponse.getDefaultError()
                    )
                }
            })
    }
}

enum class NotiApiStatusAlert(val value: String) : AlertBtnImpl {
    PASS("pass") {
        override fun getAlertDialog(context: Activity, notiResponse: NotiResponse) =
            AlertDialog.Builder(context)
                .setTitle(notiResponse.title)
                .setMessage(notiResponse.message)
                .setPositiveButton(POSITIVE_BTN_TEXT) { _, _ ->
                    context.finish()
                }
                .create()
    },
    REDIRECT("redirect") {
        override fun getAlertDialog(context: Activity, notiResponse: NotiResponse) =
            AlertDialog.Builder(context)
                .setTitle(notiResponse.title)
                .setMessage(notiResponse.message)
                .setPositiveButton(POSITIVE_BTN_TEXT) { _, _ ->
                    context.finish()
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(notiResponse.location)
                        )
                    )
                }.setNegativeButton(NEGATIVE_BTN_TEXT) { _, _ ->
                    context.finish()
                }
                .create()
    },
    BANNED("banned") {
        override fun getAlertDialog(context: Activity, notiResponse: NotiResponse) =
            AlertDialog.Builder(context)
                .setTitle(notiResponse.title)
                .setMessage(notiResponse.message)
                .setPositiveButton(POSITIVE_BTN_TEXT) { _, _ ->
                    context.finish()
                }
                .create()
    },
    ERROR("error") {
        override fun getAlertDialog(context: Activity, notiResponse: NotiResponse) =
            AlertDialog.Builder(context)
                .setTitle(notiResponse.title)
                .setMessage(notiResponse.message)
                .setPositiveButton(POSITIVE_BTN_TEXT) { _, _ ->
                    context.finish()
                }
                .create()
    };

    companion object {
        fun getStatus(status: String): NotiApiStatusAlert {
            for (notiApiStatusAlert: NotiApiStatusAlert in values()) {
                if (status == notiApiStatusAlert.value) {
                    return notiApiStatusAlert
                }
            }

            throw RuntimeException("No Api Status")
        }

        const val POSITIVE_BTN_TEXT = "OK"
        const val NEGATIVE_BTN_TEXT = "Cancel"
    }
}

interface AlertBtnImpl {
    fun getAlertDialog(context: Activity, notiResponse: NotiResponse): AlertDialog
}