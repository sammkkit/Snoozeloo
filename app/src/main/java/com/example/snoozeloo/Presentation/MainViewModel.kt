package com.example.snoozeloo.Presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.AlarmSettingUtils.AlarmReciever
import com.example.snoozeloo.data.Alarm
import com.example.snoozeloo.data.Repository.AlarmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class MainViewModel(
    private val alarmRepository: AlarmRepository,
    val context: Context
): ViewModel() {

    private val _alarmList = MutableStateFlow<List<Alarm>>(emptyList())
    val alarmList = _alarmList.asStateFlow()
    init {
        Log.d("MainViewModel", "MainViewModel initialized")
        viewModelScope.launch {
            alarmRepository.alarms.collect { alarms ->
                _alarmList.value = alarms
                Log.d("MainViewModel", "alarmList: ${_alarmList.value}")
            }

        }

    }
    fun addAlarm(alarm: Alarm) {
        viewModelScope.launch {
            Log.d("MainViewModel", "addAlarm called")
            val newAlarmID = alarmRepository.insertAlarm(alarm)
            Log.d("MainViewModel", "New alarm ID: $newAlarmID")
            val newAlarm = alarm.copy(id = newAlarmID.toInt())
            ScheduleAlarm(newAlarm)
        }
    }
    fun CancelAlarm(alarm: Alarm){
        viewModelScope.launch {
            Log.d("MainViewModel", "CancelAlarm called ${alarm.id}")
            alarmRepository.cancelAlarm(context,alarm)
        }
    }
    fun ScheduleAlarm(alarm: Alarm) {
        Log.d("MainViewModel", "Alarm Scheduled ${alarm.id}")
        alarmRepository.scheduleAlarm(context,alarm)
    }
}