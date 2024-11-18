package com.example.snoozeloo.AlarmSettingUtils


import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.snoozeloo.MainActivity
import com.example.snoozeloo.Presentation.Screens.AlarmActivity
import com.example.snoozeloo.data.Repository.AlarmRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.getKoin
import org.koin.java.KoinJavaComponent.inject

class AlarmReciever: BroadcastReceiver() {
    private val alarmRepository: AlarmRepository by inject(AlarmRepository::class.java)
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmID = intent?.getIntExtra("alarm_id", -1)
        Log.d("AlarmReceiver", "Alarm received with ID: $alarmID")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("AlarmReceiver", "Updating alarm status to OFF for ID: $alarmID")
            alarmRepository.UpdateAlarmtoOff(alarmID!!)
            Log.d("AlarmReceiver", "Alarm status updated successfully.")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (context?.let {
                    ActivityCompat.checkSelfPermission(it, Manifest.permission.POST_NOTIFICATIONS)
                } != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("AlarmReceiver", "Notification permission is not granted.")
                return
            }
        }
        Log.d("AlarmReceiver", "$alarmID Alarm triggered! before norification")
        Log.d("AlarmReceiver", "Building notification for alarm ID: $alarmID")
        val alarmIntent = Intent(context, AlarmActivity::class.java).apply {
            putExtra("alarm_id", alarmID)  // Passing alarm data
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            alarmID ?: 0,
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        // Build the Notification
        val notification = NotificationCompat.Builder(context!!, "alarm_channel")
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
            .setContentTitle("Alarm Triggered")
            .setContentText("Tap to view Alarm Trigger Screen.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)  // Set the action to launch MainActivity with Compose navigation
            .setAutoCancel(true)  // Dismiss notification once clicked
            .build()

        Log.d("AlarmReceiver", "Notification built successfully. Showing notification.")
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1, notification)
        Log.d("AlarmReceiver", "Notification sent for alarm ID: $alarmID")

        context.let {
            val alarmintent = Intent(it, AlarmActivity::class.java).apply {
                putExtra("alarm_id", alarmID) // Passing alarm data
                flags = Intent.FLAG_ACTIVITY_NEW_TASK // Required to start Activity from BroadcastReceiver
            }
            it.startActivity(alarmintent)
        }
    }
}