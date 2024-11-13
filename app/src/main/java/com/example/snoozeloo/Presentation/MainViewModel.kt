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
        Log.d("MainViewModel", "alarmList: ${alarmList.value}")
        viewModelScope.launch {
            alarmRepository.alarms.collect { alarms ->
                _alarmList.value = alarms
            }
        }
    }
    fun addAlarm(alarm: Alarm){
        viewModelScope.launch {
            val newAlarmID = alarmRepository.insertAlarm(alarm)
            Log.d("AlarmRepository2", "New alarm ID: $newAlarmID")
            val newAlarm = alarm.copy(id = newAlarmID.toInt())
            ScheduleAlarm(context,newAlarm)
        }
    }
    fun CancelAlarm(context: Context,alarm: Alarm){
        viewModelScope.launch {
            alarmRepository.cancelAlarm(context,alarm)
        }
    }
    fun deleteAlarmItem(alarm: Alarm){
        viewModelScope.launch {
            alarmRepository.DeleteAlarm(alarm)
        }
    }
    fun DeleteAlarm(context: Context,alarm: Alarm){
        alarmRepository.cancelAlarm(context,alarm)
    }
    fun ScheduleAlarm(context: Context, alarm: Alarm) {
        alarmRepository.scheduleAlarm(context,alarm)
    }
}