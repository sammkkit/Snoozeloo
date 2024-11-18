package com.example.snoozeloo.Presentation.Screens

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.bundle.Bundle
import com.example.snoozeloo.Presentation.MainViewModel
import com.example.snoozeloo.R
import com.example.snoozeloo.data.Alarm
import org.koin.androidx.compose.koinViewModel

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
    val mainViewModel = koinViewModel<MainViewModel>()
    var alarm by remember { mutableStateOf<Alarm?>(null) }
    if (alarmId != null) {
        val alarmByID by mainViewModel.alarmByID.collectAsState()
        LaunchedEffect(alarmId) {
            mainViewModel.getAlarmByID(alarmId)
            Log.d("AlarmReceiver", "Alarm :$alarmByID")
        }
        alarm = alarmByID
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.clock_icon),
            contentDescription = "Alarm Clock",
            modifier = Modifier.size(100.dp).padding(bottom = 4.dp),
            colorFilter = tint(Color(0xFF4664FF))
        )
        Text(
            text = String.format("%02d:%02d", alarm?.hour ?: 0, alarm?.minute ?: 0),
            color = Color(0xFF4664FF),
            fontSize = 80.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${alarm?.name}",
            color = Color.Black,
            fontSize = 50.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (context is ComponentActivity) {
                    context.finish() // Close the activity
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4664FF),
                contentColor = Color.White
            ),
        ) {
            Text(text = "Dismiss")
        }
    }
}
@Preview
@Composable
fun prevvv(){
    AlarmTriggerScreen(alarmId = 1)
}
