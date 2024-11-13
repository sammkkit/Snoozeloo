package com.example.snoozeloo.AlarmSettingUtils


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.snoozeloo.data.Repository.AlarmRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.getKoin
import org.koin.java.KoinJavaComponent.inject

class AlarmReciever: BroadcastReceiver() {
    private val alarmRepository: AlarmRepository by inject(AlarmRepository::class.java)
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmID = intent?.getIntExtra("alarm_id", -1) ?: -1
        CoroutineScope(Dispatchers.IO).launch {
            alarmRepository.UpdateAlarmtoON(alarmID)
        }
        Log.d("AlarmReciever", "$alarmID Alarm triggered!")
        Toast.makeText(context, "Alarm triggered!", Toast.LENGTH_SHORT).show()
    }
}