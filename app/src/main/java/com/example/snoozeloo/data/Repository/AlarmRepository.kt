package com.example.snoozeloo.data.Repository

import com.example.snoozeloo.data.Alarm
import com.example.snoozeloo.data.Database.AlarmDao
import kotlinx.coroutines.flow.Flow

class AlarmRepository(private val alarmDao: AlarmDao) {

    val alarms : Flow<List<Alarm>> =alarmDao.GetAllAlarms()

    suspend fun insertAlarm(alarm: Alarm){
        alarmDao.AddNewAlarm(alarm)
    }
}