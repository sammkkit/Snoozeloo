package com.example.snoozeloo.Presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.data.Alarm
import com.example.snoozeloo.data.Repository.AlarmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val alarmRepository: AlarmRepository
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
            alarmRepository.insertAlarm(alarm)
        }
    }
}