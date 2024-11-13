package com.example.snoozeloo.Presentation.Screens

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.bundle.Bundle

class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Extract the alarm_id passed from the PendingIntent
        val alarmId = intent?.getIntExtra("alarm_id", -1) ?: -1

        setContent {
            AlarmTriggerScreen(alarmId = alarmId)
        }
    }
}
@Composable
fun AlarmTriggerScreen(alarmId: Int?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Alarm $alarmId Triggered!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Handle any action here */ }) {
            Text("Take Action")
        }
    }
}
