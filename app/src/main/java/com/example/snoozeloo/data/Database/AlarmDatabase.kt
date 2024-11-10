package com.example.snoozeloo.data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snoozeloo.data.Alarm

@Database(entities = [Alarm::class], version = 1, exportSchema = true)
abstract class AlarmDatabase :RoomDatabase() {
    abstract fun alarmDao() : AlarmDao
}