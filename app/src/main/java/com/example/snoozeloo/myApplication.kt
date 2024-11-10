package com.example.snoozeloo

import android.app.Application
import com.example.snoozeloo.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class myapplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@myapplication)
            modules(
                AppModule
            ) // Load your Koin modules
        }
    }
}