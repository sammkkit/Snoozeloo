package com.example.snoozeloo.data.Repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.snoozeloo.AlarmSettingUtils.AlarmReciever
import com.example.snoozeloo.data.Alarm
import com.example.snoozeloo.data.Database.AlarmDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Calendar
class AlarmRepository(private val alarmDao: AlarmDao) {

    val alarms : Flow<List<Alarm>> =alarmDao.GetAllAlarms()

    suspend fun insertAlarm(alarm: Alarm) :Long{
        return alarmDao.AddNewAlarm(alarm)
    }
    suspend fun DeleteAlarm(alarm: Alarm){
        alarmDao.DeleteAlarm(alarm)
    }
    suspend fun UpdateAlarmtoOff(alarmID:Int){
        alarmDao.UpdateAlarmToOff(alarmID)
    }
    suspend fun UpdateAlarmtoON(alarmID:Int){
        alarmDao.UpdateAlarmToON(alarmID)
    }
    suspend fun DeleteAllalarms(){
        alarmDao.DeleteAllAlarms()
    }
    fun scheduleAlarm(context: Context, alarm: Alarm) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, alarm.hour)
            set(Calendar.MINUTE, alarm.minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        val intent = Intent(context, AlarmReciever::class.java).apply {
            putExtra("alarm_id", alarm.id)
            Log.d("AlarmRepository", "Alarm scheduled for: ${calendar.time} ,${alarm.id}")
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

        )
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
        CoroutineScope(Dispatchers.IO).launch {
            UpdateAlarmtoON(alarm.id)
        }
    }
    fun cancelAlarm(context: Context, alarm: Alarm) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReciever::class.java).apply {
            putExtra("alarm_id", alarm.id)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Cancel the alarm using the pending intent
        alarmManager.cancel(pendingIntent)
        CoroutineScope(Dispatchers.IO).launch {
            UpdateAlarmtoOff(alarm.id)
        }
    }

}