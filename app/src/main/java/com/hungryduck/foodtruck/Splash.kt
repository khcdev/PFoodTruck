package com.hungryduck.foodtruck

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class Splash : Activity() {
    public override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        val appVer = BuildConfig.VERSION_CODE
        val osVer = android.os.Build.VERSION.SDK_INT
        startActivity(Intent(this, MainActivity::class.java)).also {
            finish()
        }
    }
}