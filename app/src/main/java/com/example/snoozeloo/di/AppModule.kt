package com.example.snoozeloo.di

import androidx.lifecycle.get
import androidx.room.Room
import com.example.snoozeloo.Presentation.MainViewModel
import com.example.snoozeloo.data.Database.AlarmDao
import com.example.snoozeloo.data.Database.AlarmDatabase
import com.example.snoozeloo.data.Repository.AlarmRepository
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
val AppModule = module {
    single<AlarmDatabase> {
        Room.databaseBuilder(get(),AlarmDatabase::class.java,"alarm_database").build()
    }
    single<AlarmDao> {
        get<AlarmDatabase>().alarmDao()
    }
    single<AlarmRepository> {
        AlarmRepository(alarmDao = get())
    }
    viewModelOf(::MainViewModel)

}