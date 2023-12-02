package com.ishop

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IShopApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}