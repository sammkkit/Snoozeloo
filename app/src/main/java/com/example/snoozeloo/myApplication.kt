package com.example.snoozeloo

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.media.audiofx.BassBoost
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            )
        }

    }


}
