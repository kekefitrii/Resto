package com.ike.resto.ui.base

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {

    override fun onCreate() {
        appContext = this.applicationContext
        super.onCreate()

    }

    companion object {
        var appContext: Context? = null
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .build()
    }
}
